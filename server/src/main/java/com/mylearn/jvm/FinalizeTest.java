package com.mylearn.jvm;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-22
 * Time: ����10:20
 * CopyRight:360buy
 * Descrption:
 * 	��Ը������㷨�����������辭��������ǽ׶Σ�1. ������и���������û��������������е�һ�α�ǣ�Ȼ�����һ��ɸѡ���ж��Ƿ���Ҫִ��finalize������
 	1.1������󸲸���finalize������Ҫ����һЩ���飬��Ѷ������һ��F-Queue�Ķ��У������Ժ���һ����������Զ������ġ������ȼ���Finalizer�߳�ȥִ�С�
 	1.2�������û�и���finalize����������finalize�����Ѿ�ִ�й��ˣ��򲻽���F-Queue���С�
 	���F-Queue��ֵ������еڶ��α�ǣ����finalize���������¸�����һ�����ã�������Ƴ�F-Queue���С�
 * To change this template use File | Settings | File Templates.
 */
public class FinalizeTest {
    public static FinalizeTest finalizeTest = null;

    public static void main(String args[]) {
        finalizeTest = new FinalizeTest();
        test();  //��һ��test��ִ��finalize����
        test();  //�ڶ��β���ִ�У�finalize����ִֻ��һ��

    }

    private static void test() {
        finalizeTest = null;   //�����ͷ�
        System.gc();          //gc
        try {
            Thread.sleep(500);     //�ȴ� finalizerִ��
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if (finalizeTest == null) {
            System.out.println("��������");
        } else {
            System.out.println("�����д��");
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize executed!");
        finalizeTest = this;    //���¸�����һ������

    }
}
