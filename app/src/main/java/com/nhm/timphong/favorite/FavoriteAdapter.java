package com.nhm.timphong.favorite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nhm.timphong.R;
import com.nhm.timphong.data.HomeViewData;

import java.util.List;


public class FavoriteAdapter extends BaseAdapter {
    private List<HomeViewData> mItemList;
    private Context mContext;
    private LayoutInflater mInflater;
    private int mLayout;

    // Provide a suitable constructor (depends on the kind of dataset)
    public FavoriteAdapter(List<HomeViewData> itemList) {
        this.mItemList = itemList;
        //  ((Sample2Binder) getDataBinder(SampleViewType.SAMPLE2)).addAll(dataSet);
    }

    public FavoriteAdapter(Context context, int layout, List<HomeViewData> itemList) {
        this.mItemList = itemList;
        this.mLayout = layout;
        this.mContext = context;
        this.mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        FavoriteViewHolder viewHolder;

        // 캐시된 뷰가 없을 경우 새로 생성하고 뷰홀더를 생성한다
        if (convertView == null) {
            convertView = mInflater.inflate(mLayout, parent, false);
            viewHolder = new FavoriteViewHolder();

            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            viewHolder.txtLocate = (TextView) convertView.findViewById(R.id.txtLocate);
            viewHolder.txtLocateDetail = (TextView) convertView.findViewById(R.id.txtLocateDetail);
            viewHolder.ivType = (ImageView) convertView.findViewById(R.id.ivType);
            viewHolder.listItem = (LinearLayout) convertView.findViewById(R.id.listItem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FavoriteViewHolder) convertView.getTag();
        }
        //viewHolder.txtRank.setText(String.valueOf(position+1));
        viewHolder.txtTitle.setText(mItemList.get( position).getTitle());
        viewHolder.txtLocate.setText(mItemList.get(position).getLocation());
        viewHolder.txtLocateDetail.setText(mItemList.get(position).getLocation());
        viewHolder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FavoriteViewActivity.class);
                intent.putExtra("viewObject", mItemList.get(position));
                 mContext.startActivity(intent);
            }
        });
        return convertView;
    }
}
