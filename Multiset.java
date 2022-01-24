//Multiset.java

/*The MultiAlg routine implements an algorithm that generates all k permutations of an arbitrary
multiset, storing them in the array multiset. This is accomplished through backtracking, such
that the invalid permutations are skipped since the start of the main algorithm's loop. 
Each time the last element of the array is reached, i. e. i = n, the current permutation is 
printed and the algorithm backtracks to the previous position. The Initialize method works 
as follows. First, it sets both aux and multiset to 0, then for all values of the arbitrary 
permutation, it adds the number of times each one appears, to the array aux. Both aux and 
multiset are initialized to n + 1, as index 0 is never used to store the first permutation value.*/

import java.util.Arrays;

class Multi {
    private static int n = 5;                          // Number of elements in the permutation.
    public static int count = 0;                        // Count of total permutations generated.
    private static int max = Integer.MIN_VALUE;         // Max value, used to control the main while loop.
    private static int aux [] = new int [ n + 1 ];      // Auxiliary array, stores the number of elements free for each one.
    public static int multiset  [] = new int [ n + 1 ]; // The array that holds the current permutation.
    public static void Initialize ( int array [] ) {
        Arrays.fill ( aux, 0 );
        Arrays.fill ( multiset, 0 );
        for ( int i = 0; i < n; i ++ ) {
            if ( array [ i ] > max ) {                  // Get the maximum value of the complete permutation.
                max = array [ i ]; 
            }
            aux [ array [ i ] ] += 1;                   // Increase aux by one at index of the permutation's value.
        }
    }
    public static void MultiAlg ( int i ) { // The routine that implements the backtracking algorithm.
        int j = 0; // Counter for the possible values the permutation has.
        int k = 1; // The current permutation value. Only increases when the current value is exhausted or the loop ends.
        while ( j <= max ) { // Iterate through all posible n values at current column i, backtracking when the max value is reached.
            if ( aux [ k ] == 0 ) { // If the current k value is not free, increase k and j, and go to next iteration.
                k = k + 1;
                j = j + 1;
                continue;
            }
            else { // The current k value is still free.
                aux [ multiset [ i ] ] += 1;    // Free the current permutation value.
                multiset [ i ] = k;             // Assign k to the current permutation value.
                aux [ k ] -= 1;                 // Reduce aux [ k ] by one.
            }
            if ( i == n ) { // The end of the array is reached.
                System.out.println ( Arrays.toString ( multiset ) ); // Print the permutation.
                aux [ k ] += 1;                 // Free the current k value.
                multiset [ i ] = 0;             // Free the current permutation value.
                count ++;                       // Increase the permutation count.
                return;
            }
            else {
                MultiAlg ( i + 1 ); // Call the algorithm with the next i value, i.e. the next column of the array.
            }
            k = k + 1;  // Increase the k value to try the next one possible.
            j = j + 1;  // Increase j to check if the maximum is reached.
        }
        aux [ multiset [ i ] ] += 1; // Free the value at position i of the permutation.
        multiset [ i ] = 0;
    }
}

public class Multiset {
    public static void main ( String args [] ) {
        int theSet [] = { 1, 1, 2, 2, 3 };
        Multi.Initialize ( theSet );
        Multi.MultiAlg ( 1 );
        System.out.println ( "The algorithm generated " + Multi.count + " multiset permutations." ); 
    }
}
