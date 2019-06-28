package befaster.solutions.CHK;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

public class CheckoutSolution {
	
	private Integer totalPrice = 0;
	
    public Integer checkout(String skus) {
    	
    	if(StringUtils.isNotEmpty(skus)) {
    		
    		// Prepare Cart Item List here
    		List<String> shoppingCartItemList = Arrays.stream(skus.split("\\s*,\\s*")).collect(Collectors.toList());
    		System.out.println(shoppingCartItemList);
    		
    		// Prepare Item Quantity Map here
    		Map<String, Long> itemQuantityMap = ItemDataCollection.getItemQuantityMap(shoppingCartItemList);
    		System.out.println(itemQuantityMap);
    		
    		// Now Calculate Total Price
    		calculateTotalPriceForItems(itemQuantityMap);
    	}
    	
    	return totalPrice;
    }

	private void calculateTotalPriceForItems(Map<String, Long> itemQuantityMap) {
		
		if(MapUtils.isNotEmpty(itemQuantityMap)) {
			
			// Get Price for each Item here
			Map<String, Integer> itemPriceMap = ItemDataCollection.getItemPriceMap();
			System.out.println(itemPriceMap);
			
			// Get Item Offer here
			Map<String, ItemDiscount> itemDiscountMap = ItemDataCollection.getItemDiscountMap();
			
			// Start Calculation here
			itemQuantityMap.forEach((shoppingItem, shoppingQuantity) -> {
				
				// Check for discount on Item
				if(itemDiscountMap.containsKey(shoppingItem)) {
					ItemDiscount itemDiscount = itemDiscountMap.get(shoppingItem);
					if(itemDiscount != null) {
						
						if(itemDiscount.getItemQuantity() < shoppingQuantity.intValue()) {
							totalPrice += ((shoppingQuantity.intValue() % itemDiscount.getItemQuantity()) * itemPriceMap.get(shoppingItem))
									+ ((shoppingQuantity.intValue() / itemDiscount.getItemQuantity()) * itemDiscount.getItemPrice());
						}else if(itemDiscount.getItemQuantity() == shoppingQuantity.intValue()) {
							totalPrice += itemDiscount.getItemPrice();
						}else {
							totalPrice += (shoppingQuantity.intValue() * itemPriceMap.get(shoppingItem));
						}
					}
				}else {
					totalPrice += (shoppingQuantity.intValue() * itemPriceMap.get(shoppingItem));
				}
			});
			// End Calculation here
		}
	}
}

