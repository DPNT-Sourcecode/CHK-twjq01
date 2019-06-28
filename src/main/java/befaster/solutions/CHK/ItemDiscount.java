package befaster.solutions.CHK;

public class ItemDiscount {

	private String itemName;
	private Integer itemQuantity;
	private Integer itemPrice;
	private String itemFree;

	public ItemDiscount(String itemName, Integer itemQuantity, Integer itemPrice, String itemFree) {
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
		this.itemFree = itemFree;
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

	public String getItemFree() {
		return itemFree;
	}
	
}
