class star4{
 public static void main(String[] java){

 	int lines = 4;

 // top increasing part
 	for(int i=0;i<lines;i++){
 		for(int j=0;j<(lines-i);j++){
 			System.out.print(" ");
 		}

 		for(int k=0;k<=i;k++){
 			if( k==0 || k==i)
 				System.out.print("* ");
 			else 				
 				System.out.print("  ");
 		}

 		System.out.println();
 	}

// centre line
 	System.out.print("*");
 	for(int j=0;j<(lines+3);j++){
 		System.out.print(" ");
 	}
 	System.out.println("*");

//bottom decreasing part
 	for(int i=lines-1;i>=0;i--){
 		for(int j=0;j<(lines-i);j++){
 			System.out.print(" ");
 		}

 		for(int k=0;k<=i;k++){
 			if(k==0 || k==i)
 				System.out.print("* ");
 			else 				
 				System.out.print("  ");
 		}

 		System.out.println();
 	}
}
}