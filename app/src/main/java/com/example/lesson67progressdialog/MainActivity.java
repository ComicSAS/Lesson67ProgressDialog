package com.example.lesson67progressdialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pd;
    Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("HandlerLeak")
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnDefault:
                pd = new ProgressDialog(this);
                pd.setTitle("Title");
                pd.setMessage("Message");
                // добавляем кнопку
                pd.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                pd.setIndeterminate(true);
                pd.show();
                break;
            case R.id.btnHoriz:
                pd = new ProgressDialog(this);
                pd.setTitle("Title");
                pd.setMessage("Message");
                // меняем стиль на индикатор
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                // устанавливаем максимум
                pd.setMax(2048);
                // включаем анимацию ожидания
                pd.setIndeterminate(true);
                pd.show();
                h = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        // выключаем анимацию ожидания
                        pd.setIndeterminate(false);
                        if (pd.getProgress() < pd.getMax()) {
                            // увеличиваем значения индикаторов
                            pd.incrementProgressBy(50);
                            pd.incrementSecondaryProgressBy(75);
                            h.sendEmptyMessageDelayed(0, 10);
                        } // else
                        //pd.dismiss();
                    }
                };
                h.sendEmptyMessageDelayed(0, 3000);
                break;
        }
    }
}
