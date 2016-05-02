package com.example.admin.conversation.tools;

import com.example.admin.conversation.dao.JsonBean;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by admin on 2016/5/2.
 */
public class JsonParse {

    public static String parse(String args) {
        Gson gson=new Gson();
        JsonBean jsonBean=gson.fromJson(args,JsonBean.class);
        StringBuffer sb=new StringBuffer();
        ArrayList<JsonBean.WSBean> ws=jsonBean.ws;
        for (JsonBean.WSBean wsBean:ws){
            String word=wsBean.cw.get(0).w;
            sb.append(word);
        }

      return sb.toString();
    }
}
