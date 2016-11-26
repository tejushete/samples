class prime{


	   public static void main(String[] felight){

          int num=17,t=0,m;
          m=num/2;
          for(int i=2;i<=m;i++){
               if(num%i==0){
                    System.out.println(num+" is not prime");
                    t=1;
               }

          }
          if(t==0)
                System.out.println(num+" is  prime");
          	


	   }
}