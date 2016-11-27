import java.util.*;
class Scannersort{

	static void num_generator(int choice, int array[], int size){
		if(choice == 1){
			for(int i = 0; i<size; i++){
				array[i] = i+1;
			}
		}else if(choice == 3){
			for(int i = size-1; i>=0; i--){
				array[i] = i;
			}
		}else if(choice == 2){
			for(int i=0;i<size;i++){
				array[i]=(int)(Math.random()*100);
			}
		}

	}

	static int getCaseChoice(){
		Scanner sc = new Scanner(System.in);
		String complexity = "\n1.Best\n"+ "2.Avergae\n"+ "3.Worst";
		System.out.println("\n\nChoose case\n"+ complexity);
		System.out.println("-------------------");

		int choice = 0;
		boolean retried = false;
		while(choice > 3 || choice < 1){

			if(retried == true){
				System.out.println("\nWrong input please enter valid input:\n");
			}
			choice = sc.nextInt();
			retried = true;
		}

		return choice;
	}   

	static public void main(String[] java){

		System.out.println("Algorithm Benchmark");

		boolean shallcontinueagain = true;
		Scanner input = new Scanner(System.in);
		while(shallcontinueagain){
			System.out.println("\n\n\n------------STARTING------------");
			int choice = getCaseChoice();    
			switch(choice){

			case 1:
				System.out.println("Best Case");
				break;

			case 2:
				System.out.println("Average Case");
				break;

			case 3:
				System.out.println("Worst Case");
				break;


			default:
				System.out.println("Invalid option");
			}



			System.out.println("-------------------");


			System.out.println("Enter the size of an Array");

			int size = input.nextInt();
			System.out.println ("Enter The Sorting Algorithm");
			String menus="\n--------------1.Bubble Sort-------------\n"+"-----------2.Selection Sort-----------\n"+"--------3.Insertion Sort---------\n" +"---------4.Heap Sort----------\n"+"-----------5.Merge Sort------------\n"+"------------6.Benchmark All----------\n"+"--------7.Exit---------\n";
			System.out.println("\n\nChoose Sorting Algorithm\n"+ menus);
			System.out.println("-------------------");

			int operation=input.nextInt();

			int array[] = new int[size];


			switch(operation){

			case 1:

				System.out.println("Bubble Sort");
				num_generator(choice, array, size);
				algos.bubbleSort(array);
				break;

			case 2:
				System.out.println("Selection Sort");
				num_generator(choice, array, size);
				algos.bubbleSort(array);
				break;

			case 3:
				System.out.println("Inserton Sort");
				num_generator(choice, array, size);
				algos.bubbleSort(array);
				break;

			case 4:
				System.out.println("Heap Sort");
				num_generator(choice, array, size);
				algos.heapsort(array);
				break;
			case 5:
				System.out.println("Merge Sort");
				num_generator(choice, array, size);
				algos.mergesort(array);
				break;
			case 6:
			    System.out.println("Benchmark All");
			    algos.benchmarkAll();
			 	break;
			case 7:
				System.out.println("Start Over");
				break;
			case 8:
				System.out.println("Exit");
				shallcontinueagain = false;
				break;
			default:
				System.out.println("Invalid option");
			}
		}
	}
}
