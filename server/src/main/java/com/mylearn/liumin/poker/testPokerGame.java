package com.mylearn.liumin.poker;
/**
 * ��������
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
		//�����˿���
		Poker p=new Poker();
		//ϴ��
		p.washPoker();
		//�������
		for(int i=0;i<players;i++) {
		Gamer g=new Gamer();
		gList.add(g);
		
		}
		//����
		int a=0;
		for(int i=0;i<players;i++) {
			for(int j=0;j<pokernums;j++) {
				System.out.println("���"+gList.get(j).name+"����");
				//��Ҫ�Ѵ���˳����pokerList���Ԫ�ظ�ֵ������Ϸ���˵�����һ����ָ�����
				gList.get(j).ownPoker.add(p.pokerList.get(a));
				a++;
			}
			Collections.sort(gList.get(i).ownPoker);
		}
			

	int i=	p.compare(gList.get(0).ownPoker.get(0), gList.get(1).ownPoker.get(0));
	
//winner
	
		
		}

	}



