package com.example.allay;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

public class dreamListAdapter extends ArrayAdapter<dreamData> {

    private static final String TAG = "DreamListAdapter";
    private Context mContext;
    private int mResource;
    private int lastPosition = -1;
    public dreamListAdapter(Context context, int resource, ArrayList<dreamData> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        String title = getItem(position).getTitle();
        String story = getItem(position).getStory();

        final View result;

        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new dreamListAdapter.ViewHolder();

            holder.title = convertView.findViewById(R.id.dreamTitle);
            holder.story = convertView.findViewById(R.id.dreamStory);

            result = convertView;

            convertView.setTag(holder);

        }
        else{
            holder = (dreamListAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }


        holder.title.setText(title);
        holder.story.setText(story);

        return convertView;

    }

    private static class ViewHolder{
        TextView title;
        TextView story;
    }

}
