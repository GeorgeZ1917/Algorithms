//Derangement.java

/*The DeranAlg routine implements an algorithm that generates all derangements from 1 to n,
storing them in an array; this is accomplished through backtracking such that the invalid
permutations are skipped, and only the valid ones are printed. The variables are as follows:
n is the arbitrary number to generate all derangements of this size; count keeps track of the 
number of derangements generated; the array deran stores the current solution, which is printed 
when a the function isValid returns true, i.e. when a derangement has been formed; aux is an 
auxiliary array that keeps track of which numbers are already in the permutation, such that they 
do not repeat.*/

import java.util.Arrays;

class ClassDerangement {
    private static int n = 5;
    static int count = 0;
    private static int deran [] = new int [ n + 1 ];
    private static boolean auxSmall [] = new boolean [ n + 1 ];
    public static void Initialize () { // Initialize arrays aux and deran to true and 0, respectively.
        Arrays.fill ( auxSmall, true );
        Arrays.fill ( deran, 0 );
    }
    private static boolean isValid () { // Returns false if the current permutation isn't a derangement, otherwise true.
        for ( int i = 1; i <= n; i ++ ) {
            if ( deran [ i ] == i || deran [ i ] == 0 || auxSmall [ i ] == true ) {
                return false;
            }
        }
        return true;
    }
    private static void ClearArray () { // Used to clear the aux array before calling DeranAlg with i + 1.
        Arrays.fill ( auxSmall, true );
        for ( int i = 1; i <= n; i ++ ) {
            if ( deran [ i ] != 0 ) {
                auxSmall [ deran [ i ] ] = false;
            }
        }
    }
    public static void DerangAlg ( int i ) { // The routine that implements the backtracking algorithm.
        int j = 1;
        while ( j <= n ) { // Iterate through all posible n values at current column i.
            if ( auxSmall [ j ] == false || i == j ) { // If the current j value is already in the permutation, or i == j
                                                       // go to the next iteration.
                j = j + 1;
                continue;
            }
            else { // The current j value is free, add it to the permutation and make it unavailable.
                auxSmall [ j ] = false;
                deran [ i ] = j;
            }
            if ( isValid () ) { // If the permutaton is a derangement
                System.out.println ( Arrays.toString ( deran ) ); // Print the derangement.
                auxSmall [ j ] = true;                            // Free the j value in the derangement.
                deran [ i ] = 0;                                  // Free the current permutation value.
                count ++;                                         // Increase the derangement count.
                return;
            }
            else if ( i == n ) {
                return;                  // If the end is reached, backtrack.
            }
            else { // Clear the aux array and call the algorithm with the next i value, i.e. the next column of the array.
                ClearArray();
                DerangAlg ( i + 1 );
            }
            j = j + 1;                   // Increase j before the next loop.
        }
        auxSmall [ deran [ i ] ] = true; // Free the j value at position i of the permutation.
        deran [ i ] = 0;                 // Free the permutation value at column i.
    }
}

public class Derangement {
    public static void main ( String args [] ) {
        ClassDerangement.Initialize();
        ClassDerangement.DerangAlg ( 1 );
        System.out.println ( "The algorithm generated " + ClassDerangement.count + " derangements." );     
    }
}
