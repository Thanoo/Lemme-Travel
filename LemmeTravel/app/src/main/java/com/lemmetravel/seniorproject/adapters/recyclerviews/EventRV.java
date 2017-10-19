package com.lemmetravel.seniorproject.adapters.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lemmetravel.seniorproject.Model.ChapterData;
import com.lemmetravel.seniorproject.Model.EventData;
import com.lemmetravel.seniorproject.R;
import com.lemmetravel.seniorproject.activities.EventActivity;
import com.lemmetravel.seniorproject.adapters.viewholders.EventVH;

public class EventRV  extends RecyclerView.Adapter<EventVH> {

    Context context;
    EventData[] eventDatas;
    String chapterTitle;

    public EventRV(Context context, EventData[] eventDatas, String chapterTitle) {
        this.context = context;
        this.eventDatas = eventDatas;
        this.chapterTitle = chapterTitle;
    }
    @Override
    public EventVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event_list, parent, false);

        return new EventVH(view,context);
    }

    @Override
    public void onBindViewHolder(EventVH holder, final int position) {
        holder.eventName.setText("เหตุการณ์ที่ " + String.valueOf(position));
        //holder.eventDescription.setText(Html.fromHtml(eventDatas[position].getContent()));
        //holder.eventDescription.setEllipsize(TextUtils.TruncateAt.END);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPage = new Intent(context,EventActivity.class);
                nextPage.putExtra("eventdata", eventDatas[position]);
                nextPage.putExtra("chaptertitle", chapterTitle);
                context.startActivity(nextPage);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventDatas.length;
    }
}
