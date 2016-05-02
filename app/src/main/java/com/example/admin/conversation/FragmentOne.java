package com.example.admin.conversation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.conversation.dao.Friend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/5/1.
 */
public class FragmentOne extends Fragment {

    private List<Friend> list;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View V= inflater.inflate(R.layout.fragment_one,null);
        ListView ll = (ListView) V.findViewById(R.id.ll_friends);
        list = new ArrayList<>();
        Friend friend=new Friend(R.mipmap.pre8,"图灵","今天吃什么呢？","2016年5月1日" ,"xiaoyan");
        Friend friend2=new Friend(R.mipmap.pre2,"永远的17岁","今天吃什么呢？","2016年5月1日" ,"xiaoqi");
        Friend friend3=new Friend(R.mipmap.pre3,"小蓉（四川）","今天吃什么呢？","2016年5月1日" ,"xiaorong");
        Friend friend4=new Friend(R.mipmap.pre4,"小芸(东北)","今天吃什么呢？","2016年5月1日" ,"xiaoqian");
        Friend friend5=new Friend(R.mipmap.pre5,"catherine","What's for my dinner today?","2016年5月1日" ,"catherine");
        Friend friend6=new Friend(R.mipmap.pre6,"小莹（陕西）","今天吃什么呢？","2016年5月1日" ,"vixying");
        Friend friend7=new Friend(R.mipmap.pre7,"楠楠","今天吃什么呢？","2016年5月1日" ,"nannan");
        Friend friend8=new Friend(R.mipmap.pre1,"小莉(台湾) ","今天吃什么呢？","2016年5月1日" ,"xiaolin");
        list.add(friend);
        list.add(friend2);
        list.add(friend3);
        list.add(friend4);
        list.add(friend5);
        list.add(friend6);
        list.add(friend7);
        list.add(friend8);
        ll.setAdapter(new mAdapter());
        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),Conversation.class);
                intent.putExtra("name",list.get(position).getNickname());
                intent.putExtra("pic",list.get(position).getImgSrc());
                startActivity(intent);
            }
        });
        return V;
    }
    class mAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v;
            if(convertView==null){
            v=LayoutInflater.from(getActivity()).inflate(R.layout.vp_talk,null);

            }else {
                v=convertView;
            }
            ImageView iv= (ImageView) v.findViewById(R.id.iv_talk);
            TextView tv_name= (TextView) v.findViewById(R.id.tv_name);
            TextView tv_time= (TextView) v.findViewById(R.id.tv_time);
            TextView tv_conversation= (TextView) v.findViewById(R.id.tv_conversation);
            Friend f=list.get(position);
            iv.setImageResource(f.getImgSrc());
            tv_name.setText(f.getName());
            tv_time.setText(f.getTime());
            tv_conversation.setText(f.getTalkContent());
            return v;
        }
    }
}
