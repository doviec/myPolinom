
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
		this();     
		int start = 0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '+' || s.charAt(i) == '-'){
				Monom monom = new Monom(s.substring(start, i));  
				add(monom);
				start = i;
			}
		}if (start != s.length()) {
			Monom monom = new Monom (s.substring(start));
			add(monom);
		}
	}
	@Override
	public double f(double x) {

		int ans = 0;
		Iterator<Monom> iterator = iteretor();
		while (iterator.hasNext()) {

			Monom monom = iterator.next();
			ans += monom.f(x);
		}
		return ans;
	}
	public void deleteMonomIfZero(Monom m1) {
		
		if (m1.get_coefficient() == 0) {
			monomsMap.remove(m1.get_power());
		}
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

		Monom monom = monomsMap.get(m1.get_power());
		if (monom == null) {
			monomsMap.put(m1.get_power(), m1);

		}else {
			if (m1.get_power() == monom.get_power()) {
				if (m1.get_coefficient() + monom.get_coefficient() == 0)
				{
					monomsMap.remove(monom.get_power());
				} else {
					monom.add(m1); 
				}
			}
		}
	}
	public void subtract(Monom m1) {

		Monom negative = new Monom(-1 * m1.get_coefficient(), m1.get_power());
		add(negative);
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
		Iterator<Monom>iterator = p1.iteretor();
		while (iterator.hasNext()) {
			Monom m = iterator.next();

			Polynom_able tempPol = this.copy();
			tempPol.multiply(m);
			p2.add(tempPol);
		}
		monomsMap = p2.monomsMap;
	}

	@Override
	public boolean equals(Polynom_able p1) {
		Iterator<Monom> iterator = p1.iteretor();
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

		return (monomsMap.size() == 0);
	}

	@Override
	public double root(double x0, double x1, double eps) { 

		if (this.f(x0) * this.f(x1) == 0) {
			if (this.f(x0) == 0) return x0;
			else return x1;
		}
		if (this.isZero()) {
			return x0;
		}
		
		if (this.f(x0) * this.f(x1) > 0) {
			throw new RuntimeException("The values x0 and x1 arn't correct");
		}

		double smallThenEps=((x0+x1)/2);
		while (Math.abs(this.f(smallThenEps))>eps) {
			if (this.f(smallThenEps)*this.f(x0)<0) {
				x1=smallThenEps;
			}
			else {
				x0=smallThenEps;
			}
			smallThenEps=((x0+x1)/2);
		}


		return smallThenEps;
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
	public Polynom_able derivative() {                        //has to be checked
		Polynom_able polynom_able = new Polynom();
		Iterator<Monom> iterator = iteretor();
		while (iterator.hasNext()) {
			Monom monom = iterator.next();
			monom.derivative();
			polynom_able.add(monom);
		}
		return polynom_able;
	}

	@Override
	public double area(double x0, double x1, double eps) {

		if (x0 == x1) return 0;
		double temp, sumArea = 0;
		temp = Math.min(x1, x0)+eps;

		while (temp <= Math.max(x0, x1)) {
			sumArea+=(this.f(temp))*eps;
			temp+=eps;
		}

		return sumArea;
	}

	@Override
	public Iterator<Monom> iteretor() {

		return new Iterator<Monom>() {

			ArrayList<Monom>arrayList = convertMapToList();
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
			list.add(new Monom(entry.getValue()));
		}
		return list;
	}
	@Override
		public void multiply(Monom m1) {        
		Polynom p = new Polynom();
		Iterator<Monom> iterator = iteretor();
		while (iterator.hasNext()){
			Monom monom = iterator.next();
			monom.multipy(m1);
			p.add(monom);
		}
		this.monomsMap = p.monomsMap;
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
