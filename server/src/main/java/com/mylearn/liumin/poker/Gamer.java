package com.mylearn.liumin.poker;
/**
 * �����
 */
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Gamer{
public int id;
public String name;
public OnePoker maxPoker;
public List<OnePoker> ownPoker=new ArrayList<OnePoker>();


//�������
public Gamer() {
	
	System.out.println("���������ID��");
	Scanner input = new Scanner(System.in);
	this.id = input.nextInt(); 
	System.out.println("����������ƣ�");
	this.name = input.next();


	}
//�õ�������sort���Ǳ���֪��Ϊʲô д��main�����Ͳ�����
/**public void getMax() {
	Collections.sort(ownPoker);
	maxPoker=ownPoker.get(ownPoker.size()-1);
	System.out.println("���"+ name+ "������������" + maxPoker);
}
*/













}






