package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import myMath.Monom;

class MonomTest {

	@Test
	void testMonomDoubleInt() {
		fail("Not yet implemented");
	}

	@Test
	void testMonomMonom() {
		fail("Not yet implemented");
	}

	@Test
	void testDerivative() {
		fail("Not yet implemented");
	}

	@Test
	void testF() {
		fail("Not yet implemented");
	}

	@Test
	void testIsZero() {
		fail("Not yet implemented");
	}

	@Test
	void testMonomString() {
		String monomStr = "x^5";
		Monom monom = new Monom(monomStr);
		assertEquals(5, monom.get_power());
		assertEquals(1, monom.get_coefficient());
	}

	@Test
	void testAdd() {
		String monomStr1 = "x^5";
		Monom monom1 = new Monom(monomStr1);
		String monomStr2 = "7x^5";
		Monom monom2 = new Monom(monomStr2);
		
		monom1.add(monom2);
		
		assertEquals(5, monom1.get_power());
		assertEquals(8, monom1.get_coefficient());
	}

	@Test
	void testSubtract() {
		fail("Not yet implemented");
	}

	@Test
	void testMultipy() {
		fail("Not yet implemented");
	}

}
