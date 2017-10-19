package com.lemmetravel.seniorproject.adapters.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lemmetravel.seniorproject.R;

public class EventVH extends RecyclerView.ViewHolder {

    public TextView eventName, eventDescription;
    public ImageView eventCover;

    public EventVH(View itemView, Context context) {
        super(itemView);
        eventName = itemView.findViewById(R.id.eventName);
        eventDescription = itemView.findViewById(R.id.eventDescription);
    }
}
