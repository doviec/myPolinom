
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
	/** init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "3-3.4x+13.1x-1.2-3X^2-3.1"};
	 * @param s: is a string represents a Polynom
	 */

	public Polynom(String s) {
		this();     
		int start = 0;
		String trimmedPoli = s.replaceAll("\\s","");

		int index;
		if (!trimmedPoli.isEmpty() && (trimmedPoli.charAt(0) == '+' || trimmedPoli.charAt(0) == '-')) {
			index = 1;
		} else {
			index = 0;
		}

		for (; index < trimmedPoli.length(); index++) {
			if(trimmedPoli.charAt(index) == '+' || trimmedPoli.charAt(index) == '-'){
				Monom monom = new Monom(trimmedPoli.substring(start, index));  
				add(monom);
				start = index;
			}
		}if (start != trimmedPoli.length()) {
			Monom monom = new Monom (trimmedPoli.substring(start));
			add(monom);
		}
	}
	/**
	 * with the help of f function in monom class this method returns the value of the Polynom by a given x.
	 */
	@Override
	public double f(double x) {

		double valueF = 0;
		Iterator<Monom> iterator = iteretor();
		while (iterator.hasNext()) {

			Monom monom = iterator.next();
			valueF += monom.f(x);
		}
		return valueF;
	}
	/**
	 * deletes a monom if its coefficient is zero
	 * @param m1
	 */
	public void deleteMonomIfZero(Monom m1) {

		if (m1.get_coefficient() == 0) {
			monomsMap.remove(m1.get_power());
		}
	}
/**
 * adds to our Polynom a given polynom_able
 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> iterator = p1.iteretor();
		while (iterator.hasNext()) {
			Monom m = iterator.next();
			add(m);
		}
	}

	/**
	 * adds a monom to our polynom & deletes it if its zero
	 */
	@Override
	public void add(Monom m1) {
		if (!m1.isZero()) {
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
	}
	/**
	 * subtracts a monom from the polynom & deletes it if its zero.
	 * @param m1
	 */
	public void subtract(Monom m1) {

		Monom negative = new Monom(-1 * m1.get_coefficient(), m1.get_power());
		add(negative);
	}
/**
 * subtracts a polynom from the polynom
 */
	@Override
	public void substract(Polynom_able p1) {              
		Iterator<Monom> iterator = p1.iteretor();
		while (iterator.hasNext()) {
			Monom m = iterator.next();
			subtract(m);
		}
	}	
/**
 * adds a polynom to a polynom.
 */
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
/**
 * checks if two polynoms are equal.
 */
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
				if (!m.equals(monomsMap.get(m.get_power()))) {          
					return false;
				}
			}
		}
		if (monomsMap.size() != count) {
			return false;
		}

		return true;
	}
/**
 * checks if the polynom is zero
 */
	@Override
	public boolean isZero() {

		return (monomsMap.size() == 0);
	}
/**
 * gets two double values and epsilon and returns if there is a value between the two that gives us zero if we send it to f(x) and is smaller then epsilon.
 */
	@Override
	public double root(double x0, double x1, double eps) { 

			if (this.f(x0) * this.f(x1) == 0) {
			if (this.f(x0) == 0) {
				return x0;
			}
			else {
				return x1;
			}
		}
		if (this.isZero()) {
			return x0;
		}
		if (f(0) == 0) {
			return 0;
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
/**
 * this method returns a polynom_able which is a copy of our polynom.
 */
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
/**
 * returns the polynom with its derivative values.
 */
	@Override
	public Polynom_able derivative() {                      
		Polynom_able polynom_able = new Polynom();
		Iterator<Monom> iterator = this.iteretor();
		while (iterator.hasNext()) {
			Monom monom = iterator.next();
			monom = monom.derivative();
			polynom_able.add(monom);
		}
		return polynom_able;
	}
/**
 * gets two double values abd epsilon and returns the are of the function between and the x axis.
 */
	@Override
	public double area(double x0, double x1, double eps) {

		if (x0 >= x1) {
			return 0;
		}
		double temp, sumArea = 0;
		temp = x0+eps;

		while (temp <=  x1) {
			sumArea+=(this.f(temp))*eps;
			temp+=eps;
		}

		return sumArea;
	}
/**
 * ()-with this method we may iterate through  hashmap or polynom etc. and get its values (monom etc.).
 */
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
/**
 * this method is used to copy a hashmap to an arraylist. Used for the iterator function to prevent us from trying to acsses  data which does not exist (null).
 * @return
 */
	private ArrayList<Monom> convertMapToList() {
		ArrayList<Monom> list = new ArrayList<Monom>();
		for (Map.Entry<Integer, Monom> entry : monomsMap.entrySet()) {
			list.add(new Monom(entry.getValue()));
		}
		return list;
	}
	/**
	 * this method multiplies our Polynom by one monom.
	 */
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
/**
 * this method prints our Polynom
 */
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
