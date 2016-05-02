package com.example.admin.conversation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.conversation.dao.FriendImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/5/1.
 */
public class FragmentTwo extends Fragment {

    List<FriendImage> list_friend;
    List<FriendImage> list_star;
    List<FriendImage> list_head;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one, null);
        ListView lv = (ListView) v.findViewById(R.id.ll_friends);
        list_friend = new ArrayList<FriendImage>();
        list_star = new ArrayList<FriendImage>();
        list_head = new ArrayList<FriendImage>();
        FriendImage f1 = new FriendImage(R.mipmap.tex_1, "定制朋友");
        FriendImage f2 = new FriendImage(R.mipmap.hero_01, "发音设置");
        FriendImage f3 = new FriendImage(R.mipmap.hero_02, "标签");
        FriendImage f4 = new FriendImage(R.mipmap.hero_03, "待添加..");
        FriendImage fs1 = new FriendImage(R.mipmap.pre1, "小丽");
        FriendImage fs2 = new FriendImage(R.mipmap.pre2, "小琪.");
        FriendImage fs3 = new FriendImage(R.mipmap.pre3, "小蓉.");
        FriendImage fs4 = new FriendImage(R.mipmap.pre4, "小芸");
        FriendImage fs5 = new FriendImage(R.mipmap.pre5, "catherine");
        FriendImage fs6 = new FriendImage(R.mipmap.pre6, "小英");
        FriendImage fs7 = new FriendImage(R.mipmap.pre7, "楠楠");
        FriendImage fs8 = new FriendImage(R.mipmap.pre8, "图灵");
        list_head.add(f1);
        list_head.add(f2);
        list_head.add(f3);
        list_head.add(f4);
        list_star.add(fs1);
        list_star.add(fs2);
        list_friend.add(fs3);
        list_friend.add(fs4);
        list_friend.add(fs5);
        list_friend.add(fs6);
        list_friend.add(fs7);
        list_friend.add(fs8);
        lv.setAdapter(new madapter());
        return v;

    }

    class madapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list_friend.size() + list_star.size() + list_head.size() + 2;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v=null;
            int h = list_head.size();
            int s = list_star.size();
            int f = list_friend.size();
            if (position < h) {
                v = LayoutInflater.from(getActivity()).inflate(R.layout.friendlist, null);
                ImageView iv = (ImageView) v.findViewById(R.id.iv_fl);
                TextView tv = (TextView) v.findViewById(R.id.tv_fl);
                iv.setImageResource(list_head.get(position).getImageSrc());
                tv.setText(list_head.get(position).getName());
            } else if (position == h) {
                v = LayoutInflater.from(getActivity()).inflate(R.layout.layout_null, null);

            } else if (position > h && position < h + s + 1) {
                v = LayoutInflater.from(getActivity()).inflate(R.layout.friendlist, null);
                ImageView iv = (ImageView) v.findViewById(R.id.iv_fl);
                TextView tv = (TextView) v.findViewById(R.id.tv_fl);
                iv.setImageResource(list_star.get(position - h - 1).getImageSrc());
                tv.setText(list_star.get(position - h - 1).getName());
            } else if (position == h + s + 1) {
                v = LayoutInflater.from(getActivity()).inflate(R.layout.layout_null, null);
                TextView tv = (TextView) v.findViewById(R.id.tv_star);
                tv.setText("普通朋友");
            } else if (position > h+s+1&& position < h + s + f+2) {
                v = LayoutInflater.from(getActivity()).inflate(R.layout.friendlist, null);
                ImageView iv = (ImageView) v.findViewById(R.id.iv_fl);
                TextView tv = (TextView) v.findViewById(R.id.tv_fl);
                iv.setImageResource(list_friend.get(position - h-s - 2).getImageSrc());
                tv.setText(list_friend.get(position - h-s - 2).getName());
            }
            return v;
        }
    }
}
