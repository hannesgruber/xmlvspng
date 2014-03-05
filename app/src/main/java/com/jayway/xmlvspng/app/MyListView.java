package com.jayway.xmlvspng.app;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

public class MyListView extends ListView {

    private final FrameRateMeasurer frm = new FrameRateMeasurer("ListView", 100);

    public MyListView(Context context, AttributeSet attrs){
        super(context,attrs);
        Log.d("Hej", "MyListView Constructor2!");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        frm.startMeasuring();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        frm.stopMeasuring();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        frm.measureFrame(getContext());
    }
}