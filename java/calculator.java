import java.*;
class calculator{

	//add two numbers
	public static int add(int num1,int num2){
		int sum=num1+num2;
		return sum;
	}

	//area of circle
	public static double area(int r,double pi){
		double  area=pi*r*r;
		return area;
	}

	//area of triange
	public static int area(int height,int base){
		int area=height*base/2;
		return area;
	}

	//add three number
	public static int add(int num1,int num2,int num3){
		int sum=num1+num2+num3;
		return sum;
	}

	//F to c
	public static double ftoc(double F){
		double celcius=1.8*(F-32);
		return celcius;
	}

	//c to F
	public static double ctof(double celcius){
		double fahrenite=(1.8*celcius)+32;
		return fahrenite;
	}

	//rupee to dollar
	public static double rtod(double rupee){
		double dollar=rupee/66.98;
		return dollar;
	}

	//check if leap year or not
	public static boolean leapyear(int year){
		boolean leapYearIs = (year%4==0);
		return leapYearIs;
	}

	//check even or not
	public static boolean iseven(int num){
		boolean iseven = (num%2==0);
		return iseven;


	}

	//check odd or not
	public static boolean isodd(int num){
		boolean isodd = (!(num%2==0));
		return isodd;


	}

	//divisible by 6 or not
	public static boolean divby6(int num){
		boolean divby6 = (num%6==0);
		return divby6;


	}

	//palindrome or not
	public static boolean palindrome(int num){
		int s = 0;
		int t = num;
		int r = 0;

		while(t>0){
			r = t%10;
			t = t/10;
			s = s*10+r;
		}

		if(s==num){
			return true;
		}else
			return false;
	}

	//prime or not
	public static boolean prime(int num){
		int t = 0;
		int n = num;
		int m = n/2;

		for(int i = 2;i<=m;i++){
			if(n%i==0){
				t=1;
			}
		}

		if(t == 0)
			return true;
		else
			return false;
	}

	//fibonacci series
	public static String fib(int terms){

		long a = 0,b = 1,c;
		String output = "";
		output = output + a + " " + b;

		while((terms-- - 2)> 0){

			c = a + b;
			a = b;
			b = c;
			output = output + " " + c;
		}

		return output;
	}

	//check if part of fibonacci series
	public static String CheckIfFibNo(long no){

		long a = 0, b = 1, c;
		if(no == 0) return "yes";

		while(true){

			c = a + b;
			a = b;
			b = c;

			if(c == no) return "yes";
			if(c > no) return "no";
		}
	}


	//even no from to to

	public static String generateEvenNumbers(int from,int to){
		String result="";

		for(int i=from;i<=to;i++){
			if(iseven(i)){
				result=result+" "+i;
			}
		}

		return result;
	}

	//from to to palindrome
	public static String generatepalindromeNumbers(int from,int to){
		String result="";

		for(int i=from;i<=to;i++){
			if(palindrome(i)){
				result=result+" "+i;
			}
		}

		return result;
	}

	//prime from to to
	public static String generateprimeNumbers(int from,int to){
		String result="";
		for(int i=from;i<=to;i++){
			if(prime(i)){
				result=result+" "+i;
			}
		}

		return result;
	}

	//calculate speed
	public static double speed(double distance,double time){
		double speed= distance/ time;
		return speed; 
	}


	//factorial of numbers
	public static long factorial(int n){

		long fact=1;
		for(int i=1;i<=n;i++){
			fact = fact*i;
		}
		return fact;	
	}
	//fact = 1, i = 1, fact = 1 * 1; i = 2
	//fact = fact * 2 = 1 * 2 = 2;  i = 3
	// fact = fact * 3 = 2 * 3 = 6; i = 4
	// fact = fact * 4 = 6 * 4 = 24; i = 5

	//convert seconds in hours

	public static double hours(double second){
		double hours = second/3600; 
		return hours;
	}

	//count no.of digits in an intiger

	public static int digits(int num){

		//     return (int)(Math.log10(num)+1);
		int count = 0;
		while(num>0){
			num=num/10;
			count++;
		}
		return count;
	} 


	//repeat the given digit twice   

	public static int twice(int num){

		int num1=num;
		int original=num;
		while(num>0){
			num1=num1*10;
			num=num/10;
		}
		num1=num1+original;

		return num1;
	}	

	//find the value of its hundred digit

	public static int hundreddigit(int n){

		return((n/100)%10);
	}

	//find required run rate
	public static double runrate(double target,double maxOvers,double currentscore,double currentOvers){
		return((target-currentscore)/(maxOvers-currentOvers));
	} 	

	//make decimal
	public static double decimal(double a,double b,double c){
		return(((a*100)+(b*10)+(c))/100);
	}

	public static int sum2DigitNumber(int num){
		int a = num / 10;
		int b = num % 10;
		int c = a + b;
		return c;
	}

	public static boolean andThreeBooleans(boolean a, boolean b, boolean c){
		return (a && b && c);
	}

	public static boolean atLeastLargerthanOne(int n1, int n2, int n3){
		if(n1 > n2) return true;
		if(n1 > n3) return true;

		return false;
	}

	public static boolean findIfAscending(int n1, int n2, int n3){
		if((n2 > n1) && (n3 > n2)) return true;

		return false;
	}

	public static int sumOfLastFourDigits(int num){

		int sum = 0;
		int digits_added = 0;

		while(digits_added <4){
			sum += num % 10; 
			num /= 10;
			digits_added++;
		}

		return sum;
	}

	public static double areaOfSquareUsingXY(int x1, int y1, int x2, int y2){

		int diagonal_square = (y2-y1)*(y2-y1) + (x2-x1)*(x2-x1);
		//Pythogoras theorem 
		double area = diagonal_square / 2;

		return area;
	}

	public static int addRepeatedDigits(int a, int b, int c){

		int A = a * 1000 + a * 100 + a * 10 + a;
		int B = b * 1000 + b * 100 + b * 10 + b;
		int C = c * 1000 + c * 100 + c * 10 + c;

		int SUM = A + B + C;

		return SUM;
	}

	public static boolean NumberDivBy3or7(int num){

		if( num % 3 == 0) return true;
		if( num % 7 == 0) return true;
		return false;
	} 

	public static int findLargestOfThree(int n1, int n2, int n3){

		int max = n2;
		if(n1 > max) max = n1;
		if(n3 > max) max = n3;

		return max;
	}

	public static int days_in_month(int month){

		int days_in_a_month[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		return days_in_a_month[month+1];
	}

	public static char changeCase(char input){

		if(input >= 'A' && input <= 'Z'){
			char diff = (char)(input - 'A');
			return (char)('a' + diff);
		}

		if(input >= 'a' && input <= 'z'){
			char diff = (char)(input - 'a');
			return (char)('A' + diff);
		}

		return input;
	}

	public static boolean isCharDigit(char in){
		if(in <= '9' && in >='0') return true;
		return false;
	}

	public static char returnMiddleCharOFThree(char n1, 
			char n2,
			char n3){

		char largest, smallest;

		largest = n3;
		if(n1 > largest) largest = n1;
		if(n2 > largest) largest = n2;

		smallest = n3;
		if(n1 < smallest) smallest = n1;
		if(n2 < smallest) smallest = n2;


		if(n1 != largest || n1 != smallest) return n1;
		if(n2 != largest || n2 != smallest) return n2;
		if(n3 != largest || n3 != smallest) return n3;

		return 0;
	}


	public static boolean checkIfSameLastDigit(int n1, int n2){

		if(n1 >0 && n2 > 0) {
			n1 = n1 % 10;
			n2 = n2 % 10;
			if(n1 == n2) return true;
			else return false;
		}

		return false;
	}

	public static boolean sumEqualsThird(int n1, int n2, int n3){

		if((n1+n2) == n3) return true;
		if((n3+n2) == n1) return true;
		if((n1+n3) == n2) return true;

		return false;
	}

	public static int lottery_points(int no){

		if(no % 4 == 0 && no % 7 == 0) return 20;
		if(no % 4 == 0) return 6;
		if(no % 7 == 0) return 10;

		return 0;
	}

	public static int three_lottery_tickets(int l1, int l2, int l3){

		int lot1_pts, lot2_pts, lot3_pts;

		lot1_pts = lottery_points(l1);
		lot2_pts = lottery_points(l2);
		lot3_pts = lottery_points(l3);

		return (lot1_pts + lot2_pts + lot3_pts);
	}

	public static int sumOfLastThreeDigits(int no){

		int sum = 0;
		int digits_added = 0;

		while(digits_added < 3){
			sum += no % 10;
			no /= 10;
			digits_added++;
		}

		return sum;
	}

	public static int reverseNumber(int n){

		int reverseNumber = 0;
		int r=0;
		while (n > 0){
			r=n%10;
			n=n/10;
			reverseNumber=reverseNumber*10+r;
		}

		return reverseNumber;
	}

	public static int blackJack(int n1, int n2){

		if(n1 >21 && n2 > 21) return -1;
		if(n1 == n2 && n1<=21) return -2;

		if(n1 <= 21 && n2 <= 21){
			int max = n2;
			if(n1 > max) max = n1;
			return max;
		}

		if(n1 > 21) return n2;
		if(n2 > 21) return n1;

		return 0;
	}


	public static boolean perfectNo(int n){


		int sum = 0;
		for(int i = 1;i<=n/2;i++){
			if(n%i==0){
				sum = sum + i;
			}
		} 
		if(sum==n) {
			return true;
		}else
			return false;
	}


	public static void printArray(int[] array){
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
		System.out.println("\n");
	}

	public static void reveseArray(int[] array){
		System.out.println("\n");
		for(int i=array.length-1;i>0;i--){
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}

	public static int sumOfArray(int[] array){

		System.out.println("\n");
		int sum=0;
		for(int i=0;i<array.length;i++){
			sum+=array[i];
		}
		return sum;
	}


	public static int maxOfArray( int[] array){

		System.out.println("\n");
		int max=0;
		for(int i=0;i<array.length;i++){

			if(array[i]>max){

				max=array[i];
			}
		}
		return max;
	}




	public static boolean repeatedOfArray( int[] array){

		System.out.println("\n");

		for(int i=0;i<array.length;i++){

			for(int j=i+1;j<array.length;j++){

				if(array[i]==array[j]){


					return true;


				}
			}

		}return false;
	}




	public static int[] sortOfArray( int[] array){

		System.out.println("\n");
		int sort=0;

		for(int i=0;i<array.length;i++){

			for(int j=i+1;j<array.length;j++){

				if(array[i]<array[j]){

					sort=array[i];
					array[i]=array[j];
					array[j]=sort;



				}
			}

		}return array;
	}




	public static int[] reverseOfArray( int[] array){

		System.out.println("\n");
		int reverse=0;

		for(int i=0;i<array.length;i++){

			for(int j=i+1;j<array.length;j++){

				if(array[i]>array[j]){

					reverse=array[i];
					array[i]=array[j];
					array[j]=reverse;



				}
			}

		}return array;
	}


	  public static int checkpos(int[] array,int key){
     	boolean flag=false;
	     int pos=0;
		for(int i=0;i<array.length;i++){

			if(key==array[i]){
				pos=i;
				flag=true;
				break;
			}
		}
		if(flag==true)
			return pos;
		else
			return -1;

	}





	public static void caseArray(char[] array){

		for(int i=0;i<array.length;i++){

			if(array[i] >= 'A' && array[i]<= 'Z'){
				char diff = (char)(array[i] - 'A');
				array[i] = (char) ('a' + diff);
			}
			else if(array[i] >= 'a' && array[i] <= 'z'){
				char diff = (char)(array[i] - 'a');
				array[i] = (char) ('A' + diff);
			}
		}
	}
	public static void printarray(boolean[] barray){
		System.out.println("\n");
		for(int i=0;i<barray.length;i++){
			System.out.print(barray[i]+" ");
		}}
	public static void change (boolean[] barray)
	{
		for(int i=0;i<barray.length;i++)
		{
			barray[i]=true;
			System.out.print(barray[i]+" ");
		}
	}

public static int[] generateNaturalNumbers(int howMany){
System.out.println("natural numbers");
	int[] array=new int [howMany];
	for(int i =0;i<array.length;i++){
		array[i]=i+1;
	}

	return array;
}

 
 public static int[] generateRandomNumbers(int howMany){
 	System.out.println("random numbers");

	int[] array=new int [howMany];
	for(int i =0;i<array.length;i++){
		array[i]=(int)(Math.random()*1000);
	}
	return array;
}

    public static int[] generateEven1Numbers(int from,int to){

      int[] array = new int [((to-from)/2)+1];
      int index=0;
      for(int i =from;i<=to;i++){
           if(iseven(i)){
           	array[index++] =i;
           }

      }return array;




    }



     public static int[] generateOdd1Numbers(int from,int to){

      int[] array = new int [((to-from)/2)];
      int index=0;
      for(int i =from;i<=to;i++){
           if(isodd(i)){
           	array[index++] =i;
           }

      }return array;




    }

    public static int[] generateprime1Numbers(int from,int to){
		int[] array=new int [((to-from)/2)-1];
		 int index=0;
      for(int i =from;i<=to;i++){
           if(prime(i)){
           	array[index++] =i;
           }
    
       
      }return array;

}




public static int[] generatepalindrome1Numbers(int from,int to){
		int[] array=new int [((to-from)/2)-1];
		 int index=0;
      for(int i =from;i<=to;i++){
           if(palindrome(i)){
           	array[index++] =i;
           }
    
       
      }return array;

}

public static int[] generatefibbonaciSeries(int howMany){

int []array=new int[howMany];
	int n1=0,n2=1,n3;
     array[0]=n1;
     array[1]=n2;
	

	for(int i=2;i<array.length;i++){
		n3=n1+n2;

array[i]=n3;
	n1=n2;
	n2=n3;
}
	return array;

}


public static char[] generatechar1array(int howMany){
	char[] array=new char[howMany];
	int result=0;
	int count=0;
		for(int i=0;i<array.length;){
			result=(int)(Math.random()*1000);
			if(result>64&&result<91||result>97&&result<122){
				array[i]=(char)result;
				i++;
			}
		}
		for(int i=0;i<array.length;i++){
		
			if(array[i]=='a'|| array[i]=='e'||array[i]=='i'||array[i]=='o'||array[i]=='u'||array[i]=='A'||array[i]=='E'||array[i]=='I'||array[i]=='O'||array[i]=='U')
					count++;
        }
           System.out.println("no of vowels:");
        System.out.println(count);
		return array;
	}





	public static int[] generatereverse1Numbers(int howMany){



		int []array=new int [howMany];

		for(int i =0 ;i<array.length;i++)
			array[i]=i+1;

		System.out.println("\nActual value"+'\n');
	 	for(int i=0;i<array.length;i++)
	 		System.out.print(array[i]+" ");

	 	System.out.println("\nrevese value");
	 	for(int i=(array.length)-1;i>=0;i--)
	 		System.out.print(array[i]+" ");
		return array;

	


}
}






		

		
		


	



		























































































































































































































































































