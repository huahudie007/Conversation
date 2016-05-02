package com.example.admin.conversation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.admin.conversation.adapter.ConversationAdapter;
import com.example.admin.conversation.dao.ConversationBean;
import com.example.admin.conversation.tools.HttpData;
import com.example.admin.conversation.tools.HttpGetDataListener;
import com.example.admin.conversation.tools.JsonParse;
import com.example.admin.conversation.tools.Speak;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Conversation extends AppCompatActivity implements View.OnClickListener, HttpGetDataListener {
//4个控件
    private ListView lv;
    private EditText sendtext;
    private Button send_btn;
    private Button send_voice;
    //会话数据
    private ArrayList<ConversationBean> lists;
    private ConversationAdapter adapter;

    private HttpData httpData;//异步联网会话
    private String msg;//发给机器人的话
    StringBuffer mBuffer = new StringBuffer();// 语音识别
    //传递过来的头像和发音人
    private String name;
    private int pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5724c719");
        name = getIntent().getStringExtra("name");
        pic = getIntent().getIntExtra("pic", R.mipmap.pre2);
        initView();
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.ll_conversation);
        sendtext = (EditText) findViewById(R.id.et_send);
        send_btn = (Button) findViewById(R.id.btn_send);
        send_voice = (Button) findViewById(R.id.btn_voice);
        lists = new ArrayList<ConversationBean>();
        send_btn.setOnClickListener(this);
        send_voice.setOnClickListener(this);
        adapter = new ConversationAdapter(lists, this,pic);
        lv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:

                String content_str = sendtext.getText().toString();
                msgChar(content_str);
                msgSend();
                break;
            case R.id.btn_voice:
                send_voice();
                break;
        }


    }

    private void send_voice() {
        RecognizerDialog mIat = new RecognizerDialog(this, null);
        // 2.设置听写参数，详见《科大讯飞MSC API手册(Android)》SpeechConstant类
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");

        // 3.开始听写
        mIat.setListener(mRecognizerDialogListener);
        mIat.show();
    }
    private RecognizerDialogListener mRecognizerDialogListener=new RecognizerDialogListener() {
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            String parseResult = JsonParse.parse(recognizerResult.getResultString());
            mBuffer.append(parseResult);
            if (b) {
                // 会话已经结束
                String finalResult = mBuffer.toString();
                msgChar(finalResult);
                msgSend();
                mBuffer = new StringBuffer();// 此方法相当于清空buffer
            }

        }

        @Override
        public void onError(SpeechError speechError) {

        }
    };

    /**
     * 发送消息
     */
    private void msgSend() {
        JSONObject json = new JSONObject();
        try {
            json.put("key", "92ab2b5158435dc4050bbed52f5467a5");
            json.put("info", msg);
            json.put("loc", "天津");
            json.put("userid", "19864211");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpData = (HttpData) new HttpData(json.toString(), this).execute();
    }

    /**
     * 获取文字消息， 重置list
     */
    private void msgChar(String str) {
        ConversationBean data = new ConversationBean(str, ConversationBean.SEND, getTime());
        lists.add(data);
        sendtext.setText("");
        String dropk = str.replace(" ", "");
        msg = dropk.replace("\n", "");
        adapter.notifyDataSetChanged();
    }

    private String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String str = format.format(System.currentTimeMillis());
        return str;
    }

    @Override
    public void getDataUrl(String data) {
        try {
            if (data != null) {
                JSONObject obj = new JSONObject(data);
                String receiver=obj.getString("text");
                ConversationBean cd = new ConversationBean(receiver, ConversationBean.RECEIVER, getTime());
                lists.add(cd);
                adapter.notifyDataSetChanged();
                new Speak(receiver,name,this).talk();//开始说话

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
