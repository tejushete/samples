

class primearray{
     public static void main(String[] java){
     	int from=100;
     	int to=120;
     	int count=0;
     	int[] num= new int[(to-from)];
     	int[] primeArray = new int[(to-from)];

     	for(int i=0;i<num.length;i++){
     		num[i]=from+i;
     	}
     	for(int j=0;j<num.length;j++){
     		int t=0,n=num[j];
     		int m=n/2;
     			for(int i=2;i<=m;i++){
					if(n%i==0){
     					t=1;
     				}
     			}
     			if(t==0){
     				primeArray[count]=num[j];
     				count++;
     			}
     	}	
     		
     	for( int i = 0;i<primeArray.length;i++)
     		//if(primeArray[i]!=0)
     		System.out.println(primeArray[i]);
     }
}

