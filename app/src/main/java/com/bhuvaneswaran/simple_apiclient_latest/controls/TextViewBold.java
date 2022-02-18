package com.bhuvaneswaran.simple_apiclient_latest.controls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TextViewBold extends AppCompatTextView {


    public TextViewBold(Context context) {
        super(context);

        init(null);
    }

    public TextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public TextViewBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attributeSet){

        this.setTypeface(Typeface.createFromAsset(getResources().getAssets(),"fonts/Heebo-Bold.ttf"));
    }

}
