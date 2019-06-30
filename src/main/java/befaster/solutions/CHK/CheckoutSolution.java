package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    			
    			// Get Price for each Item here
    			Map<String, Integer> itemPriceMap = ItemDataCollection.getItemPriceMap();

    			// Now Calculate Total Price
    			calculateTotalPriceForItems(itemQuantityMap, itemPriceMap);
    			
    			// Now Calculate Discount here
    			calculateDiscount(itemQuantityMap, itemPriceMap);

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

	private void calculateTotalPriceForItems(Map<String, Long> itemQuantityMap, Map<String, Integer> itemPriceMap) {

		if(MapUtils.isNotEmpty(itemQuantityMap)) {

			// Start Calculation here
			itemQuantityMap.forEach((shoppingItem, shoppingQuantity) -> {

				totalPrice += (shoppingQuantity.intValue() * itemPriceMap.get(shoppingItem));
			});
			// End Calculation here
		}
	}
	
	private void calculateDiscount(Map<String, Long> itemQuantityMap, Map<String, Integer> itemPriceMap) {
		
		if(MapUtils.isNotEmpty(itemQuantityMap)) {
			
			List<ItemDiscount> alreadyAppliedDiscount = new ArrayList<>();
			
			// Get Item Offer here
			Map<String, List<ItemDiscount>> itemDiscountMap = ItemDataCollection.getItemDiscountMap();
			
			// Start Calculation here
			itemQuantityMap.forEach((shoppingItem, shoppingQuantity) -> {
				// Check for discount on Item
				if(itemDiscountMap.containsKey(shoppingItem)) {
					List<ItemDiscount> itemDiscountList = itemDiscountMap.get(shoppingItem);
					if(CollectionUtils.isNotEmpty(itemDiscountList)) {
						while(shoppingQuantity > 0) {
							Optional<ItemDiscount> itemDiscountOptional = getItemDiscount(itemDiscountList, shoppingQuantity.intValue());
							if(itemDiscountOptional.isPresent()) {
								ItemDiscount itemDiscount = itemDiscountOptional.get();

								if(StringUtils.isNotEmpty(itemDiscount.getItemFree())) {
									if(itemQuantityMap.containsKey(itemDiscount.getItemFree())) {
										if(shoppingQuantity > itemDiscount.getItemQuantity()) {
											if(CollectionUtils.isEmpty(alreadyAppliedDiscount)) {
												alreadyAppliedDiscount.add(itemDiscount);
												totalPrice -= itemPriceMap.get(itemDiscount.getItemFree());
											}else if(!alreadyAppliedDiscount.contains(itemDiscount)) {
												totalPrice -= itemPriceMap.get(itemDiscount.getItemFree());
											}
										}else if(!alreadyAppliedDiscount.stream().filter(aad -> aad.getItemName().equals(itemDiscount.getItemFree())).findFirst().isPresent()) {
											totalPrice -= itemPriceMap.get(itemDiscount.getItemFree());
										}
									}
								}else {
									if(!isFreeDiscountExist(itemDiscountMap, shoppingItem, itemQuantityMap, shoppingQuantity.intValue())) {
										totalPrice -= (itemDiscount.getItemQuantity() * itemPriceMap.get(shoppingItem)) - itemDiscount.getItemPrice();
									}else if(shoppingQuantity > itemDiscount.getItemQuantity()){
										if(CollectionUtils.isEmpty(alreadyAppliedDiscount)) {
											alreadyAppliedDiscount.add(itemDiscount);
											totalPrice -= itemDiscount.getItemPrice();
										}else if(!alreadyAppliedDiscount.contains(itemDiscount)) {
											totalPrice -= itemDiscount.getItemPrice();
										}
									}
								}
								shoppingQuantity -= itemDiscount.getItemQuantity();
							}else {
								shoppingQuantity -= shoppingQuantity;
							}
						}
					}
				}
			});
			// End Calculation here
		}
	}
	
	private Optional<ItemDiscount> getItemDiscount(List<ItemDiscount> itemDiscountList, Integer shoppingItemQuantity) {
		List<ItemDiscount> filterList = itemDiscountList.stream().filter(item -> item.getItemQuantity() <= shoppingItemQuantity).collect(Collectors.toList());
		if(CollectionUtils.isNotEmpty(filterList)) {
			Comparator<ItemDiscount> comparator = Comparator.comparing(ItemDiscount::getItemQuantity);
			return Optional.ofNullable(filterList.stream().max(comparator).orElse(null));
		}
		return Optional.empty();
	}
	
	private boolean isFreeDiscountExist(Map<String, List<ItemDiscount>> itemDiscountMap, String shoppingItem, Map<String, Long> itemQuantityMap, Integer shoppingQuantity) {
		boolean checkFlag = false;
		for(Map.Entry<String, List<ItemDiscount>> entry : itemDiscountMap.entrySet()) {
			for (ItemDiscount value : entry.getValue()) {
				if(StringUtils.isNotEmpty(value.getItemFree()) 
						&& shoppingItem.equalsIgnoreCase(value.getItemFree()) 
						&& itemQuantityMap.containsKey(value.getItemName())) {
					checkFlag = true;
					break;
				}
			}
		}
		return checkFlag;
	}
}

