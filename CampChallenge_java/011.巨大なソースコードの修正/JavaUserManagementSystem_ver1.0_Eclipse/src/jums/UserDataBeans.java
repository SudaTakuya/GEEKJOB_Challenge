package jums;

import java.io.Serializable;

//課題３ フォームの入力内容を保存するJavaBeansのクラス
public class UserDataBeans implements Serializable{
	private String name;	//名前
	private int year;		//生年月日の年
	private int month;		//生年月日の月
	private int day;		//生年月日の日
	private int type;		//種別
	private String tell;		//電話番号
	private String comment;	//自己紹介文

	public UserDataBeans(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}



}
