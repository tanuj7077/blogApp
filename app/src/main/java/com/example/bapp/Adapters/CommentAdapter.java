package com.example.bapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bapp.Models.Comment;
import com.example.bapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private Context mContext;
    private List<Comment> mdata;

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.row_comment,parent,false);
        return new CommentViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        String usrImg=mdata.get(position).getUimg();
        if(usrImg != null)
        {
            Glide.with(mContext).load(usrImg).into(holder.img_user);
        }
        else{
            Glide.with(mContext).load(R.drawable.profile_pic).into(holder.img_user);
        }
        //Glide.with(mContext).load(mdata.get(position).getUimg()).into(holder.img_user);
        holder.tv_name.setText(mdata.get(position).getUname());
        holder.tv_content.setText(mdata.get(position).getContent());
        holder.tv_date.setText(timestampToString((Long)mdata.get(position).getTimestamp()));

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public CommentAdapter(Context mContext, List<Comment> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img_user;
        //ImageView img_user;
        TextView tv_name,tv_content,tv_date;

        public CommentViewHolder(View itemView){
            super(itemView);
            img_user = itemView.findViewById(R.id.comment_user_img);
            tv_name = itemView.findViewById(R.id.comment_username);
            tv_content = itemView.findViewById(R.id.comment_content);
            tv_date = itemView.findViewById(R.id.comment_date);
        }
    }
    private String timestampToString(long time)
    {
        Date d = new Date(time);
        DateFormat f = new SimpleDateFormat("hh:mm");
        return (f.format(d));
    }
}
