//Permutation.java

/*The PermAlg routine implements an algorithm that generates all n! permutations from 1 to n,
storing them in an array; this is accomplished through backtracking such that the invalid
permutations are skipped, and only the valid ones are printed. The variables are as follows:
n is the arbitrary number to generate all permutations; count keeps track of the number of
permutations generated; the array perm stores the current solution, which is printed 
when i = n, i.e. when a permutation has been formed; aux is an auxiliary array, that keeps track 
which numbers are already in the permutation, such that they do not repeat.*/

import java.util.Arrays;

class Perm {
    private static int n = 5;
    public static int count = 0;
    private static int perm [] = new int [ n + 1 ];
    private static boolean aux [] = new boolean [ n + 1 ];
    public static void Initialize () { // Initialize arrays aux and perm to true and 0, respectively.
        Arrays.fill ( aux, true );
        Arrays.fill ( perm, 0 );
    }
    public static void PermAlg ( int i ) { // The routine that implements the backtracking algorithm.
        int j = 1;
        while ( j <= n ) { // Iterate through all posible n values at current column i.
            if ( aux [ j ] == false ) { // If the current j value is already in the permutation, go to the next iteration.
                j = j + 1;
                continue;
            }
            else { // The current j value is free, add it to the permutation and make it unavailable.
                aux [ perm [ i ] ] = true;
                perm [ i ] = j;
                aux [ j ] = false;
            }
            if ( i == n ) { // The end of the array is reached.
                System.out.println ( Arrays.toString ( perm ) ); // Print the permutation.
                aux [ j ] = true;  // Free the current j value.
                perm [ i ] = 0;    // Free the current permutation value.
                count ++;          // Increase the permutation count (at the end count = n!).
                return;
            }
            else {
                PermAlg ( i + 1 ); // Call the algorithm with the next i value, i.e. the next column of the array.
            }
            j = j + 1;             // Increase j before the next loop.
        }
        aux [ perm [ i ] ] = true; // Free the j value at position i of the permutation.
        perm [ i ] = 0;            // Free the permutation value at column i.
    }
}

public class Permutation {
    public static void main ( String args [] ) {
        Perm.Initialize();
        Perm.PermAlg ( 1 );
        System.out.println ( "The algorithm generated " + Perm.count + " permutations." ); 
    }
}
