package com.mylearn.socket.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Callable;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-8-27
 * Time: œ¬ŒÁ8:25
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class KeyBoardInput implements Runnable {

    volatile boolean isStopped = false;
    SocketChannel sc;

    public KeyBoardInput(SocketChannel sc) {
        this.sc = sc;
    }

    public void close() {
        isStopped = true;
    }

    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (!isStopped) {
            try {
                System.out.println("«Î ‰»Î£∫");
                String input = bufferedReader.readLine();
                sc.write(ByteBuffer.wrap(input.getBytes()));
            } catch (IOException e) {

            }

        }
    }

    public SocketChannel getSc() {
        return sc;
    }

    public void setSc(SocketChannel sc) {
        this.sc = sc;
    }
}
