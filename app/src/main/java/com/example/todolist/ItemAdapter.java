package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<MyItem> myItem;

    public ItemAdapter(Context c, ArrayList<MyItem> lst) {
        context = c;
        myItem = lst;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.todo_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemTitle.setText(myItem.get(position).getItemTitle());
        holder.itemDescription.setText(myItem.get(position).getItemDescription());
        holder.itemDate.setText(myItem.get(position).getItemDate());

        final String getTitle = myItem.get(position).getItemTitle();
        final String getDesc = myItem.get(position).getItemDescription();
        final String getDate = myItem.get(position).getItemDate();
        final String getKey = myItem.get(position).getItemKey();

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent change = new Intent(context, EditItemActivity.class);
                change.putExtra("itemTitle", getTitle);
                change.putExtra("itemDescription", getDesc);
                change.putExtra("itemDate", getDate);
                change.putExtra("itemKey", getKey);
                context.startActivity(change);

            }

        });
    }

    @Override
    public int getItemCount() {
        return myItem.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemTitle, itemDescription, itemDate, itemKey;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemDescription = itemView.findViewById(R.id.itemDescription);
            itemDate = itemView.findViewById(R.id.itemDate);

        }
    }

}
