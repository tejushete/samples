class gates{

	public static void main(String[] felight){

     boolean p,q;
     System.out.println("p\tq\tAND\tOR\tEX-OR\tNOTp\tNOTq");

     p=false;
     q=false;

     System.out.println(p+"\t"+q+"\t"+(p&&q)+"\t"+(p||q)+"\t"+(p^q)+"\t"+(!p)+"\t"+(!q)+"\t");


   p=false;
   q=true;
    

     System.out.println(p+"\t"+q+"\t"+(p&&q)+"\t"+(p||q)+"\t"+(p^q)+"\t"+(!p)+"\t"+(!q)+"\t");

  p=true;
  q=false;


     System.out.println(p+"\t"+q+"\t"+(p&&q)+"\t"+(p||q)+"\t"+(p^q)+"\t"+(!p)+"\t"+(!q)+"\t");
 
   p=true;
   q=false;


     
     System.out.println(p+"\t"+q+"\t"+(p&&q)+"\t"+(p||q)+"\t"+(p^q)+"\t"+(!p)+"\t"+(!q)+"\t");

  }
}