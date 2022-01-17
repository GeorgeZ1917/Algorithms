//Permutation.java

import java.util.Arrays;

class Perm {
    public static int n = 5;
    static int count = 0;
    public static int perm [] = new int [ n + 1 ];
    public static boolean aux [] = new boolean [ n + 1 ];
    public static void Initialize () {
        Arrays.fill ( aux, true );
        Arrays.fill ( perm, 0 );
    }
    public static void PermAlg ( int i ) {
        int j = 1;
        while ( j <= n ) {
            if ( aux [ j ] == false ) {
                j = j + 1;
                continue;
            }
            else {
                aux [ perm [ i ] ] = true;
                perm [ i ] = j;
                aux [ j ] = false;
            }
            if ( i == n ) {
                System.out.println ( Arrays.toString ( perm ) );
                aux [ j ] = true;
                perm [ i ] = 0;
                count ++;
                return;
            }
            else {
                PermAlg ( i + 1 );
            }
            j = j + 1;
        }
        aux [ perm [ i ] ] = true;
        perm [ i ] = 0;
    }
}

public class Permutation {
    public static void main ( String args [] ) {
        Perm.Initialize();
        Perm.PermAlg ( 1 );
        System.out.println ( "The algorithm generated " + Perm.count + " permutations." ); 
    }
}
