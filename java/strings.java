class String1{



/*

public String getResult(String input){
    StringBuilder sb = new StringBuilder();
    for(String s : input.split(" ")){
        sb.append(s.charAt(0));         
    }
    return sb.toString();
}*/



  public static boolean isPalindrome(String s) {
  int n = s.length();
  for (int i=0;i<(n/2);++i) {
     if (s.charAt(i)!=s.charAt(n-i-1)) {
         return false;
     }
  }

  return true;
}

public static String reverse(String source){
	if(source==null|| source.isEmpty()){
		return source;
	}
	String reverse="";
	for(int i=source.length()-1;i>=0;i--){
		reverse=reverse+source.charAt(i);
	}
	return reverse;
}

public void getFirstLetters(String text){
	String firstLetters="";
	for(String s:text.split(" ")){
		firstLetters=firstLetters+s.charAt(0);
	}
	System.out.println(firstLetters);
}

static boolean Checkid(String in){
	int j=0,k=0,m=0;
	char[] carr=in.toCharArray();
	for(int i=0;i<carr.length;i++){
		if(i>0){
			if(carr[i]=='@'){
				j++;
				k=1;
			}
			if(k==1){
				if(carr[i]=='.'&& carr[i+1]=='c' && carr[i+2]=='o' && carr[i]=='m')
					m++;
			}
		}
	}
	if(j==1 && m==1)
		return true;
	else
		return false;
}



public static boolean isAnagram(String first, String second) {
    String positive = first.toLowerCase();
    String negative = second.toLowerCase();

    if (positive.length() != negative.length()) {
      return false;
    }

    int[] counts = new int[26];

    int diff = 0;

    for (int i = 0; i < positive.length(); i++) {
      int pos = (int) positive.charAt(i) - 97; // convert the char into an array index
      if (counts[pos] >= 0) { // the other string doesn't have this
        diff++; // an increase in differences
      } else { // it does have it
        diff--; // a decrease in differences
      }
      counts[pos]++; // track it

      int neg = (int) negative.charAt(i) - 97;
      if (counts[neg] <= 0) { // the other string doesn't have this
        diff++; // an increase in differences
      } else { // it does have it
        diff--; // a decrease in differences
      }
      counts[neg]--; // track it
    }

    return diff == 0;
  }



}




































































