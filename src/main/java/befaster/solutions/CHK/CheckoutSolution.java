package befaster.solutions.CHK;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CheckoutSolution {
	
	private Integer totalPrice = 0;
	
    public Integer checkout(String skus) {
    	
    	if(skus != null || skus.length() != 0) {
    		
    		// Prepare Cart Item List here
    		List<String> shoppingCartItemList = Arrays.stream(skus.split("\\s*,\\s*")).collect(Collectors.toList());
    		
    		// Prepare Item Quantity Map here
    		
    	}
    	
    	return totalPrice;
    }
}


