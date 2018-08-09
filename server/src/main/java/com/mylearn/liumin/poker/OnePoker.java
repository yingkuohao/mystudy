package com.mylearn.liumin.poker;
/**
 * 新建一个单张扑克牌类
 * @author changchang
 *
 */
public class OnePoker implements Comparable<OnePoker>{
public String color;
public String point;
public OnePoker(String color,String point) {
	this.color=color;
	this.point=point;
}
public String toString() {
	return color+point;
}
public String getpoint() {
	// TODO Auto-generated method stub
	return point;
}
public String getcolor() {
	// TODO Auto-generated method stub
	return color;
}
//必须写了这个方法sort方法才不会报错，但是写在Gamer类里的sort方法还是报错不知道为什么
public int compareTo(OnePoker maxPoker) {
	String colors="方片 梅花 红桃 黑桃";
	String points="2 3 4 5 6 7 8 9 10 J Q K A";
	if(points.indexOf(this.getpoint())==points.indexOf(maxPoker.point)) {
		
		return colors.indexOf(this.getcolor())-colors.indexOf(maxPoker.getcolor());
		
	}else {
		return points.indexOf(this.getpoint())-points.indexOf(maxPoker.getpoint());
	}

}

}
