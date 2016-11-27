

class algos{

  static class MergeSort
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
  static class HeapSort
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
  public static void bubbleSort(int[] ar)
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
    double timediff = ((endtime-starttime)/1000);
    System.out.println("\nTime diff: "+ timediff +" microseconds");

  }

  public static void selectionSort(int[] ar){
    for(int i = 0; i<ar.length-1; i++){
      int min = i;
      for(int j = i+1; j<ar.length; j++){
        if(ar[j] < ar[min]) min = j;
      }
      int temp = ar[i];
      ar[i] = ar[min];
      ar[min] = temp;
    }
  }

  public static  void insertionSort(int[] ar)
  {
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
  }

  public static void heapsort(int arr[])
  {
    int n = arr.length;

    HeapSort ob = new HeapSort();
    ob.sort(arr);

    System.out.println("Sorted array is");
    ob.printArray(arr);
  }

  // Driver method
  public static void mergesort(int arr[])
  {
    System.out.println("Given Array");
    
    MergeSort ob = new MergeSort();
    ob.sort(arr, 0, arr.length-1);

    System.out.println("\nSorted array");
    ob.printArray(arr);
  }
  public static void benchmarkAll(){
    
  }
}