package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class ItemDataCollection {
	
	public static List<String> prepareShoppingCartItemList(String skus) {
		List<String> shoppingCartItemList = new ArrayList<>();
		if(StringUtils.isNotEmpty(skus)) {
			for (Character c : skus.toCharArray()) {
				shoppingCartItemList.add(String.valueOf(c));
			}
		}
		return shoppingCartItemList;
	}
	
	public static Map<String, Integer> getItemPriceMap() {
		Map<String, Integer> itemPriceMap = new TreeMap<>();
		
		itemPriceMap.put("A", 50);
		itemPriceMap.put("B", 30);
		itemPriceMap.put("C", 20);
		itemPriceMap.put("D", 15);
		itemPriceMap.put("E", 40);
		
		return itemPriceMap;
	}
	
	public static List<String> getValidItems() {
		return Arrays.asList("A","B","C","D","E");
	}
	
	public static Map<String, Long> getItemQuantityMap(List<String> shoppingCartItemList) {
		if(CollectionUtils.isNotEmpty(shoppingCartItemList)) {
			return shoppingCartItemList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		}else {
			return Collections.emptyMap();
		}
	}
	
	public static Map<String, List<ItemDiscount>> getItemDiscountMap() {
		Map<String, List<ItemDiscount>> itemDiscountMap = new TreeMap<>();
		
		itemDiscountMap.put("A", Arrays.asList(new ItemDiscount("A", 3, 130)));
		itemDiscountMap.put("B", Arrays.asList(new ItemDiscount("B", 2, 45)));
		
		return itemDiscountMap;
	}

}


