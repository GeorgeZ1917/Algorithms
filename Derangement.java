//Derangement.java

import java.util.Arrays;

class ClassDerangement {
    public static int n = 5;
    static int count = 0;
    public static int deran [] = new int [ n + 1 ];
    public static boolean auxSmall [] = new boolean [ n + 1 ];
    public static void Initialize () {
        Arrays.fill ( auxSmall, true );
        Arrays.fill ( deran, 0 );
    }
    public static boolean isValid () {
        for ( int i = 1; i <= n; i ++ ) {
            if ( deran [ i ] == i | deran [ i ] == 0 | auxSmall [ i ] == true ) {
                return false;
            }
        }
        return true;
    }
    public static void ClearArray () {
        for ( int i = 1; i <= n; i ++ ) {
            auxSmall [ i ] = true;
        } 
        for ( int i = 1; i <= n; i ++ ) {
            if ( deran [ i ] != 0 ) {
                auxSmall [ deran [ i ] ] = false;
            }
        }
    }
    public static void DerangAlg ( int i ) {
        int j = 1;
        while ( j <= n ) {
            if ( auxSmall [ j ] == false | i == j ) {
                j = j + 1;
                continue;
            }
            else {
                auxSmall [ j ] = false;
                deran [ i ] = j;
            }
            if ( isValid () ) {
                System.out.println ( "Is valid: " + Arrays.toString ( deran ) );
                auxSmall [ j ] = true;
                deran [ i ] = 0;
                count ++;
                return;
            }
            else if ( i == n ) {
                return;
            }
            else {
                ClearArray();
                DerangAlg ( i + 1 );
            }
            j = j + 1;
        }
        auxSmall [ deran [ i ] ] = true;
        deran [ i ] = 0;
    }
}

public class Derangement {
    public static void main ( String args [] ) {
        ClassDerangement.Initialize();
        ClassDerangement.DerangAlg ( 1 );
        System.out.println ( "The algorithm generated " + ClassDerangement.count + " derangements." );     
    }
}
