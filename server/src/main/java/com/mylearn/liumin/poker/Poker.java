package com.mylearn.liumin.poker;
/**
 * 新建一副扑克牌类
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Poker implements Comparator<OnePoker> {
public String[] colors= {"黑桃","红桃","梅花","方片"};
public String[] points= {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
//创建扑克牌
List<OnePoker> pokerList=new ArrayList<OnePoker>();
public  Poker(){
for(String co:colors) {
	for(String po:points) {
		pokerList.add(new OnePoker(co,po));
	}
}
System.out.println("创建成功！"+":");
for(OnePoker pokerlist:pokerList) {
	System.out.println("【"+pokerlist.toString()+"】");
}
}
//洗牌
public void washPoker() {
	List<OnePoker> newPokerList=new ArrayList<OnePoker>();
	for(int i=0;i<pokerList.size();i++) {
		int num = (int) (Math.random() * 52);
	    newPokerList.add(pokerList.get(num));
	    OnePoker po=newPokerList.get(i);
	    //System.out.println("【"+po.toString()+"】");
	}
	pokerList=newPokerList;
	System.out.println("洗牌成功！");
}
//比较大小
@Override
public int compare(OnePoker arg0, OnePoker arg1) {
	// TODO Auto-generated method stub
	String colors="方片 梅花 红桃 黑桃";
	String points="2 3 4 5 6 7 8 9 10 J Q K A";
	if(points.indexOf(arg0.getpoint())==points.indexOf(arg1.point)) {
		
		return colors.indexOf(arg0.getcolor())-colors.indexOf(arg1.getcolor());
		
	}else {
		return points.indexOf(arg0.getpoint())-points.indexOf(arg1.getpoint());
	}
		

}




}
