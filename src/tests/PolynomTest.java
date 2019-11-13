package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import myMath.Monom;
import myMath.Polynom;

class PolynomTest {


	@Test
	void testPolynomString() {
		fail("Not yet implemented");
	}

	@Test
	void testF() {
		String string1 ="3x^2- 2x^4 +3";
		String string2 ="2x-x^2";
		String string3 ="2";

		Polynom polynom1 = new Polynom(string1);
		Polynom polynom2 = new Polynom(string2);
		Polynom polynom3 = new Polynom(string3);

		double value1 = polynom1.f(2);
		double value2 = polynom2.f(2);
		double value3 = polynom3.f(2);

		assertEquals(-17,value1);
		assertEquals(0,value2);
		assertEquals(2,value3);

	}

	@Test
	void testAddPolynom_able() {

		String string1 ="-x^5+2x^2-1x^4+3";
		String string2 ="x^5+3x^3+2x^4-9";

		Polynom polynom1 = new Polynom(string1);
		Polynom polynom2 = new Polynom(string2);

		polynom1.add(polynom2);

		HashMap<Integer, Monom> hashMap = new HashMap<>();
		Iterator<Monom> iterator = polynom1.iteretor();

		while (iterator.hasNext()) {
			Monom m = iterator.next();
			hashMap.put(m.get_power(),m);
		}
		assertNotNull(hashMap.get(4));
		assertNotNull(hashMap.get(3));
		assertNotNull(hashMap.get(2));
		assertNotNull(hashMap.get(0));

		assertEquals(1, hashMap.get(4).get_coefficient());
		assertEquals(3, hashMap.get(3).get_coefficient());
		assertEquals(2, hashMap.get(2).get_coefficient());
		assertEquals(-6, hashMap.get(0).get_coefficient());
		assertEquals(0, hashMap.get(5).get_coefficient());

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
	}

	@Test
	void testSubtract() {

		String string1 ="x^5+2x^2-x^4+3";
		String string2 ="x^5+3x^3+4x^4-9";

		Polynom polynom1 = new Polynom(string1);
		Polynom polynom2 = new Polynom(string2);

		polynom1.substract(polynom2);

		HashMap<Integer, Monom> hashMap = new HashMap<>();
		Iterator<Monom> iterator = polynom1.iteretor();

		while (iterator.hasNext()) {
			Monom m = iterator.next();
			hashMap.put(m.get_power(),m);
		}
		assertNull(hashMap.get(5));
		
		assertNotNull(hashMap.get(4));
		assertNotNull(hashMap.get(3));
		assertNotNull(hashMap.get(2));
		assertNotNull(hashMap.get(0));

		assertEquals(-5, hashMap.get(4).get_coefficient());
		assertEquals(-3, hashMap.get(3).get_coefficient());
		assertEquals(2, hashMap.get(2).get_coefficient());
		assertEquals(12, hashMap.get(0).get_coefficient());
		

	}
	
	@Test
	void testSubstract() {
		String s1 ="-2x^4";
		String s2 ="3x^2- 2x^4 +3";
		

		Monom m1 = new Monom(s1);
		Polynom p2 = new Polynom(s2);

		p2.subtract(m1);

		HashMap<Integer, Monom> hash = new HashMap<>();

		Iterator<Monom> iterator = p2.iteretor();
		while (iterator.hasNext()) {
			Monom m = iterator.next();
			hash.put(m.get_power(),m);
		}
		
		assertNull(hash.get(4));
		assertNotNull(hash.get(2));
		assertNotNull(hash.get(0));

		
		assertEquals(3, hash.get(2).get_coefficient());
		assertEquals(3, hash.get(0).get_coefficient());
	}

	@Test
	void testMultiplyPolynom_able() {
		
		String string1 ="2x^2-x+3";
		String string2 ="3x^3+4x^4-2";

		Polynom polynom1 = new Polynom(string1);
		Polynom polynom2 = new Polynom(string2);

		polynom1.multiply(polynom2);

		HashMap<Integer, Monom> hashMap = new HashMap<>();
		Iterator<Monom> iterator = polynom1.iteretor();

		while (iterator.hasNext()) {
			Monom m = iterator.next();
			hashMap.put(m.get_power(),m);
		}
		
		
		assertNotNull(hashMap.get(6));
		assertNotNull(hashMap.get(5));
		assertNotNull(hashMap.get(4));
		assertNotNull(hashMap.get(3));
		assertNotNull(hashMap.get(2));
		assertNotNull(hashMap.get(1));
		assertNotNull(hashMap.get(0));

		assertEquals(8, hashMap.get(6).get_coefficient());
		assertEquals(2, hashMap.get(5).get_coefficient());
		assertEquals(9, hashMap.get(4).get_coefficient());
		assertEquals(9, hashMap.get(3).get_coefficient());
		assertEquals(-4, hashMap.get(2).get_coefficient());
		assertEquals(2, hashMap.get(1).get_coefficient());
		assertEquals(-6, hashMap.get(0).get_coefficient());
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
