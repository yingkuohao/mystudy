package com.mylearn.rmi.principle.local;

import com.mylearn.rmi.principle.remote.Server_Skeleton;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-30
 * Time: ????5:21
 * CopyRight:360buy
 * Descrption:  ????????
 * To change this template use File | Settings | File Templates.
 */
public class Client {

    public static void main(String args[]) {
        //1.?????????????
        final Thread threadServer1 = new Thread(new Server_Skeleton());

        //2.??5?????????????sayHello
        for (int i = 0; i < 5; i++) {
            Thread threadClient1 = new Thread(new Runnable() {
                public void run() {
                    Client_Stub client_stub = new Client_Stub();
                    String response = client_stub.sayHello();
                    System.out.println("response1 = " + response);
                }
            });
            threadClient1.start();
        }

        //3.??5?????????????sayLove
        for (int i = 0; i < 5; i++) {
            Thread threadClient2 = new Thread(new Runnable() {
                public void run() {
                    Client_Stub client_stub = new Client_Stub();
                    String response = client_stub.sayLove();
                    System.out.println("response2 = " + response);
                }
            });
            threadClient2.start();
        }
        threadServer1.start();//??????????????

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
