package brad.tw.mythreadtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test1(View v){
        MyThread mt1 = new MyThread();
        mt1.start();
//        mt1.start();
        mt1.run();
        mt1.run();
        mt1.run();
    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            for (int i=0; i<20; i++){
                Log.d("brad", "i = " + i);
            }

        }
    }


}
