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

public class professionalListAdapter extends ArrayAdapter<professionalData> {

    private static final String TAG = "ProfessionalListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    public professionalListAdapter(Context context, int resource, ArrayList<professionalData> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        setupImageLoader();
        String name = getItem(position).getName();
        String lastMessage = getItem(position).getRecentMessage();
        String imgUrl = getItem(position).getImgUrl();
        String status = getItem(position).getActive();


        final View result;

        professionalListAdapter.ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new professionalListAdapter.ViewHolder();

            holder.name = convertView.findViewById(R.id.patientName);
            holder.recentMessage = convertView.findViewById(R.id.recentMessage);
            holder.img = convertView.findViewById(R.id.image);
            holder.status = convertView.findViewById(R.id.status);

            result = convertView;

            convertView.setTag(holder);

        }
        else{
            holder = (professionalListAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

        ImageLoader imageLoader = ImageLoader.getInstance();

        int defaultImg = mContext.getResources().getIdentifier("@drawable/patient", null, mContext.getPackageName());
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImg)
                .showImageOnFail(defaultImg)
                .showImageOnLoading(defaultImg).build();

        imageLoader.displayImage(imgUrl, holder.img, options);

        holder.name.setText(name);
        holder.recentMessage.setText(lastMessage);
        if(status.equals("Active")){
            holder.status.setText("Online");
            holder.status.setTextColor(Color.parseColor("#00FF00"));
        }
        else{
            holder.status.setText(status);
            holder.status.setTextColor(Color.GRAY);
        }

        return convertView;

    }

    private void setupImageLoader(){
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }

    private static class ViewHolder{
        TextView name;
        TextView recentMessage;
        TextView status;
        ImageView img;
    }
}
