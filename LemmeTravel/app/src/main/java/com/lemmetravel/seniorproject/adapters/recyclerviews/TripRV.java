package com.lemmetravel.seniorproject.adapters.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemmetravel.seniorproject.Model.TripData;
import com.lemmetravel.seniorproject.R;
import com.lemmetravel.seniorproject.activities.ChapterActivity;
import com.lemmetravel.seniorproject.activities.TripActivity;
import com.lemmetravel.seniorproject.adapters.viewHolder;
import com.lemmetravel.seniorproject.adapters.viewholders.TripVH;
import com.squareup.picasso.Picasso;


public class TripRV extends RecyclerView.Adapter<TripVH>{

    private Context context;
    private TripData[] tripDatas;

    public TripRV(Context context, TripData[] tripDatas) {
        this.context = context;
        this.tripDatas = tripDatas;
    }

    @Override
    public TripVH onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_listview, parent, false);

        return new TripVH(view,context);
    }

    @Override
    public void onBindViewHolder(TripVH holder, final int position) {

        Picasso.with(context).load("http://10.0.2.2/lemmetravel/public/uploads/" + tripDatas[position].getCover_img()).fit().centerInside().into(holder.tripPhoto);
        holder.tripName.setText(tripDatas[position].getTitle());
        holder.tripDescription.setText(tripDatas[position].getDescription());
        holder.tripDescription.setEllipsize(TextUtils.TruncateAt.END);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPage = new Intent(context,TripActivity.class);
                nextPage.putExtra("tripdatas", tripDatas[position]);
                context.startActivity(nextPage);
            }
        });
    }



    @Override
    public int getItemCount() {
        return tripDatas.length;
    }
}