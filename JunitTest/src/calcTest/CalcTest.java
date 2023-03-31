package calcTest;

import static org.junit.Assert.*;

import org.junit.Test;

import junittest.Calc;

public class CalcTest {

	@Test
	public void testAdd() {
		Calc c=new Calc();
		assertEquals(5, c.add(2, 3));
	}

}
