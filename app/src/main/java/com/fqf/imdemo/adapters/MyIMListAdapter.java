package com.fqf.imdemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fqf.imdemo.ClientItem;
import com.fqf.imdemo.IItem;
import com.fqf.imdemo.R;
import com.fqf.imdemo.ServerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 房庆丰 on 2016/11/12.
 */

public class MyIMListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<IItem>mList;

    public MyIMListAdapter(Context context) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        mList = new ArrayList<>();
    }

    public void addItem(IItem item) {
        this.mList.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolderClient clientHolder = null;
        ViewHolderServer serverHolder = null;
        if (convertView == null) {
            switch (type) {
                case IItem.CLIENT:
                    convertView = mInflater.inflate(R.layout.client_item, parent, false);
                    clientHolder = new ViewHolderClient();
                    clientHolder.mTextView = (TextView) convertView.findViewById(R.id.textView_client);
                    convertView.setTag(clientHolder);
                    break;
                case IItem.SERVER:
                    convertView = mInflater.inflate(R.layout.server_item, parent, false);
                    serverHolder = new ViewHolderServer();
                    serverHolder.mTextView = (TextView) convertView.findViewById(R.id.textView_server);
                    convertView.setTag(serverHolder);
                    break;
            }
        } else {
            switch (type) {
                case IItem.CLIENT:
                    clientHolder = (ViewHolderClient) convertView.getTag();
                    break;
                case IItem.SERVER:
                    serverHolder = (ViewHolderServer) convertView.getTag();
                    break;
            }
        }
        //赋值
        switch (type) {
            case IItem.CLIENT:
                ClientItem item = (ClientItem) mList.get(position);
                clientHolder.mTextView.setText(item.getInfo());
                break;
            case IItem.SERVER:
                ServerItem item1 = (ServerItem) mList.get(position);
                serverHolder.mTextView.setText(item1.getInfo());
                break;
        }
        return convertView;
    }

    static class ViewHolderClient {
        private ImageView mImageview;
        private TextView mTextView;
    }

    static class ViewHolderServer {
        private ImageView mImageView;
        private TextView mTextView;
    }
}
