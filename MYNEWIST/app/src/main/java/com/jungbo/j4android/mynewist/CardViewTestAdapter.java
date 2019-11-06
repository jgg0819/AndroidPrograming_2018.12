package com.jungbo.j4android.mynewist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CardViewTestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    private ArrayList<CardViewitemDTO> cardViewitemDTOs=new ArrayList<>();

    public CardViewTestAdapter() {
        cardViewitemDTOs.add(new CardViewitemDTO(R.drawable.cloths,"첫번쨰","풍경1"));
        cardViewitemDTOs.add(new CardViewitemDTO(R.drawable.juj,"두번쨰","풍경2"));
        cardViewitemDTOs.add(new CardViewitemDTO(R.drawable.man,"세번쨰","풍경3"));
    }


    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //xml세팅
        View view=LayoutInflater .from(parent.getContext()).inflate(R.layout.cardview_item,parent,false);
        return new RowCell(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //이미지 셋팅
        ((RowCell)holder).imageView.setImageResource(cardViewitemDTOs.get(position).imageview);
        ((RowCell)holder).title.setText(cardViewitemDTOs.get(position).title);
        ((RowCell)holder).subtitle.setText(cardViewitemDTOs.get(position).subtitle);
    }

    @Override
    public int getItemCount() {
        //이미지 카운터
        return cardViewitemDTOs.size();
    }

    private class RowCell extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView subtitle;
        public RowCell(View view) {
            super(view);
            imageView=(ImageView)view.findViewById(R.id.cardview_imageview);
            title=(TextView) view.findViewById(R.id.cardview_title);
            subtitle=(TextView)view.findViewById(R.id.cardview_subtitle);
        }
    }
}
