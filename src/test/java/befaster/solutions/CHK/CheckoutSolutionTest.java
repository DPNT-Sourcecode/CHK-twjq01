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
//		assertThat(checkout.checkout("E"), equalTo(40));
//		assertThat(checkout.checkout("F"), equalTo(-1));
//		assertThat(checkout.checkout("ABCD"), equalTo(115));
//		assertThat(checkout.checkout("ABCDE"), equalTo(155));
//		assertThat(checkout.checkout("AABCD"), equalTo(165));
//		assertThat(checkout.checkout("AAABCD"), equalTo(195));
//		assertThat(checkout.checkout("AAABBCD"), equalTo(210));
//		assertThat(checkout.checkout("AAAABBBCD"), equalTo(290));
//		assertThat(checkout.checkout("AAAAABBBCD"), equalTo(310));
//		assertThat(checkout.checkout("AAAAAAAAABEE"), equalTo(460));
//		assertThat(checkout.checkout("EEEB"), equalTo(120));
//		assertThat(checkout.checkout("EEEEBB"), equalTo(160));
//		assertThat(checkout.checkout("BEBEEE"), equalTo(160));
//		assertThat(checkout.checkout("ABCDECBAABCABBAAAEEAA"), equalTo(650));
		assertThat(checkout.checkout("ABCDE"), equalTo(165));
	}
}
