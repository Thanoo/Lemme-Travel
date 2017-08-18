package com.lemmetravel.seniorproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemmetravel.seniorproject.R;
import com.squareup.picasso.Picasso;

public class RectclerViewAdapter extends RecyclerView.Adapter<viewHolder>{
    
    Context context;
    public RectclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_listview, parent, false);

        return new viewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        Picasso.with(context).load(R.drawable.example).resize(300,300).into(holder.tripPhoto);
        holder.tripName.setText("เที่ยวสิงคโปร์ด้วยตัวเอง");
        holder.tripDescription.setText("ทริปเที่ยวสิงคโปร์ครั้งนี้ เกิดจากการไปเจอตั๋วเครื่องบินค่ายหางแดงราคาถูก ซึ่งเป็นช่วงโปรโ...");
        holder.itemView.setTag(position);
    }



    @Override
    public int getItemCount() {
        return 10;
    }
}