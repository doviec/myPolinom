package myMath;
import java.util.ArrayList;
/**
 * This class represents a simple (naive) tester for the Monom class, 
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
0) 2.0    	isZero: false	 f(0) = 2.0  <br>
1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
3) 0    	isZero: true	 f(3) = 0.0  <br>
 *****  Test2:  *****  <br>
0) 0    	isZero: true  	eq: true  <br>
1) -1.0    	isZero: false  	eq: true  <br>
2) -1.3x    	isZero: false  	eq: true  <br>
3) -2.2x^2    	isZero: false  	eq: true  <br>
 */
public class MonomTest {
	public static void main(String[] args) {
		test1();
		test2();
	}
	private static void test1() {
		System.out.println("*****  Test1:  *****");
		String[] monoms = {"2", "-x","-3.2x^2","0"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
		}
	}
	private static void test2() {
		System.out.println("*****  Test2:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));

		for(int i=0;i<monoms.size();i++) {
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			Monom m1 = new Monom(s);
			boolean e = m.equals(m1);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e);
		}
		// test1
		String st1 = "+++";
		String st2 = "+2";
		String st3 = "+3";
		String st4 = "x^5";
		String st5 = "1.4.x";
		String st6 = "4^-2";
		String st7 = "  ";
		String st8 = "0";

		System.out.println("test power and coefficient of monom");
		Monom m1 = new Monom(st1);
		Monom m2 = new Monom(st2);
		Monom m3 = new Monom(st3);
		Monom m4 = new Monom(st4);
		Monom m5 = new Monom(st5);
		Monom m6 = new Monom(st6);
		Monom m7 = new Monom(st7);
		Monom m8 = new Monom(st8);

		System.out.println(m1.toString());
		System.out.println(m2.toString());
		System.out.println(m3.toString());
		System.out.println(m4.toString());
		System.out.println(m5.toString());
		System.out.println(m6.toString());
		System.out.println(m7.toString());
		System.out.println(m8.toString());

		System.out.println("test adding monoms");
		m1.add(m2); 
		m3.add(m4); 
		m7.add(m8);
		m8.add(m7); 
		System.out.println(m1.toString());
		System.out.println(m3.toString()); 
		System.out.println(m7.toString());
		System.out.println(m8.toString());


		System.out.println("test multyplies monoms"); 
		m1.multipy(m5); 
		m3.multipy(m2);
		m5.multipy(m6); 
		m7.multipy(m8); 
		System.out.println(m1.toString());
		System.out.println(m3.toString()); 
		System.out.println(m5.toString());
		System.out.println(m7.toString());


	}

}

