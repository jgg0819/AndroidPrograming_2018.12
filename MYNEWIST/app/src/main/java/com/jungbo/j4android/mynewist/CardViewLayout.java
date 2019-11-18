package com.jungbo.j4android.mynewist;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CardViewLayout extends LinearLayout {

    ImageView imageView;
    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;


    public CardViewLayout(Context context) {
        super(context);
    }

    public CardViewLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Context context){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cardview_item,this,true);

        imageView=findViewById(R.id.cardview_imageview);
        textView=findViewById(R.id.cardview_title);
        textView1=findViewById(R.id.cardview_subtitle);
        textView2=findViewById(R.id.cardview_price);
        textView3=findViewById(R.id.cardview_comment);

    }

    public void setImage(int resId){
        imageView.setImageResource(resId);
    }

    public void setTitle(String title){
        textView.setText(title);
    }

    public void setSubTitle(String subTitle){
        textView1.setText(subTitle);
    }

    public void setPrice(String price){
        textView2.setText(price);
    }

    public void setComment(String comment){
        textView3.setText(comment);
    }

}
