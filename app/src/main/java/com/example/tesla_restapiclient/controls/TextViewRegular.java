package com.example.tesla_restapiclient.controls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import org.w3c.dom.Attr;

public class TextViewRegular extends AppCompatTextView {


    public TextViewRegular(Context context) {
        super(context);
        init(null);
    }

    public TextViewRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TextViewRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }


    public void init(AttributeSet attributeSet){
        this.setTypeface(Typeface.createFromAsset(getResources().getAssets(),"fonts/ComicNeue-Light.ttf"));
    }
}
