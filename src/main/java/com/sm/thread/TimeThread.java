package com.sm.thread;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeThread extends Thread {
    private JLabel timeLabel;
    public void setTimeLabel(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }
    @Override
    public void run() {
        while (true) {
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            timeLabel.setText(String.valueOf(df.format(new Date())));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}