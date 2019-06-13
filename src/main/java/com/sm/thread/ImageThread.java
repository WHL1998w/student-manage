package com.sm.thread;


import com.sm.frame.StudentMainFrame;

import javax.swing.*;

public class ImageThread extends Thread {
    private JLabel imageLabel;
    public void setImageLabel(JLabel imageLabel){
        this.imageLabel = imageLabel;
    }
    @Override
    public void run() {
        String[] imgPath = {"b(1).jpg","b(1).png","b(2).jpg","b(3),jpg"};
        while (true){
            for(int i = 0; i < imgPath.length; i++){
                imageLabel = new JLabel("<html><img src='" + imgPath +"'width=1150 height=350 /></html>");
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if (i == imgPath.length - 1){
                    i = 0;
                }
            }
        }
    }
}
