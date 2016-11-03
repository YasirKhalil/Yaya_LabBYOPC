/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yasir
 */




public class ThreeLaneAssembly {
    public static void main(String[] args) {
        int[][] l1 = {{4, 5, 3, 2},
                         {2, 10, 1, 4}};
        int[][] l2 = {{0, 7, 4, 5},
                         {0, 9, 2, 8}};
        
        int[] enterance = {10, 12, 12};
        int[] exit = {18, 7, 12};
        System.out.println(DynamicProgramming(l1, l2, enterance, exit));
        System.out.println(PCAssemblyRecursion(l1, l2, enterance, exit));
    }

    public static int PCAssemblyRecursion(int[][] l1, int[][] l2, int[] enter, int[] exit){
        int n = l1[0].length-1;
        return Math.min(Recursion(l1,l2, enter, exit, n, 0) + exit[0], 
                                Recursion(l1,l2, enter, exit, n, 1) + exit[1]);
    }

    public static int DynamicProgramming(int[][] l1, int[][] t, int[] e, int[] x){
        int n = l1[0].length;
        int[] Time1 = new int[n];
        int[] Time2 = new int[n];
        int[] Time3 = new int[n];

        Time1[0] = e[0] + l1[0][0];
        Time2[0] = e[1] + l1[1][0];

        for(int i=1; i<n; i++){
            Time1[i] = Math.min(Time1[i-1]+l1[0][i], Time2[i-1]+t[1][i]+l1[0][i]);
            Time2[i] = Math.min(Time2[i-1]+l1[1][i], Time1[i-1]+t[0][i]+l1[1][i]);
        }

        return Math.min(Time1[n-1]+x[0], Time2[n-1]+x[1]);
    }
    
    
    public static int Recursion(int[][] l1, int[][] l2, int[] e, int[] x, int n, int lane){  
    if(n == 0){  
        return e[lane] + l1[lane][0];  
    }  

    int T0 = Integer.MAX_VALUE;  
    int T1 = Integer.MAX_VALUE;  
    int T2 = Integer.MAX_VALUE;
    if(lane == 0){      
        T0 = Math.min(Recursion(l1, l2, e, x, n-1, 0) + l1[0][n],             
                            Recursion(l1, l2, e, x, n-1, 1) + l2[1][n] + l1[0][n]);    
    }else if(lane == 1){       
        T1 = Math.min(Recursion(l1, l2, e, x, n-1, 1) + l1[1][n],             
                             Recursion(l1, l2, e, x, n-1, 0) + l2[0][n] + l1[1][n]);   
    }  
    else if(lane == 2){       
        T2 = Math.min(Recursion(l1, l2, e, x, n-1, 1) + l1[1][n],             
                             Recursion(l1, l2, e, x, n-1, 0) + l2[0][n] + l1[1][n]);   
    } 
    

    return Math.min(T0, T1);  
} 

}
