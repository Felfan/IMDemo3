package com.fqf.imdemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.fqf.imdemo.adapters.MyIMListAdapter;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;


public class MainActivity extends Activity {
    //对方的name
    private String name = "kefu";
    private String text;
    private ListView mListView;
    private EditText mEditText;
    private MyIMListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intent = this.getIntent();
       // name = intent.getStringExtra("name");
        init();
        mEditText = (EditText) this.findViewById(R.id.editText_IM);
        mListView = (ListView) this.findViewById(R.id.listView_IM);
        mAdapter = new MyIMListAdapter(this);
        mListView.setAdapter(mAdapter);
        //进入会话，状态栏不再进行展示
        JMessageClient.enterSingleConversation(name, null);
        createThread();
    }

    private void createThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isForeground) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getInfo();
                        }
                    });
                }
            }
        }).start();

    }

    public void back(View view) {
        //调用exitConversation之后，将恢复对应的通知栏提示
        JMessageClient.exitConversation();
        this.finish();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView1_IM:
               //getInfo();
                break;
            case R.id.imageView2_IM:
                break;
            case R.id.imageView3_IM:
                text = mEditText.getText().toString();
                if (TextUtils.isEmpty(text)) {
                    //Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                Message message = JMessageClient.createSingleTextMessage(name, null, text);
                message.setOnSendCompleteCallback(new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i == 0) {
                            //Log.i("flag", "JMessageClient.createSingleTextMessage" + ", responseCode = " + i + " ; LoginDesc = " + s);
                            //Toast.makeText(getApplicationContext(), "发送成功", Toast.LENGTH_SHORT).show();
                            mEditText.setText("");
                            ClientItem item = new ClientItem();
                            item.setInfo(text);
                            mAdapter.addItem(item);
                        } else {
                           // Log.i("flag", "JMessageClient.createSingleTextMessage" + ", responseCode = " + i + " ; LoginDesc = " + s);
                            Toast.makeText(getApplicationContext(), "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //发送动作建议放在callback之后
                JMessageClient.sendMessage(message);
                break;
            case R.id.editText_IM:
                //调用手机键盘
                break;
        }
    }


    public static boolean isForeground = false;
    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    private void init(){
        JPushInterface.init(getApplicationContext());
    }


    @Override
    protected void onResume() {
        isForeground = true;
        JPushInterface.onResume(this);
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        JPushInterface.onPause(this);
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private int lastId = 0;
    public void getInfo() {
        Conversation conversation = JMessageClient.getSingleConversation("kefu", null);
        if (conversation == null) return;
        /**=================     获取会话中的属性    =================*/
        Message latestMessage = conversation.getLatestMessage();
        int id = latestMessage.getId();
        if (lastId == id) {
            return;
        }
        if (latestMessage != null) {
            if (!latestMessage.getFromUser().getUserName().equals("kefu")) {
                return;
            }
            MessageContent content = latestMessage.getContent();
            if (content.getContentType() == ContentType.text) {
                TextContent stringExtra = (TextContent) content;
                //stringExtra.getText()---->得到发送过来的内容
                ServerItem item = new ServerItem();
                item.setInfo(stringExtra.getText());
                mAdapter.addItem(item);
            }
        }
        lastId = id;
    }

}
