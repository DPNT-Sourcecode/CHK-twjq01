package befaster.solutions.CHK;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
		assertThat(checkout.checkout("A"), equalTo(50));
		assertThat(checkout.checkout("E"), equalTo(-1));
		assertThat(checkout.checkout("A,B,C,D"), equalTo(115));
		assertThat(checkout.checkout("A,A,B,C,D"), equalTo(165));
		assertThat(checkout.checkout("A,A,A,B,C,D"), equalTo(195));
		assertThat(checkout.checkout("A,A,A,B,B,C,D"), equalTo(210));
		assertThat(checkout.checkout("A,A,A,A,B,B,B,C,D"), equalTo(290));
	}
}

