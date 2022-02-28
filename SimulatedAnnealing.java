// SimulatedAnnealing.java

/* The function SimAnneal implements the simulated annealing heuristic, which, given the function expressed as a polynomial,
returns the pair (x,y) such that y is minimum. The cost function is computed numerically by funct and returned as a double,
and SimAnneal is called from main as a static method. */

import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

class SimulatedAnnealing {
    static double funct ( double x ) {
        double constVal = Math.pow ( 10, - 9 );
        double result = constVal * ( x - 5 ) * ( x + 79 ) * ( x - 315 ) * ( x + 798 ) * ( x + 3129 ) * ( x + 7109 ); // Compute an arbitrary polynomial.
        return result; // Return result as double
    }
    static void SimAnneal () {
        int val = 1000000; // An arbitrary value to define the range.
        double s = ThreadLocalRandom.current().nextDouble ( - val, val ); // A random number between - val and val.
        double temp = 1;
        double a = 0.95; // Cooling constant.
        double k = 1.38064852 * Math.pow ( 10, - 23 );  // Boltzmann's constant.
        for ( int i = 0; i < 1000; i ++ ) {
            for ( int j = 0; j < 500; j ++ ) {
                double si = ThreadLocalRandom.current().nextDouble ( - val, val ); // A random value.
                double delta = funct ( s ) - funct ( si ); // The difference in cost of the function with the new value.
                double exp = ( - delta / funct ( s ) ) / ( k * temp ); // The exponential.
                if ( delta > 0 ) { // If the new value is better than the previous one, update the previous.
                    s = si;
                }
                else if ( Math.pow ( Math.E, exp ) > Math.random() ) { // If not, update it with probability in terms of the temp value.
                    s = si;
                }
            }
            temp *= a; // Update the current temporary value.
        }
        System.out.println( s + " " + funct ( s ) ); // Print the result.
    }
    public static void main ( String args [] ) {
        SimulatedAnnealing.SimAnneal ();
    }
}