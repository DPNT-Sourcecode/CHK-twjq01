package befaster.solutions.CHK;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

public class ItemDataCollection {
	
	public static Map<String, Integer> getItemPriceMap() {
		Map<String, Integer> itemPriceMap = new TreeMap<>();
		
		itemPriceMap.put("A", 50);
		itemPriceMap.put("B", 30);
		itemPriceMap.put("C", 20);
		itemPriceMap.put("D", 15);
		
		return itemPriceMap;
	}
	
	public static Map<String, Long> getItemQuantityMap(List<String> shoppingCartItemList) {
		if(CollectionUtils.isNotEmpty(shoppingCartItemList)) {
			return shoppingCartItemList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		}else {
			return Collections.emptyMap();
		}
	}
	
	public static Map<String, ItemDiscount> getItemDiscountMap() {
		Map<String, ItemDiscount> itemDiscountMap = new TreeMap<>();
		
		itemDiscountMap.put("A", new ItemDiscount("A", 3, 160));
		itemDiscountMap.put("B", new ItemDiscount("B", 2, 45));
		
		return itemDiscountMap;
	}

}




