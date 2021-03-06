package com.example.tesla_restapiclient.controls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TextViewBlack extends AppCompatTextView {


    public TextViewBlack(Context context) {
        super(context);
        init(null);
    }

    public TextViewBlack(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TextViewBlack(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attributeSet){
        this.setTypeface(Typeface.createFromAsset(getResources().getAssets(),"fonts/Heebo-Black.ttf"));
    }
}
