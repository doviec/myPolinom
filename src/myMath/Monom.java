
package myMath;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real
 * number and a is an integer (summed a none negative), see:
 * https://en.wikipedia.org/wiki/Monomial The class implements function and
 * support simple operations as: construction, value at x, derivative, add and
 * multiply.
 * 
 * @author Boaz
 *
 */
public class Monom implements function {
	public static final Monom ZERO = new Monom(0, 0);
	public static final Monom MINUS1 = new Monom(-1, 0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();

	public static Comparator<Monom> getComp() {
		return _Comp;
	}

	public Monom(double a, int b) {
		this.set_coefficient(a);
		this.set_power(b);
	}

	public Monom(Monom monom) {
		this(monom.get_coefficient(), monom.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}

	public int get_power() {
		return this._power;
	}

	/**
	 * this method returns the derivative monom of this.
	 * 
	 * @return
	 */
	public Monom derivative() {
		if (this.get_power() == 0) {
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient() * this.get_power(), this.get_power() - 1);
	}
	/**
	 * this method returns the value of the monom by a given x(double)-f(x).
	 * @return
	 */
	public double f(double x) {
		double ans = 0;
		double p = this.get_power();
		ans = this.get_coefficient() * Math.pow(x, p);
		return ans;
	}
	/**
	 * this method returns true if the coefficient is zero.
	 * @return
	 */
	public boolean isZero() {
		return this.get_coefficient() == 0;
	}

	// ***************** add your code below **********************
	/**
	 * by a given string this method checks if its valid and adds to monom’s parameters its values.
	 * @param s
	 */
	public Monom(String s) {

		if (checkString(s)) {

			if (isZero(s)) { 
				set_coefficient(0);
				set_power(0);
			} else {
				int x_index = s.indexOf("x");
				if (x_index > -1) { 
					if (x_index == 0) {
						set_coefficient(1);
					} else {
						set_coefficient(parse(s.substring(0, x_index)));
					}
					int powerIndex = s.indexOf("^");
					if (powerIndex > 0) {
						set_power((int) parse(s.substring(powerIndex + 1)));
					} else {
						set_power(1);
					}

				} else {
					set_coefficient(parse(s));
					set_power(0);
				}
			}
		} else {
			throw new RuntimeException("The String " + s + " is not a Monom");
		}
	}
	/**
	 * this method adds two monoms to one single monom only if they hold the same power.
	 * @param m
	 */
	public void add(Monom m) {

		if (m.get_power() != this.get_power()) {

			throw new RuntimeException("monom's exponents arn't equal therefor we can't add them to one single monom");
		} else {
			set_coefficient(this.get_coefficient() + m.get_coefficient());
		}

	}
	/**
	 * this method subtracts two monoms to one single monom only if they hold the same power
	 * @param m
	 */
	public void subtract(Monom m) {

		if (m.get_power() != this.get_power()) {

			throw new RuntimeException("monom's exponents arn't equal therefor we can't subtract them to one single monom");
		} else {
			set_coefficient(this.get_coefficient() - m.get_coefficient());
		}

	}
	/**
	 * this method multiplies two monoms to one single monom.
	 * @param d
	 */
	public void multipy(Monom d) {

		set_power(this.get_power() + d.get_power());
		set_coefficient(this.get_coefficient() * d.get_coefficient());
	}
	/**
	 * this method returns true if two monoms are equivalent
	 * @param m
	 * @return
	 */
	public boolean equals (Monom m) {

		double eps = m.get_coefficient()-this.get_coefficient();
		if(Math.abs(eps) <= EPSILON && this.get_power() == m.get_power())
		{
			return true;
		}
		else {
			return false;
		}
	
	}
	/**
	 * this method prints the monom
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.get_coefficient());
		if (this.get_power() > 0){
			sb.append("x^").append(this.get_power());
		}
		return sb.toString();
	}

	// ****************** Private Methods and Data *****************
	/**
	 * this method checks if our string represents a valid monom (for example, may have spaces but not characters that aren’t mathematics).
	 * @param cs
	 * @return
	 */
	private boolean checkString(String cs) {

		if (cs.isBlank() || cs.equals(null)) {
			return false;
		} 
		cs = cs.toLowerCase().trim().replaceAll(" ", "");
		String[] arrS = cs.split("x"); 
		if (arrS.length > 2) {
			return false;
		} 
		if (arrS.length == 0 && cs.length() > 1) {
			return false;
		} 
		if (arrS.length == 0 && cs.length() == 1) {
			return true;
		} 
		if(!arrS[0].isEmpty()) {   
			try {
				parse(arrS[0]);
			} catch (Exception e) {
				return false;
			}
		}
		if (arrS.length == 2) {
			if (arrS[1].length() == 1 || arrS[1].charAt(0) != '^' && arrS[1].charAt(1) == '-') {
				return false;
			} 
			else { 
				try {
					Integer.parseInt(arrS[1].substring(1));
				} catch (Exception e) {

					return false;
				}
			}
		}

		return true;
	}
	/**
	 * this method checks if the string is equivalent to the monom zero
	 * @param s
	 * @return
	 */
	private boolean isZero(String s) {

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != '0') {
				return false;
			}
		}
		return true;
	}
	/**
	 * function that returns its string numeric value with its negative/positive symbol.
	 * @param expression
	 * @return
	 */
	private double parse(String expression) { 
		if (expression.indexOf('-') == 0) {
			if (expression.length() == 1) {
				return -1;
			}
			return (-1) * (Double.parseDouble(expression.substring(1)));

		}
		return Double.valueOf(expression);

	}

	private void set_coefficient(double a) {
		this._coefficient = a;
	}

	private void set_power(int p) {
		if (p < 0) {
			throw new RuntimeException("ERR the power of Monom should not be negative, got: " + p);
		}
		this._power = p;
	}

	private static Monom getNewZeroMonom() {
		return new Monom(ZERO);
	}

	private double _coefficient;
	private int _power;
}
