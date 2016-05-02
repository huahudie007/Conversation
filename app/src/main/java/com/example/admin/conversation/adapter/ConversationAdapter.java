package com.example.admin.conversation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.conversation.R;
import com.example.admin.conversation.dao.ConversationBean;

import java.util.List;

/**
 * Created by admin on 2016/5/1.
 */
public class ConversationAdapter extends BaseAdapter{
    List<ConversationBean> listData;
    Context context;
    private RelativeLayout layout;
    private  int pic;

    public ConversationAdapter(List<ConversationBean> data, Context context ,int pic){
        this.context=context;
        this.listData=data;
        this.pic=pic;

    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        if (listData.get(position).getFlag()== ConversationBean.RECEIVER){
            layout = (RelativeLayout) inflater.inflate(R.layout.left_item, null);
            ImageView imageView= (ImageView) layout.findViewById(R.id.iv);
            imageView.setImageResource(pic);
        }else if(listData.get(position).getFlag()== ConversationBean.SEND){
            layout = (RelativeLayout) inflater.inflate(R.layout.right_item, null);
        }
        TextView tv= (TextView) layout.findViewById(R.id.tv);
        TextView time= (TextView) layout.findViewById(R.id.time);
        tv.setText(listData.get(position).getContent());
        time.setText(listData.get(position).getTime());
        return layout;
    }
}
