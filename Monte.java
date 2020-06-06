// CS 1538 Fall 2009
// Simple demonstration of Monte Carlo simulation to evaluate three integrals.
// These integrals are fairly simple, but Monte Carlo methods can be used to
// evaluate integrals that have no closed form and that are very difficult
// to evaluate by standard numerical methods.

import java.util.*;
public class Monte
{
	public static void main(String [] args)
	{
		int iter = Integer.parseInt(args[0]);
		// This first example evaluates the integral of sin(x) from x = 0
		// to x = Math.PI.  Note that his could be done easily in a closed
		// form using simple integral calculus (the solution is 2.0).  
		// However, it shows how Monte Carlo methods can be used.
		double max = Math.PI;
		double min = 0;
		double diff = max - min;
		Random r = new Random();
		double sum = 0;
		double temp;
		for (int i = 0; i < iter; i++)
		{
			double rval = r.nextDouble();
			double x = min + (max - min) * rval; // assign x in correct range
			sum += Math.sin(x);
		}
		double ans = diff * sum / iter;
		System.out.println("Integral of sin(x) from 0 to PI = " + ans);

		// This second example evaluates the integral of
		// e^(-x * cos(Math.PI*x)) from x = 0 to x = 1.  This integral 
		// cannot be calculated in an analytical closed form.  However, it
		// can be solved using other (deterministic) numerical techniques.
		max = 1;
		min = 0;
		diff = max - min;
		sum = 0;
		for (int i = 0; i < iter; i++)
		{
			double x = r.nextDouble();
			temp = Math.cos(Math.PI * x);
			temp = temp * (-x);
			sum += Math.exp(temp);
		}
		ans = diff * sum / iter;
		System.out.println("Integral of e^(-x * cos(Math.PI*x)) from 0 to 1 = " + ans);

		// This third example is something we just covered in class --
		// the cumulative distribution function for an exponential
		// distribution, or the integral of
		// lambda * e^(-lambda * t) from t = 0 to t = x.
		// We have used the closed form for this in some of our examples
		// already (see Eq. 5.28 in the text).  However, we can see how the
		// values can also be calculated using Monte Carlo methods.
		Scanner inScan = new Scanner(System.in);
		System.out.println("Value for lambda?");
		double lambda = inScan.nextDouble();
		System.out.println("Value for x?");
		double x = inScan.nextDouble();
		diff = x - 0;
		sum = 0;
		for (int i = 0; i < iter; i++)
		{
			double rval = r.nextDouble();
			double t = rval * x;  // t is from 0 to x
			temp = -lambda * t;
			temp = Math.exp(temp);
			sum += temp * lambda;
		}
		ans = diff * sum / iter;
		System.out.println("Integral of " + lambda + " * e^(-" + lambda + " * t) from 0 to " +
							x + " = " + ans);
		temp = -lambda * x;
		temp = Math.exp(temp);
		temp = 1 - temp;
		System.out.println("The analytical answer is " + temp);
	}
}

