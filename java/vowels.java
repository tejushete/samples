class vowels{

	public static void main(String[] java){

		char[] vowels=new char[15];
		int count=0;
		int result=0;

		for(int i=0;i<vowels.length;){
			result=(int)(Math.random()*100);

			if(result>64&&result<92||result>97&&result<123){
				vowels[i]=(char)result;
				i++;
			}
		}

		for(int i=0;i<vowels.length;i++){
			System.out.println(vowels[i]);
			if(vowels[i]=='a'|| vowels[i]=='e'||vowels[i]=='i'||vowels[i]=='o'||vowels[i]=='u'||vowels[i]=='A'||vowels[i]=='E'||vowels[i]=='I'||vowels[i]=='O'||vowels[i]=='U')
				count++;

		}
			System.out.println(count);






	}
}