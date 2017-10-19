package com.lemmetravel.seniorproject.adapters.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lemmetravel.seniorproject.R;

public class TripVH extends RecyclerView.ViewHolder {

    public ImageView tripPhoto;
    public TextView tripName;
    public TextView tripDescription;

    public TripVH(View itemView, final Context context) {
        super(itemView);

        tripPhoto = (ImageView) itemView.findViewById(R.id.chapter_cover);
        tripName = (TextView) itemView.findViewById(R.id.item_trip_name);
        tripDescription = (TextView) itemView.findViewById(R.id.item_trip_description);



        /**itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickIndex = (int) v.getTag();
                Intent nextPage = new Intent(context,ChapterActivity.class);
                nextPage.putExtra("PARAM", clickIndex);
                context.startActivity(nextPage);

                Toast.makeText(context, "Index : " + clickIndex, Toast.LENGTH_SHORT).show();
                //Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });**/
    }


}