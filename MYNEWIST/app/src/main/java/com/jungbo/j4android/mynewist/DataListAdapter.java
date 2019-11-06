package com.jungbo.j4android.mynewist;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DataListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context context;
    private List<Data> dataList;
    private List<Data> saveList;
    private Activity parentActivity;
    //private ArrayList<CardViewitemDTO> cardViewitemDTOS=new ArrayList<>();

    public DataListAdapter(Context context, List<Data> userList, Activity parentActivity, List<Data> saveList)
    {
        this.context=context;
        this.dataList=userList;
        this.parentActivity=parentActivity;
        this.saveList=saveList;
        //cardViewitemDTOS.add(new CardViewitemDTO(R.drawable.data1,"첫번째","풍경1"));
       // cardViewitemDTOS.add(new CardViewitemDTO(R.drawable.data2,"두번째","풍경2"));
       // cardViewitemDTOS.add(new CardViewitemDTO(R.drawable.data3,"세번째","풍경3"));

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item,parent,false);
        return new RowCell(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RowCell)holder).imageView.setImageResource(R.drawable.data3);
        ((RowCell)holder).title.setText(dataList.get(position).userID);
        ((RowCell)holder).subtitle.setText(dataList.get(position).styleName);
        ((RowCell)holder).comment.setText(dataList.get(position).comment);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private class RowCell extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView subtitle;
        public TextView comment;
        public RowCell(View view) {
            super(view);
            imageView=(ImageView)view.findViewById(R.id.cardview_imageview);
            title=(TextView) view.findViewById(R.id.cardview_title);
            subtitle=(TextView)view.findViewById(R.id.cardview_subtitle);
            comment=(TextView)view.findViewById(R.id.cardview_comment);
        }
    }






}
