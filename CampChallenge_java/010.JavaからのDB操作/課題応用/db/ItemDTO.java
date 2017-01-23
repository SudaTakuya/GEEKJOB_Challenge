package db;

public class ItemDTO {
	private int itemID;
	private String name;
	private int price;

	public ItemDTO(){}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = Integer.parseInt(price);
	}

	//未入力の項目がないかチェックするメソッド（priceはセッターでから文字でないことを確認済み）
	public boolean blankCheck(){
		if(this.name.trim().equals("")){
			return false;
		}

		return true;
	}



}
