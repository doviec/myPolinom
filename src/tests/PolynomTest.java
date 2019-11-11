package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import myMath.Monom;
import myMath.Polynom;

class PolynomTest {

	@Test
	void testPolynom() {
		fail("Not yet implemented");
	}

	@Test
	void testPolynomString() {
		fail("Not yet implemented");
	}

	@Test
	void testF() {
		fail("Not yet implemented");
	}

	@Test
	void testAddPolynom_able() {
		fail("Not yet implemented");
	}

	@Test
	void testAddMonom() {
		String s1 ="x^4";
		String s2 ="3x^2- 2x^4 +3";
		
		Monom m1 = new Monom(s1);
		Polynom p2 = new Polynom(s2);
		
		p2.add(m1);
		
		HashMap<Integer, Monom> hash = new HashMap<>();
		
		Iterator<Monom> iterator = p2.iteretor();
		while (iterator.hasNext()) {
			Monom m = iterator.next();
			hash.put(m.get_power(),m);
		}
		assertNotNull(hash.get(4));
		assertNotNull(hash.get(2));
		assertNotNull(hash.get(0));
		
		assertEquals(-1, hash.get(4).get_coefficient());
		assertEquals(3, hash.get(2).get_coefficient());
		assertEquals(3, hash.get(0).get_coefficient());
		
		//assertEquals(expected, actual);
	}

	@Test
	void testSubtract() {
		fail("Not yet implemented");
	}

	@Test
	void testSubstract() {
		fail("Not yet implemented");
	}

	@Test
	void testMultiplyPolynom_able() {
		fail("Not yet implemented");
	}

	@Test
	void testEqualsPolynom_able() {
		fail("Not yet implemented");
	}

	@Test
	void testIsZero() {
		fail("Not yet implemented");
	}

	@Test
	void testRoot() {
		fail("Not yet implemented");
	}

	@Test
	void testCopy() {
		fail("Not yet implemented");
	}

	@Test
	void testDerivative() {
		fail("Not yet implemented");
	}

	@Test
	void testArea() {
		fail("Not yet implemented");
	}

	@Test
	void testIteretor() {
		fail("Not yet implemented");
	}

	@Test
	void testMultiplyMonom() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

}
