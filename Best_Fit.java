public class Best_Fit {
  
    static void bestFit(int blockSize[], int m, int processSize[], int n) {
        int allocation[] = new int[n];
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;
        }
              for (int i = 0; i < n; i++) {
            int bestIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (bestIdx == -1) // issume the first index is the best index
                        bestIdx = j;
                    else if (blockSize[bestIdx] > blockSize[j]) // also check if any other minimum block is there or not if yes then update the bestIdx
                        bestIdx = j;
                }
            }
            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                blockSize[bestIdx] -= processSize[i];
            }
        }
        System.out.println("\nProcess No.\tProcess Size\tBlock no.");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");

            if (allocation[i] != -1) {
                System.out.print(allocation[i] + 1);
            } else {
                System.out.print("Not Allocated");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int blockSize[] = { 100, 500, 200, 300, 600 };
        int processSize[] = { 212, 417, 112, 426 };
        int m = blockSize.length;
        int n = processSize.length;

        bestFit(blockSize, m, processSize, n);
    }
}
