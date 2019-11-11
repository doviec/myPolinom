package myMath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */

public class Polynom implements Polynom_able{

	private HashMap<Integer,Monom> monomsMap;

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		monomsMap = new HashMap<>();
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		this();     //בנאי דיפולטיבי
		int start = 0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '+' || s.charAt(i) == '-'){
				Monom m = new Monom(s.substring(start, i));          //לבדוק מה קורה אם מתחיל בפלוס או מינוס בעיה
				monomsMap.put(m.get_power(), m);         //adds to hashmap where hash key is monom power for futre help
				start = i;
			}
		}if (start != s.length()) {
			Monom m = new Monom (s.substring(start));
			monomsMap.put(m.get_power(), m);
		}
	}
	@Override
	public double f(double x) {

		int ans = 0;
		Iterator<Monom> iterator = iteretor();
		while (iterator.hasNext()) {

			Monom m = iterator.next();
			ans += m.f(x);
		}
		return ans;
	}

	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> iterator = p1.iteretor();
		while (iterator.hasNext()) {
			Monom m = iterator.next();
			add(m);
		}
	}

	/**
	 * 
	 */
	@Override
	public void add(Monom m1) {

		if (monomsMap.get(m1.get_power()) == null) {
			monomsMap.put((m1.get_power()), m1);
		}else {
			Monom m2 = monomsMap.get(m1.get_power());
			m2.add(m1);                                         //m2 gets object from list by reference thats why when we change m2 we also change the original-m1 (if we did new it wouldnt work0
		}

	}
	public void subtract(Monom m1) {

		if (monomsMap.get(m1.get_power()) == null) {
			monomsMap.put((m1.get_power()), m1);
		}else {
			Monom m2 = monomsMap.get(m1.get_power());
			m2.subtract(m1);                                       
		}
	}

	@Override
	public void substract(Polynom_able p1) {              
		Iterator<Monom> iterator = p1.iteretor();
		while (iterator.hasNext()) {
			Monom m = iterator.next();
			subtract(m);
		}
	}	

	@Override
	public void multiply(Polynom_able p1) {

		Polynom p2 = new Polynom();
		Iterator<Monom>iterator = iteretor();
		while (iterator.hasNext()) {

			Monom m = iterator.next();
			Polynom_able temp = this.copy();
			temp.multiply(m);
			p2.add(temp);
		}
		monomsMap = p2.monomsMap;
	}

	@Override
	public boolean equals(Polynom_able p1) {
		Iterator<Monom> iterator = iteretor();
		int count = 0;
		while (iterator.hasNext()) {
			Monom m = iterator.next();
			count++;
			if (monomsMap.get(m.get_power()) == null) {             
				return false;
			}else {
				if (!m.equals(monomsMap.get(m.get_power()))) {          //checks the whole monom
					return false;
				}
			}
		}
		if (monomsMap.size() != count) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isZero() {
		
		return (monomsMap.size() == 1 && monomsMap.get(0) != null && monomsMap.get(0).isZero());
	}

	@Override
	public double root(double x0, double x1, double eps) {  //מה שמאפס-שורש
		return 0;
	}

	@Override
	public Polynom_able copy() {
		Polynom_able polynom_able = new Polynom();
		Iterator<Monom> iterator = this.iteretor();
		while (iterator.hasNext()) {
			Monom m = iterator.next();
			polynom_able.add(m);
		}
		return polynom_able;
	}

	@Override
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Monom> iteretor() {

		ArrayList<Monom>arrayList = convertMapToList();

		return new Iterator<Monom>() {
			int index = 0;	
			@Override
			public Monom next() {
				return arrayList.get(index++);

			}

			@Override
			public boolean hasNext() {
				if (index < arrayList.size()) return true;

				return false;
			}
		};

	}



	private ArrayList<Monom> convertMapToList() {
		ArrayList<Monom> list = new ArrayList<Monom>();
		for (Map.Entry<Integer, Monom> entry : monomsMap.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}
	@Override
	public void multiply(Monom m1) {         //needs a check

		Polynom p = new Polynom();

		for(int i=0;i<=monomsMap.size();i++) {
			if (monomsMap.get(i)!=null) {
				Monom m2 = monomsMap.get(i);
				m2.multipy(m1);
				p.add(m2);
			}
		}
		this.monomsMap=p.monomsMap;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<Monom> iterator = this.iteretor();
		while(iterator.hasNext()) {
			Monom m = iterator.next();
			if (m.get_coefficient()>0) {
				sb.append("+");
			}
			sb.append(m.toString());
		}
		String polynomStr;
		if (sb.length() > 0 && sb.charAt(0) == '+') {
			polynomStr = sb.substring(1);
		} else {
			polynomStr = sb.toString();
		}
		return polynomStr;
	}





}
