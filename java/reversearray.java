class reverse{

	public static void main(String[] java){

		int[]reverse=new int[15];

		for(int i =0 ;i<reverse.length;i++)
			reverse[i]=i+1;

		System.out.println("Actual value"+'\n');
	 	for(int i=0;i<reverse.length;i++)
	 		System.out.println(reverse[i]);

	 	System.out.println("revese value");
	 	for(int i=(reverse.length)-1;i>=0;i--)
	 		System.out.println(reverse[i]);
		}

	}



