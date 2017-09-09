package com.houalef.insta.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.houalef.insta.InstaApplication;
import com.houalef.insta.R;
import com.houalef.insta.model.GridItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mehdi Houalef on 24/01/2017.
 */


public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder> {

    private List<GridItem> dataList = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(GridItem item);
    }

    public GridAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<GridItem> dataList) {
        this.dataList = dataList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public NetworkImageView img;
        public TextView tv;

        public MyViewHolder(View view, int type) {
            super(view);

            switch (type) {

                case 0:
                    tv = (TextView) view.findViewById(R.id.tv);
                    break;

                case 1:
                    img = (NetworkImageView) view.findViewById(R.id.niv);
                    break;


            }

        }

        public void bind(final Integer position, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(dataList.get(position));
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        GridItem item = dataList.get(position);

        switch (getItemViewType(position)) {

            case 0: // header
                holder.tv.setText(item.getTitle());
                break;

            case 1: // img
                holder.img.setDefaultImageResId(R.drawable.placeholder);
                holder.img.setImageUrl(item.getSocialPicture().getThumbnail(), InstaApplication.getImageLoader());

                holder.bind(position, listener);
//                holder.itemView.startAnimation(AnimationUtils.loadAnimation(InstaApplication.getAppContext(), R.anim.fade_in_500));
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (dataList != null)
            return dataList.size();
        else
            return 0;
    }

    @Override
    public int getItemViewType(int position) {
        // return type depending on position
        return dataList.get(position).getType();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        MyViewHolder mvh = null;

        switch (viewType) {

            case 0:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_title, parent, false);
                break;

            case 1:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pics, parent, false);
                int width = parent.getMeasuredWidth();
                int height = width / 3;
                itemView.setMinimumHeight(height);
                itemView.findViewById(R.id.niv).setLayoutParams(new RelativeLayout.LayoutParams(height, height));
                break;
        }

        mvh = new MyViewHolder(itemView, viewType);


        return mvh;
    }

    @Override
    public void onViewRecycled(MyViewHolder holder) {
        super.onViewRecycled(holder);
//        holder.itemView.startAnimation(AnimationUtils.loadAnimation(InstaApplication.getAppContext(), R.anim.fade_in_500));
    }
}
