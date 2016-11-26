
class palindrome {

	public static void main(String[] felight){

		String s = "abcga";

		int length = s.length();
		//get length of string

		boolean is_palindrome = true;
		//assume initially that string is palindrome

		for(int i = 0; i<length/2; i++){

			if(s.charAt(i) != s.charAt(length-1-i)){
			 	is_palindrome = false;
			 	break;
			}
		}

		if(is_palindrome == true)
			System.out.println("String \""+s+"\" is palindrome.");
		else
			System.out.println("String \""+s+"\" is not palindrome.");

	}

}