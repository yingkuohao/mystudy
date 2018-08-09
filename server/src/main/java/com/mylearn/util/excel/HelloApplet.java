package com.mylearn.util.excel;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Package:com.jd.ebook.admin.service.excel
 * User: yingkuohao
 * Date: 12-2-20
 * Time: ????11:35
 * CopyRight:360buy
 * Descrption:   ?????applet??main????
 */


public class HelloApplet extends Applet {


    public void paint(Graphics g) {
        g.drawString("Helloworld!", 50, 25);
    }


    public static void main(String[] args) {
        HelloApplet applet = new HelloApplet();
        JFrame frame = new JFrame("PinTu");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.getContentPane().add(
                applet, BorderLayout.CENTER);
        frame.setSize(650, 520);
        applet.init();
        applet.start();
        frame.setVisible(true);

    }

}
