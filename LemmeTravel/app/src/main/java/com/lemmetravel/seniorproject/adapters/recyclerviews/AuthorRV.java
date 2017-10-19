package com.lemmetravel.seniorproject.adapters.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lemmetravel.seniorproject.Model.AuthorData;
import com.lemmetravel.seniorproject.R;
import com.lemmetravel.seniorproject.UserActivity;
import com.lemmetravel.seniorproject.adapters.viewholders.AuthorVH;
import com.squareup.picasso.Picasso;


public class AuthorRV extends RecyclerView.Adapter<AuthorVH>{

    AuthorData[] authorDatas;
    AuthorData[] coautData;
    Context context;

    public AuthorRV(Context context, AuthorData[] authorDatas, AuthorData[] coAutDatas) {
        this.authorDatas = authorDatas;
        this.context = context;
        this.coautData = coAutDatas;
    }

    @Override
    public AuthorVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_author_list, parent, false);
        return new AuthorVH(view,context);
    }

    @Override
    public void onBindViewHolder(AuthorVH holder, final int position) {

        if (position == 0) {
            Picasso.with(context).load("http://10.0.2.2/lemmetravel/public/uploads/" + authorDatas[0].getProfile_img()).fit().centerInside().into(holder.authorImg);
            holder.authorName.setText(authorDatas[0].getUsername());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"ไปยังหน้าของ " + authorDatas[0].getUsername(), Toast.LENGTH_LONG).show();
                    Intent nextPage = new Intent(context,UserActivity.class);
                    context.startActivity(nextPage);
                }
            });
        } else if (position > 0 && coautData != null){
            Picasso.with(context).load("http://10.0.2.2/lemmetravel/public/uploads/" + coautData[position-1].getProfile_img()).fit().centerInside().into(holder.authorImg);
            holder.authorName.setText(coautData[position-1].getUsername());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"ไปยังหน้าของ " + coautData[position-1].getUsername(), Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (coautData == null){
            return 1;
        } else {
            return coautData.length+1;
        }

    }
}
