
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

	public double f(double x) {
		double ans = 0;
		double p = this.get_power();
		ans = this.get_coefficient() * Math.pow(x, p);
		return ans;
	}

	public boolean isZero() {
		return this.get_coefficient() == 0;
	}

	// ***************** add your code below **********************
	public Monom(String s) {

		if (checkString(s)) {

			if (isZero(s)) { // checks if the string equals to zero
				set_coefficient(0);
				set_power(0);
			} else {
				int x_index = s.indexOf("x");
				if (x_index > -1) { // checks if has x in the string
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

	public void add(Monom m) {

		if (m.get_power() != this.get_power()) {

			throw new RuntimeException("monom's exponents arn't equal therefor we can't add them to one single monom");
		} else {
			set_coefficient(this.get_coefficient() + m.get_coefficient());
		}

	}

	public void subtract(Monom m) {

		if (m.get_power() != this.get_power()) {

			throw new RuntimeException("monom's exponents arn't equal therefor we can't subtract them to one single monom");
		} else {
			set_coefficient(this.get_coefficient() - m.get_coefficient());
		}

	}

	public void multipy(Monom d) {

		set_power(this.get_power() + d.get_power());
		set_coefficient(this.get_coefficient() * d.get_coefficient());
	}

	public boolean equals (Monom m) {

		return (this.get_power() == m.get_power() && this.get_coefficient() == m.get_coefficient());
	}

	public String toString() {
		if (get_power() == 0) { // if theres no power (no x)
			return "monom: " + get_coefficient();
		}
		if (get_coefficient() == 0 && get_power() == 0) {
			return "monom: 0";
		}
		return "monom: " + get_coefficient() + "x^" + get_power();
	}
	// you may (always) add other methods.

	// ****************** Private Methods and Data *****************

	private boolean checkString(String cs) {

		if (cs.isBlank() || cs.equals(null)) {
			return false;
		} // checks if the string is blank or equal to null
		cs = cs.toLowerCase().trim().replaceAll(" ", "");
		String[] arrS = cs.split("x"); // splits the string wherever theres a x
		if (arrS.length > 2) {
			return false;
		} // checks if there was more then two x
		if (arrS.length == 0 && cs.length() > 1) {
			return false;
		} // incase the string was only made of x's (more then one)
		if (arrS.length == 0 && cs.length() == 1) {
			return true;
		} // the string is x;
		if(!arrS[0].isEmpty()) {       //in monom מצורה x^a
			try {
				parse(arrS[0]);
			} catch (Exception e) {
				return false;
			}
		}
		if (arrS.length == 2) {
			if (arrS[1].length() == 1 || arrS[1].charAt(0) != '^' && arrS[1].charAt(1) == '-') {
				return false;
			} // checks if the power has only one char or more then one
			else { // ....but dosent begin with ^
				try {
					Integer.parseInt(arrS[1].substring(1));
				} catch (Exception e) {

					return false;
				}
			}
		}

		return true;
	}

	private boolean isZero(String s) { // function that checks if the string is only with zero charachters

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != '0') {
				return false;
			}
		}
		return true;
	}

	private double parse(String expression) { // function thats returns a number with its negitive/positive sign

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
