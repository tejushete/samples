class star3{
 public static void main(String[] java){

 	int lines = 9;
 	for(int i=0;i<lines;i++){
 		for(int j=0;j<(lines-i);j++){
 			System.out.print(" ");
 		}

 		for(int k=0;k<=i;k++){
 			if(i == (lines-1) || k==0 || k==i)
 				System.out.print("* ");
 			else 				
 				System.out.print("  ");
 		}

 		System.out.println();
 	}





 }

}
