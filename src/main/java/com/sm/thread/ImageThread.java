package com.sm.thread;

import com.sm.frame.TeacherMainFrame;

import javax.swing.*;
import java.io.*;

public class ImageThread extends Thread {
    private String[] imgPath = {"D:/image/a.jpg", "D:/image/b.jpg", "D:/image/c.jpg",
            "D:/image/d.jpg"};
    private JLabel imgLabel;
    public void setImgLabel(JLabel imgLabel){
        this.imgLabel = imgLabel;
    }
    @Override
    public void run() {
        int i = 0;
        int len = imgPath.length - 1;
        while (true) {
            try {
                File file = new File(imgPath[i]);
                InputStream inputStream = new FileInputStream(file);
                byte[] bytes = new byte[(int) file.length()];
                inputStream.read(bytes);
                Icon icon = new ImageIcon(bytes);
                imgLabel.setIcon(icon);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
                if (i == len) {
                    i = 0;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("IO异常");
            }
        }
    }
}
