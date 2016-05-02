package com.example.admin.conversation.tools;

import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechSynthesizer;

/**
 * Created by admin on 2016/5/2.
 */
public class Speak {
    //说话内容
    private String content;
    private Context context;
    // 语音合成对象
    private SpeechSynthesizer mTts;

    // 默认发音人
    private String voicer = "xiaoqi";

    public Speak(String content,String voicer,Context context) {
        this.content = content;
        this.context=context;
        this.voicer=voicer;
    }
    public void talk(){
        // 初始化合成对象
        SpeechSynthesizer mtts=SpeechSynthesizer.createSynthesizer(context,null);
        mtts.setParameter(SpeechConstant.VOICE_NAME,voicer);//发音人对象
        mtts.setParameter(SpeechConstant.SPEED,"60");//语速
        mtts.setParameter(SpeechConstant.PITCH, "60");//音调
        mtts.setParameter(SpeechConstant.VOLUME,"80");//音量
        mtts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);//设置为云端合成
        mtts.startSpeaking(content,null);

    }

}
