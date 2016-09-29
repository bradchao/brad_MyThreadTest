package brad.tw.mythreadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private MyHandler handler;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();
        handler = new MyHandler();
        tv = (TextView)findViewById(R.id.tv);

    }

    public void test1(View v){
        MyThread mt1 = new MyThread("A");
        MyThread mt2 = new MyThread("B");

        MyRunnable mr1 = new MyRunnable("C");
        Thread tr1 = new Thread(mr1);

        mt1.start();
        //mt2.start();
        //tr1.start();


        //mt1.start();
//        mt1.start();
//        mt1.run();
//        mt1.run();
    }

    public void test2(View v){
        MyTask mt1 = new MyTask();
        timer.schedule(mt1, 3*1000, 1*1000);
    }

    private class MyTask extends TimerTask {
        int i;
        @Override
        public void run() {
            Log.d("brad", "i = " + i++);
        }
    }


    private class MyThread extends Thread {
        String name;
        MyThread(String name){this.name = name;}
        @Override
        public void run() {
            for (int i=0; i<20; i++){
                Log.d("brad", name + ":" + "i = " + i);

                //tv.setText(name + ":" + "i = " + i);
                Message mesg = new Message();
                Bundle data = new Bundle();
                data.putString("key",name + ":" + "i = " + i);
                mesg.setData(data);
                handler.sendMessage(mesg);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }

        }
    }

    private class MyRunnable implements Runnable {
        String name;
        MyRunnable(String name){this.name = name;}
        @Override
        public void run() {
            for (int i=0; i<20; i++){
                Log.d("brad", name + ":" + "i = " + i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }

        }
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String mesg = msg.getData().getString("key");

            tv.setText(mesg);
        }
    }

}
