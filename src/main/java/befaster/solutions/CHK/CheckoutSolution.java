package befaster.solutions.CHK;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class CheckoutSolution {
	
	private Integer totalPrice = 0;
	
    public Integer checkout(String skus) {
    	
    	if(StringUtils.isNotEmpty(skus)) {
    		
    		// Prepare Cart Item List here
    		List<String> shoppingCartItemList = Arrays.stream(skus.split("\\s*,\\s*")).collect(Collectors.toList());
    		
    		// Prepare Item Quantity Map here
    		Map<String, Long> itemQuantityMap = ItemDataCollection.getItemQuantityMap(shoppingCartItemList);
    		
    		// Now Calculate Total Price
    	}
    	
    	return totalPrice;
    }
}




