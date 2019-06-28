package befaster.solutions.CHK;

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
			System.out.println(itemQuantityMap);
			
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
						while(shoppingQuantity > 0) {
							Optional<ItemDiscount> itemDiscountOptional = getItemDiscount(itemDiscountList, shoppingQuantity.intValue());
							if(itemDiscountOptional.isPresent()) {
								ItemDiscount itemDiscount = itemDiscountOptional.get();
								
								if(StringUtils.isNotEmpty(itemDiscount.getItemFree())) {
									
									totalPrice += (itemDiscount.getItemQuantity() * itemPriceMap.get(shoppingItem));
									
									if(itemQuantityMap.containsKey(itemDiscount.getItemFree()) 
											&& itemQuantityMap.get(itemDiscount.getItemFree()).intValue() > 0) {
										List<ItemDiscount> itemDiscountFreeList = itemDiscountMap.get(itemDiscount.getItemFree());
										if(CollectionUtils.isNotEmpty(itemDiscountFreeList)) {
											Integer freeItemCount = itemQuantityMap.get(itemDiscount.getItemFree()).intValue();
											Optional<ItemDiscount> itemDiscountFreeOptional = getItemDiscount(itemDiscountFreeList, freeItemCount);
											if(itemDiscountFreeOptional.isPresent()) {
												ItemDiscount itemDiscountFree = itemDiscountFreeOptional.get();
												totalPrice -= itemDiscountFree.getItemPrice();
												itemQuantityMap.computeIfPresent(itemDiscount.getItemFree(), (key, value) -> value > 0 ? value - freeItemCount : 0);
											}else {
												totalPrice -= itemPriceMap.get(itemDiscount.getItemFree());
												itemQuantityMap.computeIfPresent(itemDiscount.getItemFree(), (key, value) -> value > 0 ? value - 1 : 0);
											}
										}else {
											totalPrice -= itemPriceMap.get(itemDiscount.getItemFree());
											itemQuantityMap.computeIfPresent(itemDiscount.getItemFree(), (key, value) -> value > 0 ? value - 1 : 0);
										}
									}
								}else {
									totalPrice += itemDiscount.getItemPrice();
									itemQuantityMap.computeIfPresent(itemDiscount.getItemName(), (key, value) -> value > 0 ? value - itemDiscount.getItemQuantity() : 0);
								}
								
								shoppingQuantity -= itemDiscount.getItemQuantity();
							}else {
								totalPrice += (shoppingQuantity.intValue() * itemPriceMap.get(shoppingItem));
								int sQty = shoppingQuantity.intValue(); 
								itemQuantityMap.computeIfPresent(shoppingItem, (key, value) -> value > 0 ? value - sQty : 0);
								shoppingQuantity -= shoppingQuantity;
							}
						}
					}
				}else {
					totalPrice += (shoppingQuantity.intValue() * itemPriceMap.get(shoppingItem));
				}
				
				System.out.println("shoppingItem -->"+shoppingItem+ " totalPrice ::"+totalPrice);
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
}
