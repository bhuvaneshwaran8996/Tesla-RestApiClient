package com.example.tesla_restapiclient.controls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class EditTextRegular extends EditText {

    public EditTextRegular(Context context) {
        super(context);
        init(null);
    }

    public EditTextRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EditTextRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public EditTextRegular(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void init(AttributeSet attributeSet){
        this.setTypeface(Typeface.createFromAsset(getResources().getAssets(),"fonts/Heebo-Regular.ttf"));
    }
}
