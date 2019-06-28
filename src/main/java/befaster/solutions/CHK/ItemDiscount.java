package befaster.solutions.CHK;

public class ItemDiscount {

	private String itemName;
	private Integer itemQuantity;
	private Integer itemPrice;

	public ItemDiscount(String itemName, Integer itemQuantity, Integer itemPrice) {
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
	}

	public String getItemName() {
		return itemName;
	}

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public Integer getItemPrice() {
		return itemPrice;
	}

}
