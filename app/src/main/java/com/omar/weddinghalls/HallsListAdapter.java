package com.omar.weddinghalls;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HallsListAdapter extends RecyclerView.Adapter<HallsListAdapter.MyViewHolder> {
    private Context context;
    private List<HallItem> itemList = new ArrayList<>();

    public HallsListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.halls_item_list_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.thumbnail)
        ImageView thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(HallItem hallItem) {
            name.setText(hallItem.getName());

            description.setText(hallItem.getDescription());

            price.setText(context.getString(R.string.price, hallItem.getPrice()));

            Glide.with(context)
                    .load(hallItem.getThumbnail())
                    .into(thumbnail);
        }
    }

    /**
     * Replacing the adapter data set and refreshing the list
     */
    public void setItems(List<HallItem> hallItems) {
        itemList.clear();
        itemList.addAll(hallItems);
        notifyDataSetChanged();
    }
}
