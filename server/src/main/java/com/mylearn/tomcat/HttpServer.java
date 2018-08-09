package com.mylearn.tomcat;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-1
 * Time: ????2:10
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class HttpServer {

    public static String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    private static String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String args[]) {
            HttpServer httpServer =new HttpServer();
        httpServer.await();
    }

    private void await() {
        ServerSocket serverSocket =null;
        int port = 80090;
        try {
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
