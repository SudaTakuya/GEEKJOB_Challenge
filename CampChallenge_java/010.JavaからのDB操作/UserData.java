package model;
import java.util.Calendar;

//入力された挿入データを保管するクラス
public class UserData {

	private int ID;
	private String name;
	private String tell;
	private int age;
	private Calendar birthday = Calendar.getInstance();

	public UserData(){
		this.ID = 0;
		this.name = "";
		this.tell = "";
		this.age = 0;
		this.birthday.set(0, 0, 0);
	}

	public int getID() {
		return ID;
	}

	public void setID(String id) {
		if(!id.trim().equals("")){
			this.ID = Integer.parseInt(id);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(!name.trim().equals("")){
			this.name = name;
		}

	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		if(!tell.trim().equals("")){
			this.tell = tell;
		}
	}

	public int getAge() {
		return age;
	}

	public void setAge(String age) {
		if(!age.trim().equals("")){
			this.age = Integer.parseInt(age);
		}
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(String year,String month,String day) {
		int yearTmp=0;
		int monthTmp=0;
		int dayTmp=0;

		if(!year.trim().equals("")){
			 yearTmp = Integer.parseInt(year);
		}
		if(!month.trim().equals("")){
			monthTmp = Integer.parseInt(month) - 1;
		}
		if(!day.trim().equals("")){
			dayTmp = Integer.parseInt(day);
		}

		birthday.set(yearTmp, monthTmp, dayTmp);
	}

	//未入力項目がないかチェックするメソッド
	public boolean blankCheck(String id,String name ,String tell,String age ,String year ,String month,String day){
		if(id.trim().equals("")){
			return false;
		}
		if(name.trim().equals("")){
			return false;
		}
		if(tell.trim().equals("")){
			return false;
		}
		if(age.trim().equals("")){
			return false;
		}
		if(year.trim().equals("")){
			return false;
		}
		if(month.trim().equals("")){
			return false;
		}
		if(day.trim().equals("")){
			return false;
		}

		return true;
	}

	//未入力項目がないかチェックするメソッド（課題１２の複合検索に使う項目のみ）
	public boolean blankCheckFor12(String name,String age,String year,String month,String day){
		if(name.trim().equals("")){
			return false;
		}
		if(age.trim().equals("")){
			return false;
		}
		if(year.trim().equals("")){
			return false;
		}
		if(month.trim().equals("")){
			return false;
		}
		if(day.trim().equals("")){
			return false;
		}


		return true;
	}



}
