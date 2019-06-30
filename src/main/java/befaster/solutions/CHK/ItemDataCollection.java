package befaster.solutions.CHK;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class ItemDataCollection {
	
	public static List<String> prepareShoppingCartItemList(String skus) {
		List<String> shoppingCartItemList = new LinkedList<>();
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
		itemPriceMap.put("F", 10);
		itemPriceMap.put("G", 20);
		itemPriceMap.put("H", 10);
		itemPriceMap.put("I", 35);
		itemPriceMap.put("J", 60);
		itemPriceMap.put("K", 80);
		itemPriceMap.put("L", 90);
		itemPriceMap.put("M", 15);
		itemPriceMap.put("N", 40);
		itemPriceMap.put("O", 10);
		itemPriceMap.put("P", 50);
		itemPriceMap.put("Q", 30);
		itemPriceMap.put("R", 50);
		itemPriceMap.put("S", 30);
		itemPriceMap.put("T", 20);
		itemPriceMap.put("U", 40);
		itemPriceMap.put("V", 50);
		itemPriceMap.put("W", 20);
		itemPriceMap.put("X", 90);
		itemPriceMap.put("Y", 10);
		itemPriceMap.put("Z", 50);
		
		return itemPriceMap;
	}
	
	public static List<String> getValidItems() {
		return Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
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
		
		itemDiscountMap.put("A", Arrays.asList(new ItemDiscount("A", 3, 130, null), new ItemDiscount("A", 5, 200, null)));
		itemDiscountMap.put("B", Arrays.asList(new ItemDiscount("B", 2, 45, null)));
		itemDiscountMap.put("E", Arrays.asList(new ItemDiscount("E", 2, 0, "B")));
		itemDiscountMap.put("F", Arrays.asList(new ItemDiscount("F", 2, 0, "F")));
		itemDiscountMap.put("H", Arrays.asList(new ItemDiscount("H", 5, 45, null), new ItemDiscount("H", 10, 80, null)));
		itemDiscountMap.put("K", Arrays.asList(new ItemDiscount("K", 2, 150, null)));
		itemDiscountMap.put("N", Arrays.asList(new ItemDiscount("N", 3, 0, "M")));
		itemDiscountMap.put("P", Arrays.asList(new ItemDiscount("P", 5, 200, null)));
		itemDiscountMap.put("Q", Arrays.asList(new ItemDiscount("Q", 3, 80, null)));
		itemDiscountMap.put("R", Arrays.asList(new ItemDiscount("R", 3, 0, "Q")));
		itemDiscountMap.put("U", Arrays.asList(new ItemDiscount("U", 3, 0, "U")));
		itemDiscountMap.put("V", Arrays.asList(new ItemDiscount("V", 2, 90, null), new ItemDiscount("V", 3, 130, null)));
		
		return itemDiscountMap;
	}

}







