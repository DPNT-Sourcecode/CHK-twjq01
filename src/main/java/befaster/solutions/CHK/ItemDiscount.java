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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((itemPrice == null) ? 0 : itemPrice.hashCode());
		result = prime * result + ((itemQuantity == null) ? 0 : itemQuantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemDiscount other = (ItemDiscount) obj;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemPrice == null) {
			if (other.itemPrice != null)
				return false;
		} else if (!itemPrice.equals(other.itemPrice))
			return false;
		if (itemQuantity == null) {
			if (other.itemQuantity != null)
				return false;
		} else if (!itemQuantity.equals(other.itemQuantity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemDiscount [itemName=" + itemName + ", itemQuantity=" + itemQuantity + ", itemPrice=" + itemPrice
				+ ", itemFree=" + itemFree + "]";
	}
}
