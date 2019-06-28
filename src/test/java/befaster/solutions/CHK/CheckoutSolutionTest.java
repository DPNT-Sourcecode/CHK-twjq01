package befaster.solutions.CHK;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CheckoutSolutionTest {
	
	private CheckoutSolution checkout;
	
	@Before
	public void setUp() {
		checkout = new CheckoutSolution();
	}

	@Test
	public void checkoutTest() {
		assertThat(checkout.checkout(""), equalTo(0));
	}
}

