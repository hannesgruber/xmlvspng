package com.jayway.xmlvspng.app;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class ListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        MyListView list = (MyListView) findViewById(R.id.list);

        String[] values = new String[1000];
        for(int i = 0; i < values.length; i++){
            values[i] = "" + i;
        }

        list.setAdapter(new MyAdapter(this, values));
    }


    private class MyAdapter extends ArrayAdapter<String> {

        private final String[] values;
        LayoutInflater inflater;
        public MyAdapter(Context context, String[] values){
            super(context, R.layout.list_item, values);
            this.values = values;

            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if(convertView == null){
                convertView = inflater.inflate(R.layout.list_item, parent, false);
            }

            TextView text = (TextView)convertView.findViewById(R.id.text);
            Button button = (Button)convertView.findViewById(R.id.button);
            RadioButton radioButton = (RadioButton)convertView.findViewById(R.id.radioButton);

            text.setText(values[position]);

            double rand = Math.random();
            if(rand < 0.33){
                button.setBackgroundResource(R.drawable.button1_bg_selector);
            } else if(rand < 0.66){
                button.setBackgroundResource(R.drawable.button2_bg_selector);
            } else {
                button.setBackgroundResource(R.drawable.button3_bg_selector);
            }

            button.setEnabled(Math.random() > 0.5);

            rand = Math.random();
            if(rand < 0.15){
                radioButton.setButtonDrawable(R.drawable.radiobutton_blue_selector);
            } else if(rand < 0.3){
                radioButton.setButtonDrawable(R.drawable.radiobutton_green_selector);
            } else if(rand < 0.45){
                radioButton.setButtonDrawable(R.drawable.radiobutton_grey_selector);
            } else if(rand < 0.6){
                radioButton.setButtonDrawable(R.drawable.radiobutton_orange_selector);
            } else if(rand < 0.75){
                radioButton.setButtonDrawable(R.drawable.radiobutton_red_selector);
            } else {
                radioButton.setButtonDrawable(R.drawable.radiobutton_yellow_selector);
            }

            return convertView;
        }
    }




}
