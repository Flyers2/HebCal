package com.example.fly.hebcal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Fly on 7/28/2014.
 */
public class HebcalAdapter extends ArrayAdapter<Dates>{

    private  int resource;

    public HebcalAdapter(Context context, int resource) {
        super(context, resource);
        this.resource = resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View row = inflater.inflate(resource,null,true);

        TextView textViewDate = (TextView)row.findViewById(R.id.date);
        TextView textViewTitle = (TextView)row.findViewById(R.id.title);
        ImageView picture = (ImageView)row.findViewById(R.id.thePic);
        Dates date = getItem(position);
        Log.v("inadapter",date.getTitle() );
        textViewTitle.setText(date.getTitle());
        textViewDate.setText(date.getDate());


        int picToDisplay;
        if(date.getCategory().equalsIgnoreCase("candles")){
            picToDisplay=R.drawable.candles;
        }else if(date.getCategory().equalsIgnoreCase("parashat")){
            picToDisplay=R.drawable.torah;
        }else{
            picToDisplay=R.drawable.holiday;
        }
        picture.setImageResource(picToDisplay);


        return row;
    }
}
