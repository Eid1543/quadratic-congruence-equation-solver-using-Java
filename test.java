import java.util.Scanner; 
public class test {
	
	public static int a = 0 , b = 0 , c = 0 , p = 0  ; 
	
	public static void main(String[] args) {
			
		
	
		
		
		
		int choice = 0 ; 
		Scanner s = new Scanner (System.in) ; 
		
		while ( choice != -1 ) {
			
		store(); 
		
		if ( ! DoeshasSolution() ) {
			System.out.println("The equation has no solution ");

		}
	else {
			
			solutionOfEquation(); 		
		}
		
		System.out.println("do you want to continue ? , press any number to continue , -1 to exist ");
		 choice = s.nextInt() ; 
		}
		
		System.out.println("Good bye :) ");
	   
	    
	     
	    }
	
	
	
	
	
	// this method will compute the modular expansion ( fermat rule ) 
 public static int ModularExp (int base , int exp ) 
 { 
     
     int res = 1;      
      
     base = modulo_Euclidean(base); 
   
     while (exp > 0) 
     { 
         if((exp & 1)==1)    
             res = modulo_Euclidean( (res * base ) );
   
         exp = exp >> 1;  
         base = modulo_Euclidean(  (base * base) );
     } 
     return res; 
 } 
 
 
 
 // to store the inputs of the user 
 public static void store () { 
	 
	   Scanner s = new Scanner(System.in) ; 
	 
	   // the user will enter these : 
	 System.out.println("Enter the value of a , b , c and p in the equation: ax 2 +bx +c = 0 mod p to solve the equation, note: p must be an odd prime number ");
	  a = s.nextInt() ; 
	  b = s.nextInt() ; 
	  c = s.nextInt() ; 
	  p = s.nextInt() ; 
	  
	 
	  
	  
	    int check = modulo_Euclidean(a) ; 
	  
	  if ( ( check == 0  ) || ( ! oddPrime(p)  )  ) {  // checking if a is not divisible by p  and to check p is odd prime 
		  
		  System.out.println("error , a should be not divisble by p or p should be odd prime ");   // if a is divisible by p then we broke a condition 
		  System.exit(0); 
	  }
	  
	 
	 
 }
	
	
 
 
 // this method will check if a number is prime and odd 
 public static boolean oddPrime (int n ) {
	 
	 if ( ( n % 2 != 0 ) && isPrime(n)) 
		 return true ; 
	 else 
		 return false ; 
	 
 }
 
 
 
 
 
 // helper method to check if a number is prime or not 
 public static boolean isPrime(int n) 
 { 
    
     if (n <= 1) 
         return false; 

    
     for (int i = 2; i < n; i++) 
         if (n % i == 0) 
             return false; 

     return true; 
 } 
 
 
 
 // this mehtod will compute the d ,  " d = b^2 - 4ac "  
 
 public static int compute_D () { 
	 
	  int d = 0 ; 
	  
	  d = ( b * b ) - 4 * a * c ; 
	 
	  return d ; 
 }
 
 
 
 
 // this method will check if an equation have solution or not 
 
 public static boolean DoeshasSolution () {
 
    // first we will count ( p - 1 ) / 2 
	  
	 int exp = ( p - 1 ) / 2 ; 
	 int base = compute_D() ; 
	  
	 // does it have solution or not 
	 
	 if ( ModularExp(base, exp) == 1  )  // have solution 
		 return true  ; 
	 else if (ModularExp(base, exp ) == -1 ) // does not have solution 
		 return false ; 
	 
	 return false ; 

}
 
 
 
 
 
 // this mehtod will get us the solution of the equation 
 public static void solutionOfEquation () {
	 
	 

     // n = ( -b + alpha ) , n2 = ( -b - alpha ) 
	 //  m = 2*a ; 
    
		
		int alpha= countAlpha() ; 
		int n = -b + alpha ; 
		int n2 = -b - alpha ; 
		int m = 2 * a ; 
	
		// taking the mod of the numbers 
		n = modulo_Euclidean(n); 
		n2 = modulo_Euclidean(n2) ; 
		
		
// the solutions :  
	
		int sol1 = (n) * modInverse(m , p)  ; 
		int sol2 = (n2) * modInverse(m , p) ; 
		
		sol1 = modulo_Euclidean(sol1);   
		sol2 =  modulo_Euclidean(sol2); 
		
		System.out.print("The solutions of the equation are : ");
		System.out.println(" {"+sol1+","+sol2+"}");
		
		
 }
 
 
 
  // this mehtod will compute the alpha ,  
 public static int countAlpha () {
	 
	 int k = 0 ; 
	 int d = compute_D() ; 
	  
	 boolean perfectSquare = isPerfectSquare( d + ( p * k )) ; 
	 
	 while ( ! perfectSquare ) {
		 
		 k = k + 1 ; 
		 
		 perfectSquare = isPerfectSquare( d + ( p * k ) ) ;
	 }
	 
	 
	 // the value of alpha : 
	 double alpha = Math.sqrt( d + ( p * k )) ; 
	 
	 
	 
	 return (int ) alpha ; 
	 
	 
 }
 
 
 
 
 
 // helper mehod to check if a number is a perfect square 
 public static boolean isPerfectSquare (double x)  
 { 
       
    
     double sr = Math.sqrt(x); 
   
    
     return ((sr - Math.floor(sr)) == 0); 
 } 
   
 
 
 
 
 // to compute the inverse 
 static int modInverse(int a, int prime) 
 { 
     a = modulo_Euclidean(a) ; 
     
     for (int x = 1; x  <prime; x++) 
     if ( modulo_Euclidean( (a * x) ) == 1)  
         return x; 
       
     return -1; 
 } 
 
 
 
 
 
 // to compute the mod ( the euclidean way ) , " n mod p "   
 public static int modulo_Euclidean(int a ) {
	  int m = a % p;
	  if (m < 0) {
	   
	    m = (p < 0) ? m - p : m + p;
	  }
	  return m;
	}

 
 
 
}