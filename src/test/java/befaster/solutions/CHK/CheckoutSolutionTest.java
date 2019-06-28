package befaster.solutions.CHK;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

public class CheckoutSolutionTest {
	
	private CheckoutSolution checkout;
	
	@Before
	public void setUp() {
		checkout = new CheckoutSolution();
	}

	@Test
	public void checkoutTest() {
		assertThat(checkout.checkout("A,B,C,A,B,D"), equalTo(0));
	}
}
