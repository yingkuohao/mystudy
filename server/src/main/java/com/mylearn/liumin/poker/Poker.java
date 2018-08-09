package com.mylearn.liumin.poker;
/**
 * �½�һ���˿�����
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Poker implements Comparator<OnePoker> {
public String[] colors= {"����","����","÷��","��Ƭ"};
public String[] points= {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
//�����˿���
List<OnePoker> pokerList=new ArrayList<OnePoker>();
public  Poker(){
for(String co:colors) {
	for(String po:points) {
		pokerList.add(new OnePoker(co,po));
	}
}
System.out.println("�����ɹ���"+":");
for(OnePoker pokerlist:pokerList) {
	System.out.println("��"+pokerlist.toString()+"��");
}
}
//ϴ��
public void washPoker() {
	List<OnePoker> newPokerList=new ArrayList<OnePoker>();
	for(int i=0;i<pokerList.size();i++) {
		int num = (int) (Math.random() * 52);
	    newPokerList.add(pokerList.get(num));
	    OnePoker po=newPokerList.get(i);
	    //System.out.println("��"+po.toString()+"��");
	}
	pokerList=newPokerList;
	System.out.println("ϴ�Ƴɹ���");
}
//�Ƚϴ�С
@Override
public int compare(OnePoker arg0, OnePoker arg1) {
	// TODO Auto-generated method stub
	String colors="��Ƭ ÷�� ���� ����";
	String points="2 3 4 5 6 7 8 9 10 J Q K A";
	if(points.indexOf(arg0.getpoint())==points.indexOf(arg1.point)) {
		
		return colors.indexOf(arg0.getcolor())-colors.indexOf(arg1.getcolor());
		
	}else {
		return points.indexOf(arg0.getpoint())-points.indexOf(arg1.getpoint());
	}
		

}




}
