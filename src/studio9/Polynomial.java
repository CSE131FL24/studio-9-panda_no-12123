package studio9;

import java.util.LinkedList;

public class Polynomial {

    private LinkedList<Double> list;

    /**
     * Constructs a Polynomial with no terms yet.
     */
    public Polynomial() {
        list = new LinkedList<>(); // Initialize an empty list
    }

    /**
     * Adds a term to the polynomial.
     * 
     * @param coeff the coefficient of the term to add
     */
    public void addTerm(double coeff) {
        list.add(coeff);
    }

    /**
     * Returns a String representation of the polynomial in the proper form:
     * Cx^N + Cx^N-1 + ... + Cx + C
     */
    @Override
    public String toString() {
        if (list.isEmpty()) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        int degree = list.size() - 1;
        
        for (int i = 0; i < list.size(); i++) {
            double coeff = list.get(i);
            
            if (coeff != 0) {
                if (sb.length() > 0 && coeff > 0) {
                    sb.append(" + ");
                } else if (coeff < 0) {
                    sb.append(" - ");
                }

                double absCoeff = Math.abs(coeff);
                if (absCoeff != 1 || degree - i == 0) {
                    sb.append(absCoeff);
                }

                if (degree - i > 0) {
                    sb.append("x");
                    if (degree - i > 1) {
                        sb.append("^").append(degree - i);
                    }
                }
            }
        }
        
        return sb.toString();
    }

    /**
     * Evaluates the polynomial for a given value of x.
     * 
     * @param x the value at which to evaluate the polynomial
     * @return the evaluated value
     */
    public double evaluate(double x) {
        double result = 0;
        int degree = list.size() - 1;

        for (int i = 0; i < list.size(); i++) {
            result += list.get(i) * Math.pow(x, degree - i);
        }
        
        return result;
    }

    /**
     * Computes the derivative of the polynomial.
     * 
     * @return a new Polynomial representing the derivative
     */
    public Polynomial derivative() {
        Polynomial derivative = new Polynomial();
        int degree = list.size() - 1;

        for (int i = 0; i < list.size() - 1; i++) {
            double coeff = list.get(i);
            derivative.addTerm(coeff * (degree - i));
        }

        return derivative;
    }

    /**
     * Checks equality between this polynomial and another object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Polynomial))
            return false;
        
        Polynomial other = (Polynomial) obj;
        return this.list.equals(other.list);
    }
}
