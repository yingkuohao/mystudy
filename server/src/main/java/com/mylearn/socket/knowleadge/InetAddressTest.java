package com.mylearn.socket.knowleadge;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-12
 * Time: ????4:54
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class InetAddressTest {

    public static void main(String args[]) throws UnsupportedEncodingException {
        System.out.println(new Byte[]{}.toString());
 /*       try {
            Enumeration<NetworkInterface> interfaceEnum = NetworkInterface.getNetworkInterfaces();
            while (interfaceEnum.hasMoreElements()) {
                   NetworkInterface cur = interfaceEnum.nextElement();
                Enumeration<InetAddress> inetAddressEnumeration =    cur.getInetAddresses();
                while(inetAddressEnumeration.hasMoreElements()) {
                    InetAddress inetAddress =inetAddressEnumeration.nextElement();
                    System.out.println("inetAdress=" + inetAddress.getHostAddress() + "   address = " +inetAddress.getHostName() + "  getCanonicalHostName=" + inetAddress.getCanonicalHostName());
                }
            }

            System.out.println(InetAddress.getLocalHost());
        } catch (SocketException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnknownHostException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }*/
    }
}
