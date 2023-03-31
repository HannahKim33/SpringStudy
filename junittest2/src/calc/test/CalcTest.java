package calc.test;

import static org.junit.Assert.*;

import org.junit.Test;

import junittest2.Calc;



public class CalcTest {

	@Test(timeout=2000)
	public void testAdd() {
		Calc c=new Calc();
		assertEquals(5, c.add(2, 3));
	}

}
