package db;


public class UserDTO {

	private int userID;
	private String name;
	private String passward;

	public UserDTO(){}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassward() {
		return passward;
	}

	public void setPassward(String password) {
		this.passward = password;
	}

	//未入力の項目がないかチェックするメソッド
	public boolean blankCheck(){
		if(this.name.trim().equals("")){
			return false;
		}
		if(this.passward.trim().equals("")){
			return false;
		}

		return true;
	}


}
