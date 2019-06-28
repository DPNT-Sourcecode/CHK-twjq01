package befaster.solutions.CHK;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

public class CheckoutSolution {
	
	private Integer totalPrice;
	
    public Integer checkout(String skus) {
    	totalPrice = 0;
    	if(StringUtils.isNotEmpty(skus)) {

    		// Prepare Cart Item List here
    		List<String> shoppingCartItemList = ItemDataCollection.prepareShoppingCartItemList(skus);

    		try {

    			// Validate Input
    			validateShoppingCartItems(shoppingCartItemList);


    			// Prepare Item Quantity Map here
    			Map<String, Long> itemQuantityMap = ItemDataCollection.getItemQuantityMap(shoppingCartItemList);

    			// Now Calculate Total Price
    			calculateTotalPriceForItems(itemQuantityMap);

    		}catch(IllegalArgumentException e) {
    			// Log Message here
    			System.out.println(e.getMessage());
    			totalPrice = -1;
    		}
    	}
    	
    	return totalPrice;
    }

	private void validateShoppingCartItems(List<String> shoppingCartItemList) {
		if(CollectionUtils.isNotEmpty(shoppingCartItemList)) {
			for (String item : shoppingCartItemList) {
				if(!ItemDataCollection.getValidItems().contains(item)) {
					throw new IllegalArgumentException("Invalid Item in Shopping Cart");
				}
			}
		}
	}

	private void calculateTotalPriceForItems(Map<String, Long> itemQuantityMap) {
		
		if(MapUtils.isNotEmpty(itemQuantityMap)) {
			
			// Get Price for each Item here
			Map<String, Integer> itemPriceMap = ItemDataCollection.getItemPriceMap();
			
			// Get Item Offer here
			Map<String, List<ItemDiscount>> itemDiscountMap = ItemDataCollection.getItemDiscountMap();
			
			// Start Calculation here
			itemQuantityMap.forEach((shoppingItem, shoppingQuantity) -> {
				
				// Check for discount on Item
				if(itemDiscountMap.containsKey(shoppingItem)) {
					List<ItemDiscount> itemDiscountList = itemDiscountMap.get(shoppingItem);
					
					if(CollectionUtils.isNotEmpty(itemDiscountList)) {
						
						for (ItemDiscount itemDiscount : itemDiscountList) {

							if(itemDiscount.getItemQuantity() < shoppingQuantity.intValue()) {
								totalPrice += ((shoppingQuantity.intValue() % itemDiscount.getItemQuantity()) * itemPriceMap.get(shoppingItem))
										+ ((shoppingQuantity.intValue() / itemDiscount.getItemQuantity()) * itemDiscount.getItemPrice());
							}else if(itemDiscount.getItemQuantity() == shoppingQuantity.intValue()) {
								totalPrice += itemDiscount.getItemPrice();
							}else {
								totalPrice += (shoppingQuantity.intValue() * itemPriceMap.get(shoppingItem));
							}
						}
					}
				}else {
					totalPrice += (shoppingQuantity.intValue() * itemPriceMap.get(shoppingItem));
				}
			});
			// End Calculation here
		}
	}
	
	private Optional<ItemDiscount> getItemDiscount(List<ItemDiscount> itemDiscountList, Integer shoppingItemQuantity) {
		
	}
}

