package com.nhm.timphong.homeview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhm.timphong.R;

import java.util.List;


public class HomeListAdapter extends BaseAdapter {
    private List<HomeListData> mItemList;
    private Context mContext;
    private LayoutInflater mInflater;
    private int mLayout;

    // Provide a suitable constructor (depends on the kind of dataset)
    public HomeListAdapter(List<HomeListData> itemList) {
        this.mItemList = itemList;
        //  ((Sample2Binder) getDataBinder(SampleViewType.SAMPLE2)).addAll(dataSet);
    }

    public HomeListAdapter(Context context, int layout, List<HomeListData> itemList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeListViewHolder viewHolder;

        // 캐시된 뷰가 없을 경우 새로 생성하고 뷰홀더를 생성한다
        if (convertView == null) {
            convertView = mInflater.inflate(mLayout, parent, false);
            viewHolder = new HomeListViewHolder();

            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            viewHolder.txtLocate = (TextView) convertView.findViewById(R.id.txtLocate);
            viewHolder.txtLocateDetail = (TextView) convertView.findViewById(R.id.txtLocateDetail);
            viewHolder.ivType = (ImageView) convertView.findViewById(R.id.ivType);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HomeListViewHolder) convertView.getTag();
        }
        //viewHolder.txtRank.setText(String.valueOf(position+1));
        viewHolder.txtTitle.setText(mItemList.get( position).getTitle());
        viewHolder.txtLocate.setText(mItemList.get(position).getLocate());
        viewHolder.txtLocateDetail.setText(mItemList.get(position).getLocateDetail());
        //viewHolder.ivType.setText(mItemList.get(position).getLink());
        /*viewHolder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(mContext, RankNewsListDetailActivity.class);
                //intent.putExtra("sermon_details", (android.os.Parcelable) mDataset.get(customHeaderView != null ? position - 1 : position));
                // mContext.startActivity(intent);
            }
        });*/
        return convertView;
    }
}
