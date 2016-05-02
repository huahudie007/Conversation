package com.example.admin.conversation.dao;

import java.util.ArrayList;

/**
 * Created by admin on 2016/5/2.
 */
public class JsonBean {
    public ArrayList<WSBean> ws;

    public class WSBean {
        public ArrayList<CWBean> cw;
    }

    public class CWBean {
        public String w;
    }
}
