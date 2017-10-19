package com.lemmetravel.seniorproject.adapters.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lemmetravel.seniorproject.R;

public class AuthorVH extends RecyclerView.ViewHolder {

    public ImageView authorImg;
    public TextView authorName;

    public AuthorVH(View itemView, Context context) {
        super(itemView);

        authorImg = itemView.findViewById(R.id.author_img);
        authorName = itemView.findViewById(R.id.author_name);

    }
}
