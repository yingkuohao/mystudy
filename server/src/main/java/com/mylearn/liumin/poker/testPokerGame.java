package com.mylearn.liumin.poker;
/**
 * 测试主类
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class testPokerGame {
	 static int players=2;
	static int pokernums=2;
	static List<Gamer> gList=new ArrayList<Gamer>();
	//static OnePoker[][] playerPoker=new OnePoker[players][pokernums];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建扑克牌
		Poker p=new Poker();
		//洗牌
		p.washPoker();
		//创建玩家
		for(int i=0;i<players;i++) {
		Gamer g=new Gamer();
		gList.add(g);
		
		}
		//发牌
		int a=0;
		for(int i=0;i<players;i++) {
			for(int j=0;j<pokernums;j++) {
				System.out.println("玩家"+gList.get(j).name+"拿牌");
				//想要把打乱顺序后的pokerList里的元素赋值给玩游戏的人但是有一个空指针错误
				gList.get(j).ownPoker.add(p.pokerList.get(a));
				a++;
			}
			Collections.sort(gList.get(i).ownPoker);
		}
			

	int i=	p.compare(gList.get(0).ownPoker.get(0), gList.get(1).ownPoker.get(0));
	
//winner
	
		
		}

	}



