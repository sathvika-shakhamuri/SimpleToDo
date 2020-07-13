package com.sathvi.simpletodo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

//Responsible for displaying data from the model into a row in the recycler view
public class itemsAdapter extends RecyclerView.Adapter<itemsAdapter.ViewHolder>{
    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }
    List<String> items;
    OnLongClickListener longClickListener;
    public itemsAdapter(List<String> items, OnLongClickListener longClickListener){
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use Layout inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent, false);
        // wrap it inside a View Holder and return it
        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //Grab item at the position
        String item = items.get(position);
        //Bind the item into the specificed viewholder
        viewHolder.bind(item);

    }

    @Override
    //Tells the RV how many items are in the llist
    public int getItemCount() {
        return items.size();
    }


    //container to provide easy acess to views that represent each row at a list
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvItem ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //Update view inside of the view holder with the data of string holder
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //Notifying the listener position was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });


        }
    }
}
