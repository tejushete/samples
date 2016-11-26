class star2{
 public static void main(String[] java){

 	int n = 16;
 	for(int i=0;i<n;i++){
 		for(int j=0;j<n-i;j++){
 			System.out.print(" ");
 		}
 		for(int k=0;k<=i;k++){
 			System.out.print("* ");
 		}
 		System.out.println();
}


     //System.out.println("\n");
 	for(int i=n-2;i>=0;i--){
 		for(int j=0;j<=n-2-i;j++){
 			System.out.print(" ");
 		}
 		for(int k=0;k<=i;k++){
 			System.out.print(" *");
 		}
 		System.out.println();
}
















	}
	}