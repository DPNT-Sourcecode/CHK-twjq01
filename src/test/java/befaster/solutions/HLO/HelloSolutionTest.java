package befaster.solutions.HLO;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

public class HelloSolutionTest {

    private HelloSolution hello;

    @Before
    public void setUp() {

    	hello = new HelloSolution();
    }

    @Test
    public void compute_sum() {
        assertThat(hello.hello("ValTech"), equalTo("Hello, World!"));
    }
}