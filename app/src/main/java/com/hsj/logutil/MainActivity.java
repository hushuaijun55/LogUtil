package com.hsj.logutil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * 短文本打印调用方法
     * @param view
     */
    public void shortText(View view) {
        HLogger.d("这是一条测试信息。这是一条测试信息");
    }

    /**
     * 长文本打印调用方法
     * @param view
     */
    public void longText(View view) {
        HLogger.longInfo("545345343132121102045402.102135435412.121234564651313210210214541321shksjadhsdhaskjhdaskfhcask" +
                "dhaskdhakuhdoiauwdhaksncxkajsjhdkajshdaksjshdaksdhaskdhaskjdhaskjhdaksjhdaskjhdkahsfgbdajsfcnkjdanc," +
                "dsnbcsdhjkfgvsdkjhfhgaksudhaouisdqodhaksdnbcaskjcbnkadsfhdksafhgiudshgfsdkjfnakcbnkajsdhaksufghakfhdasjkfsakjdhfudhaoui" +
                "sdqodhaksdnbcaskjcbnkadsfhdksafhgiudshgfsdkjfnakcbnkajsdhaksufghakfhdasjkfsakjdhf" +
                "udhaouisdqodhaksdnbcaskjcbnkadsfhdksafhgiudshgfsdkjfnakcbnkajsdhaksufghakfhdasjkfsakjdhf" +
                "udhaouisdqodhaksdnbcaskjcbnkadsfhdksafhgiudshgfsdkjfnakcbnkajsdhaksufghakfhdasjkfsakjdhf" +
                "udhaouisdqodhaksdnbcaskjcbnkadsfhdksafhgiudshgfsdkjfnakcbnkajsdhaksufghakfhdasjkfsakjdhf");

    }


}
