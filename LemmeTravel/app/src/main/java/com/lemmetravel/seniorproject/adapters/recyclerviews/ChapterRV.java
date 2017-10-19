package com.lemmetravel.seniorproject.adapters.recyclerviews;


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemmetravel.seniorproject.Model.ChapterData;
import com.lemmetravel.seniorproject.Model.TripData;
import com.lemmetravel.seniorproject.R;
import com.lemmetravel.seniorproject.activities.ChapterActivity;
import com.lemmetravel.seniorproject.activities.EventActivity;
import com.lemmetravel.seniorproject.adapters.viewholders.ChapterVH;
import com.lemmetravel.seniorproject.adapters.viewholders.TripVH;
import com.squareup.picasso.Picasso;

public class ChapterRV  extends RecyclerView.Adapter<ChapterVH>{

    Context context;
    ChapterData[] chapterDatas;

    public ChapterRV(Context context, ChapterData[] chapterDatas) {
        this.context = context;
        this.chapterDatas = chapterDatas;
    }

    @Override
    public ChapterVH onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_chapter_list, parent, false);

        return new ChapterVH(view,context);
    }

    @Override
    public void onBindViewHolder(ChapterVH holder, final int position) {

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/thaisanslite_r1.ttf");
        holder.chapterName.setTypeface(tf);
        holder.chapterDescription.setTypeface(tf);
        holder.chapterName.setText(chapterDatas[position].getTitle());
        holder.chapterDescription.setText(chapterDatas[position].getDescription());
        holder.chapterDescription.setEllipsize(TextUtils.TruncateAt.END);
        Picasso.with(context).load("http://10.0.2.2/lemmetravel/public/uploads/" + chapterDatas[position].getCover_img()).fit().centerCrop().into(holder.chapterCover);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPage = new Intent(context,ChapterActivity.class);
                nextPage.putExtra("chapterdata", chapterDatas[position]);
                context.startActivity(nextPage);
            }
        });

    }



    @Override
    public int getItemCount() {
        return chapterDatas.length;
    }


}