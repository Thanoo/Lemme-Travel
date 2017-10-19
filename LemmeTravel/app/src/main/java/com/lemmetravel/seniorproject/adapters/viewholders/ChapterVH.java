package com.lemmetravel.seniorproject.adapters.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lemmetravel.seniorproject.R;

public class ChapterVH extends RecyclerView.ViewHolder {

    public TextView chapterName, chapterDescription;
    public ImageView chapterCover;

    public ChapterVH(View itemView, Context context) {
        super(itemView);

        chapterName = itemView.findViewById(R.id.chapterName);
        chapterDescription = itemView.findViewById(R.id.chapterDescription);
        chapterCover = itemView.findViewById(R.id.chapter_cover);
    }
}