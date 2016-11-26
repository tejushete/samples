
class recursion_factorial {
 
	static long fact(long in){
		if(in <= 1) return 1;	
		return (fact(in-1)*in);
	}

	public static void main(String[] args){

		int n = 15;

		System.out.println("Factorial of No("+n+") using recursion is "+fact(n));
	}
}