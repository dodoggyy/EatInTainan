package com.example.luckycookies;

import java.util.ArrayList;

import android.content.Context;
import android.widget.ArrayAdapter;

public class CircularArrayAdapter extends ArrayAdapter{
	public static final int HALF_MAX_VALUE = Integer.MAX_VALUE/2;
    public final int MIDDLE;
    private String[] objects;
    private ArrayList<String> objects2;
    public CircularArrayAdapter(Context context, int textViewResourceId, String[] objects)
    {
        super(context, textViewResourceId, objects);
        this.objects = objects;
        MIDDLE = HALF_MAX_VALUE - HALF_MAX_VALUE % objects.length;
    }
    
    public CircularArrayAdapter(Context context, int textViewResourceId, ArrayList<String> objects)
    {
        super(context, textViewResourceId, objects);
        this.objects2 = objects;
        MIDDLE = HALF_MAX_VALUE - HALF_MAX_VALUE % objects.size();
    }
    
    @Override
    public int getCount()
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public String getItem(int position) 
    {
        return objects[position % objects.length];
    }
    
    
}
