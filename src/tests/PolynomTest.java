package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import myMath.Monom;
import myMath.Polynom;
import myMath.Polynom_able;

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
		assertNull(hashMap.get(5));

		assertNotNull(hashMap.get(4));
		assertNotNull(hashMap.get(3));
		assertNotNull(hashMap.get(2));
		assertNotNull(hashMap.get(0));

		assertEquals(1, hashMap.get(4).get_coefficient());
		assertEquals(3, hashMap.get(3).get_coefficient());
		assertEquals(2, hashMap.get(2).get_coefficient());
		assertEquals(-6, hashMap.get(0).get_coefficient());


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

		String string1 ="2x^2-x+3";
		String string2 ="3x^3+4x^4-2";
		String string3 ="3+2x^2-x ";
		String string4 ="3x^3+4x^4-6";
		String string5 ="x-6";
		String string6 = "x-6";

		Polynom polynom1 = new Polynom(string1);
		Polynom polynom2 = new Polynom(string2);
		Polynom polynom3 = new Polynom(string3);
		Polynom polynom4 = new Polynom(string4);
		Polynom polynom5 = new Polynom(string5);
		Polynom polynom6 = new Polynom(string6);

		boolean isEqual1 = polynom1.equals(polynom3); 
		boolean isEqual2 = polynom1.equals(polynom2); 
		boolean isEqual3 = polynom2.equals(polynom3); 
		boolean isEqual4 = polynom2.equals(polynom4); 
		boolean isEqual5 = polynom6.equals(polynom5); 


		assertEquals(true, isEqual1);
		assertEquals(false, isEqual2);
		assertEquals(false, isEqual3);
		assertEquals(false, isEqual4);
		assertEquals(true, isEqual5);


	}

	@Test
	void testIsZero() {

		String string1 ="2x^2-x";
		String string2 ="0";
		String string3 ="2x-2x";

		Polynom polynom1 = new Polynom(string1);
		Polynom polynom2 = new Polynom(string2);
		Polynom polynom3 = new Polynom(string3);

		boolean isZero1 = polynom1.isZero();
		boolean isZero2 = polynom2.isZero();
		boolean isZero3 = polynom3.isZero();

		assertEquals(false, isZero1);
		assertEquals(true, isZero2);
		assertEquals(true, isZero3);
		
	}

	@Test
	void testRoot() {
		
		String string1 ="9-x^2";
		String string2 ="x^2";
		
		Polynom polynom1 = new Polynom(string1);
		Polynom polynom2 = new Polynom(string2);
		
		double x0 = 1;
		double x1 = 4;
		double x2 = -6;
		double x3 = 8;
		
		double answerRoot1;
		double answerRoot2;
		
		answerRoot1 = polynom1.root(x0, x1, Monom.EPSILON);
	//	answerRoot2 = polynom1.root(x2, x3, Monom.EPSILON);
		
		assertEquals(3, answerRoot1);
	//	assertEquals(0, answerRoot2);
		
		
		
	}

	@Test
	void testCopy() {

		String string1 ="9-x^2";
			

		Polynom_able polynom_able1 = new Polynom(string1);
		Polynom_able polynom_able2 = polynom_able1.copy();
		
		polynom_able1.toString();
		polynom_able2.toString();
		
		assertEquals(true, polynom_able1.equals(polynom_able2));
	}
		

	@Test
	void testDerivative() {

		String string1 ="x^6+4x^3-x+5";
		
		Polynom polynom1 = new Polynom(string1);
		
		polynom1.derivative();
	
		HashMap<Integer, Monom> hashMonom = new HashMap<>();
		Iterator<Monom> iterator = polynom1.iteretor();

		while (iterator.hasNext()) {
			Monom m = iterator.next();
			hashMonom.put(m.get_power(),m);
		}
		
		
		assertNotNull(hashMonom.get(5));
		assertNotNull(hashMonom.get(2));
		assertNotNull(hashMonom.get(0));
				
		assertEquals(6,hashMonom.get(5).get_coefficient() );
		assertEquals(8,hashMonom.get(2).get_coefficient() );
		assertEquals(1,hashMonom.get(0).get_coefficient() );

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

		String s1 ="-2x^2";
		String s2 ="3x^2- 2x^4 -3x";


		Monom m1 = new Monom(s1);
		Polynom p2 = new Polynom(s2);

		p2.multiply(m1);

		HashMap<Integer, Monom> hash = new HashMap<>();

		Iterator<Monom> iterator = p2.iteretor();
		while (iterator.hasNext()) {
			Monom m = iterator.next();
			hash.put(m.get_power(),m);
		}

		assertNotNull(hash.get(6));
		assertNotNull(hash.get(4));
		assertNotNull(hash.get(3));


		assertEquals(4, hash.get(6).get_coefficient());
		assertEquals(-6, hash.get(4).get_coefficient());	
		assertEquals(6, hash.get(3).get_coefficient());	
	}

	@Test
	void testToString() {
		
		String s1 ="-2x^2+6x";
		
		Polynom p2 = new Polynom(s1);
		
	
	}

}
