package befaster.solutions.CHK;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

public class ItemDataCollection {
	
	public static Map<String, Integer> getItemPriceMap() {
		
	}
	
	public static Map<String, Long> getItemQuantityMap(List<String> shoppingCartItemList) {
		if(CollectionUtils.isNotEmpty(shoppingCartItemList)) {
			return shoppingCartItemList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		}else {
			return Collections.emptyMap();
		}
	}

}
