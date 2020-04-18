package com.example.gmailusingrecycleview;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmailItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<EmailItemModel> items;

    public EmailItemAdapter(List<EmailItemModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new EmailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmailViewHolder viewHolder = (EmailViewHolder) holder;
        EmailItemModel item = items.get(position);
        viewHolder.textLetter.setText(item.getName().substring(0,1));
        Drawable background = viewHolder.textLetter.getBackground();
        background.setColorFilter(new PorterDuffColorFilter(item.getColor(), PorterDuff.Mode.SRC_ATOP));
        viewHolder.textName.setText(item.getName());
        viewHolder.textSubject.setText(item.getSubject()) ;
        viewHolder.textContent.setText(item.getContent());
        viewHolder.textTime.setText(item.getTime());
        if(item.isFavourite()) viewHolder.imageFavourite.setImageResource(R.drawable.ic_starfavourite);
        else viewHolder.imageFavourite.setImageResource(R.drawable.ic_star_unfavourite);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class EmailViewHolder extends RecyclerView.ViewHolder{
        TextView textLetter;
        TextView textName;
        TextView textSubject;
        TextView textContent;
        TextView textTime;
        ImageView imageFavourite;



        public EmailViewHolder(@NonNull View itemView) {
            super(itemView);

            textLetter = itemView.findViewById(R.id.text_letter);
            textName = itemView.findViewById(R.id.text_name);
            textSubject = itemView.findViewById(R.id.text_subject);
            textContent = itemView.findViewById(R.id.text_content);
            textTime = itemView.findViewById(R.id.text_time);
            imageFavourite = itemView.findViewById(R.id.image_favourite);

            imageFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isFavourite = items.get(getAdapterPosition()).isFavourite();
                    items.get(getAdapterPosition()).setFavourite(!isFavourite);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
