package jums;

import java.io.Serializable;

//課題３ フォームの入力内容を保存するJavaBeansのクラス
public class UserDataBeans implements Serializable{
	private String name;	//名前
	private String year;		//生年月日の年
	private String month;		//生年月日の月
	private String day;		//生年月日の日
	private String type;		//種別
	private String tell;		//電話番号
	private String comment;	//自己紹介文

	public UserDataBeans(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

