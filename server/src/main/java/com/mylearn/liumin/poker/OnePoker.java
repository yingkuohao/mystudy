package com.mylearn.liumin.poker;
/**
 * �½�һ�������˿�����
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
//����д���������sort�����Ų��ᱨ������д��Gamer�����sort�������Ǳ���֪��Ϊʲô
public int compareTo(OnePoker maxPoker) {
	String colors="��Ƭ ÷�� ���� ����";
	String points="2 3 4 5 6 7 8 9 10 J Q K A";
	if(points.indexOf(this.getpoint())==points.indexOf(maxPoker.point)) {
		
		return colors.indexOf(this.getcolor())-colors.indexOf(maxPoker.getcolor());
		
	}else {
		return points.indexOf(this.getpoint())-points.indexOf(maxPoker.getpoint());
	}

}

}
