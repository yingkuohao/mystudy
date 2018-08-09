package com.mylearn.liumin.poker;
/**
 * 玩家类
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


//创建玩家
public Gamer() {
	
	System.out.println("请输入玩家ID：");
	Scanner input = new Scanner(System.in);
	this.id = input.nextInt(); 
	System.out.println("输入玩家名称：");
	this.name = input.next();


	}
//得到最大的牌sort总是报错不知道为什么 写到main函数就不报错
/**public void getMax() {
	Collections.sort(ownPoker);
	maxPoker=ownPoker.get(ownPoker.size()-1);
	System.out.println("玩家"+ name+ "的最大的手牌是" + maxPoker);
}
*/













}






