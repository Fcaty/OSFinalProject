import java.util.Scanner;

public class Process {
    static Scanner myScan = new Scanner(System.in);

    //Method to input memory blocks
    public static int[] inputMemBlocks(int[] memoryBlocks) {
        for(int i = 0; i < memoryBlocks.length - 1; i++){
            do{
                System.out.print("Size of Memory Block M"+(i+1)+": ");
                memoryBlocks[i] = myScan.nextInt();
            }while(memoryBlocks[i] <= 0);
        }
        return memoryBlocks;
    }

    //Method to input job blocks
    public static int[] inputJobBlocks(int[] jobBlocks) {
        for(int i = 0; i < jobBlocks.length; i++){
            do{
                System.out.print("Size of Job Block J"+(i+1)+": ");
                jobBlocks[i] = myScan.nextInt();
            }while(jobBlocks[i] <= 0);
        }
        return jobBlocks;
    }

    //Method to evaluate and display memory allocation process
    public static void firstFitAllocation(int[] jobBlocks, int[] memoryBlocks) {
        int jobMemory[] = new int[(jobBlocks.length >= memoryBlocks.length) ? jobBlocks.length : memoryBlocks.length - 1];
        boolean isFull[] = new boolean[memoryBlocks.length];

        for (int j = 0; j < jobBlocks.length; j++) {
            jobMemory[j] = -1;
            for (int m = 0; m < memoryBlocks.length - 1; m++) {
                if (!isFull[m] && jobBlocks[j] <= memoryBlocks[m]) {
                    jobMemory[j] = m;
                    isFull[m] = true;
                    break;
                } else if (isFull[m]) {
                    continue;
                }
            }
        }
        Output.displayJointTable(jobMemory, memoryBlocks, jobBlocks);
    }

    public static void bestFitAllocation(int[] jobBlocks, int[] memoryBlocks) {
        int jobMemory[] = new int[(jobBlocks.length >= memoryBlocks.length) ? jobBlocks.length : memoryBlocks.length - 1];
        boolean isFull[] = new boolean[memoryBlocks.length];

        int smallestDiff = 0;
        int currentIndex = -1;

        for(int j = 0; j < jobBlocks.length; j++){
            for(int m = 0; m < memoryBlocks.length - 1; m++){
                if(currentIndex == -1 && !isFull[m]){
                    smallestDiff = memoryBlocks[m] - jobBlocks[j];
                    if(smallestDiff < 0) continue;
                    currentIndex = m;
                } else if ((smallestDiff > memoryBlocks[m] - jobBlocks[j] && memoryBlocks[m] - jobBlocks[j] >= 0) && (!isFull[m])) {
                    smallestDiff = memoryBlocks[m] - jobBlocks[j];
                    currentIndex = m;
                }
            }
            if(currentIndex == -1){
                jobMemory[j] = -1;
                continue;
            }
            jobMemory[j] = currentIndex;
            isFull[currentIndex] = true;
            currentIndex = -1;

        }
        Output.displayJointTable(jobMemory, memoryBlocks, jobBlocks);
    }
}