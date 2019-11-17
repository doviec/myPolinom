package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import myMath.Monom;

class MonomTest {



	@Test
	void testDerivative() {
		String Str1 = "x^5";
		String Str2 = "2x^3";
		String Str3 = "x";

		Monom monom1 = new Monom(Str1);
		Monom monom2 = new Monom(Str2);
		Monom monom3 = new Monom(Str3);
		
		monom1 = monom1.derivative();
		monom2 = monom2.derivative();
		monom3 = monom3.derivative();
		
		assertEquals(4, monom1.get_power());
		assertEquals(5, monom1.get_coefficient());
		assertEquals(2, monom2.get_power());
		assertEquals(6, monom2.get_coefficient());
		assertEquals(0, monom3.get_power());
		assertEquals(1, monom3.get_coefficient());

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
		String monomStr1 = "8x^5";
		Monom monom1 = new Monom(monomStr1);
		String monomStr2 = "7x^5";
		Monom monom2 = new Monom(monomStr2);
		

		monom1.subtract(monom2);

		assertEquals(5, monom1.get_power());
		assertEquals(1, monom1.get_coefficient());
	}

	@Test
	void testMultipy() {
		String monomStr1 = "3x^5";
		Monom monom1 = new Monom(monomStr1);
		String monomStr2 = "4x^2";
		Monom monom2 = new Monom(monomStr2);
		String monomStr3 = "2x";
		Monom monom3 = new Monom(monomStr3);

		monom1.multipy(monom2);
		monom2.multipy(monom3);

		assertEquals(7, monom1.get_power());
		assertEquals(12, monom1.get_coefficient());
		assertEquals(3, monom2.get_power());
		assertEquals(8, monom2.get_coefficient());
	}

}









