package com.felight.benchmark;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class BenchmarkActivity extends AppCompatActivity {
    private Button btnGenArray,btnBubbleSort,btnSelectionSort,btnInsertionSort,btnMergeSort,btnBenchmark;
    private EditText etArraySize;
    private RadioButton rbBestCase,rbAverageCase,rbWorstCase;
    private TextView tvBubbleSort,tvSelectionSort,tvInsertionSort,tvMergeSort;


      private Context context= this;



    private int mArray[],arraySize,complexity=2;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.benchmark_layout);
        btnGenArray = (Button) findViewById(R.id.btnGenerateArray);
        btnBubbleSort = (Button) findViewById(R.id.btnBubbleSort);
        btnSelectionSort = (Button) findViewById(R.id.btnSelectionSort);
        btnInsertionSort= (Button) findViewById(R.id.btnInsertionSort);
        btnMergeSort = (Button) findViewById(R.id.btnMergeSort);
        btnBenchmark = (Button) findViewById(R.id.btnBenchmarkAll);
        etArraySize = (EditText)findViewById(R.id.etArraySize);
        rbBestCase = (RadioButton)findViewById(R.id.rbBestCase);
        rbAverageCase = (RadioButton)findViewById(R.id.rbAverageCase);
        rbWorstCase = (RadioButton)findViewById(R.id.rbWorstCase);
        tvBubbleSort = (TextView)findViewById(R.id.tvBubbleSort);
        tvSelectionSort = (TextView)findViewById(R.id.tvSelectionSort);
        tvInsertionSort = (TextView)findViewById(R.id.tvInsertionSort);
        tvMergeSort = (TextView)findViewById(R.id.tvMergeSort);

        btnGenArray.setBackgroundColor(Color.RED);
        btnGenArray.setTextColor(0xFF00FF00);

        View root = rbWorstCase.getRootView();
        root.setBackgroundColor(0xff0000ff);
        btnBenchmark.setBackgroundResource(R.drawable.benchmarkall);
        btnGenArray.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String size = etArraySize.getText().toString();
                arraySize=Integer.parseInt(size);
                mArray=new int [arraySize];
                num_generator(complexity,mArray,arraySize);
                Toast.makeText(context,"Array generated succesfully",Toast.LENGTH_SHORT).show();


            }
        });


        btnBubbleSort.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                algos A=new algos();
                double time=A.bubbleSort(mArray);
                tvBubbleSort.setText(String.valueOf(time)+" ms");
                tvSelectionSort.setText("--- ms");
                tvInsertionSort.setText("--- ms");
                tvMergeSort.setText("--- ms");



            }
        });
        btnSelectionSort.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                algos A=new algos();
                double time=A.selectionSort(mArray);
                tvSelectionSort.setText(String.valueOf(time)+" ms");
                tvBubbleSort.setText("--- ms");
                tvInsertionSort.setText("--- ms");
                tvMergeSort.setText("--- ms");


            }
        });
        btnInsertionSort.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                algos A=new algos();
                double time=A.insertionSort(mArray);
                tvInsertionSort.setText(String.valueOf(time)+" ms");
                tvSelectionSort.setText("--- ms");
                tvBubbleSort.setText("--- ms");
                tvMergeSort.setText("--- ms");


            }
        });
        btnMergeSort.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                algos A=new algos();
                double time=A.mergesort(mArray);
                tvMergeSort.setText(String.valueOf(time)+" ms");
                tvSelectionSort.setText("--- ms");
                tvInsertionSort.setText("--- ms");
                tvBubbleSort.setText("--- ms");


            }
        });
        btnBenchmark.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                algos A=new algos();
                double bubbleSorttime=A.bubbleSort(mArray);

                double selectionSorttime=A.selectionSort(mArray);

                double insertionSorttime=A.insertionSort(mArray);

                double mergeSorttime=A.mergesort(mArray);
                tvBubbleSort.setText(String.valueOf(bubbleSorttime)+" ms");
                tvSelectionSort.setText(String.valueOf(selectionSorttime)+" ms");
                tvInsertionSort.setText(String.valueOf(insertionSorttime)+" ms");
                tvMergeSort.setText(String.valueOf(mergeSorttime)+" ms");



            }
        });
        rbBestCase.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                rbAverageCase.setChecked(false);
                rbWorstCase.setChecked(false);
                complexity=1;

            }
        });
        rbAverageCase.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                rbBestCase.setChecked(false);
                rbWorstCase.setChecked(false);
                complexity=2;

            }
        });
        rbWorstCase.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                rbAverageCase.setChecked(false);
                rbBestCase.setChecked(false);
                complexity=3;

            }
        });



    }


    public class algos{

        class MergeSort
        {

            void merge(int arr[], int l, int m, int r)
            {
                int n1 = m - l + 1;
                int n2 = r - m;

                int L[] = new int [n1];
                int R[] = new int [n2];

                for (int i=0; i<n1; ++i)
                    L[i] = arr[l + i];
                for (int j=0; j<n2; ++j)
                    R[j] = arr[m + 1+ j];

                int i = 0, j = 0;

                int k = l;
                while (i < n1 && j < n2)
                {
                    if (L[i] <= R[j])
                    {
                        arr[k] = L[i];
                        i++;
                    }
                    else
                    {
                        arr[k] = R[j];
                        j++;
                    }
                    k++;
                }

                while (i < n1)
                {
                    arr[k] = L[i];
                    i++;
                    k++;
                }

                while (j < n2)
                {
                    arr[k] = R[j];
                    j++;
                    k++;
                }
            }

            void sort(int arr[], int l, int r)
            {
                if (l < r)
                {
                    // Find the middle point
                    int m = (l+r)/2;

                    // Sort first and second halves
                    sort(arr, l, m);
                    sort(arr , m+1, r);

                    // Merge the sorted halves
                    merge(arr, l, m, r);
                }
            }

            /* A utility function to print array of size n */
            void printArray(int arr[])
            {
                int n = arr.length;
                for (int i=0; i<n; ++i)
                    System.out.print(arr[i] + " ");
                System.out.println();
            }

        }
        class HeapSort
        {
            public void sort(int arr[])
            {
                int n = arr.length;

                // Build heap (rearrange array)
                for (int i = n / 2 - 1; i >= 0; i--)
                    heapify(arr, n, i);

                // One by one extract an element from heap
                for (int i=n-1; i>=0; i--)
                {
                    // Move current root to end
                    int temp = arr[0];
                    arr[0] = arr[i];
                    arr[i] = temp;

                    // call max heapify on the reduced heap
                    heapify(arr, i, 0);
                }
            }

            // To heapify a subtree rooted with node i which is
            // an index in arr[]. n is size of heap
            void heapify(int arr[], int n, int i)
            {
                int largest = i;  // Initialize largest as root
                int l = 2*i + 1;  // left = 2*i + 1
                int r = 2*i + 2;  // right = 2*i + 2

                // If left child is larger than root
                if (l < n && arr[l] > arr[largest])
                    largest = l;

                // If right child is larger than largest so far
                if (r < n && arr[r] > arr[largest])
                    largest = r;

                // If largest is not root
                if (largest != i)
                {
                    int swap = arr[i];
                    arr[i] = arr[largest];
                    arr[largest] = swap;

                    // Recursively heapify the affected sub-tree
                    heapify(arr, n, largest);
                }
            }

            /* A utility function to print array of size n */
            void printArray(int arr[])
            {
                int n = arr.length;
                for (int i=0; i<n; ++i)
                    System.out.print(arr[i]+" ");
                System.out.println();
            }
        }
        public double bubbleSort(int[] ar)
        {
            double starttime = System.nanoTime();
            for(int i = ar.length-1; i>=0; i--){
                for(int j = 1; j<=i;j++){
                    if(ar[j-1]>ar[j]){
                        int temp = ar[j-1];
                        ar[j-1] = ar[j];
                        ar[j] = temp;
                    }
                }
            }

            double endtime = System.nanoTime();

            for(int i = 0; i<ar.length; i++){
                System.out.print(ar[i]+" ");
            }
            double timediff = ((endtime-starttime)/1000000);
            System.out.println("\nTime diff: "+ timediff +" milliseconds");
            return timediff;

        }

        public  double selectionSort(int[] ar){
            double starttime = System.nanoTime();
            for(int i = 0; i<ar.length-1; i++){
                int min = i;
                for(int j = i+1; j<ar.length; j++){
                    if(ar[j] < ar[min]) min = j;
                }
                int temp = ar[i];
                ar[i] = ar[min];
                ar[min] = temp;
            }


            double endtime = System.nanoTime();

            for(int i = 0; i<ar.length; i++){
                System.out.print(ar[i]+" ");
            }
            double timediff = ((endtime-starttime)/1000000);
            System.out.println("\nTime diff: "+ timediff +" milliseconds");
            return timediff;

        }

        public  double insertionSort(int[] ar)
        {
            double starttime = System.nanoTime();
            for (int i=1; i < ar.length; i++)
            {
                int index = ar[i]; int j = i;
                while (j > 0 && ar[j-1] > index)
                {
                    ar[j] = ar[j-1];
                    j--;
                }
                ar[j] = index;
            }
            double endtime = System.nanoTime();

            for(int i = 0; i<ar.length; i++){
                System.out.print(ar[i]+" ");
            }
            double timediff = ((endtime-starttime)/1000000);
            System.out.println("\nTime diff: "+ timediff +" milliseconds");
            return timediff;

        }


        public  void heapsort(int arr[])
        {
            int n = arr.length;

            HeapSort ob = new HeapSort();
            double starttime = System.nanoTime();
            ob.sort(arr);
            double endtime = System.nanoTime();
            System.out.println("Sorted array is");
            ob.printArray(arr);
            double timediff = ((endtime-starttime)/1000000);
            System.out.println("\nTime diff: "+ timediff +" milliseconds");



        }

        // Driver method
        public  double mergesort(int arr[])
        {
            MergeSort ob = new MergeSort();
            double starttime = System.nanoTime();
            ob.sort(arr, 0, arr.length-1);

            double endtime = System.nanoTime();
            double timediff = ((endtime-starttime)/1000000);
            System.out.println("\nTime diff: "+ timediff +" milliseconds");
            return  timediff;

        }
        public  void benchmarkAll(){

        }
    }
}
