import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //Scanner Constructor
        Scanner myScan = new Scanner(System.in);

        //Variable declaration for selection, memory and job amounts
        int selection = 0;
        int memoryBlockAmt = 0;
        int jobAmt = 0;

        Output.greetUser(); //Welcome screen

        System.out.print("How many memory blocks are we working with?: "); //User is prompted for memory block amount
        memoryBlockAmt = myScan.nextInt(); //User is prompted to input amount of memory blocks
        int memoryBlockSize[] = new int[memoryBlockAmt+1]; //+1 to account for compaction block
        memoryBlockSize = Process.inputMemBlocks(memoryBlockSize); //User is prompted for the size of each memory block amount
        Output.displayMemTable(memoryBlockSize); //Memory blocks are displayed to the user

        System.out.println("==============================================================================");
        System.out.print("How many jobs are we working with?: ");
        jobAmt = myScan.nextInt();
        int jobBlockSize[] = new int[jobAmt];
        jobBlockSize = Process.inputJobBlocks(jobBlockSize);
        Output.displayJobTable(jobBlockSize);

        System.out.println("==============================================================================");
        System.out.println("[1] First Fit Memory Allocation");
        System.out.println("[2] Best Fit Memory Allocation");
        System.out.print("Select an option: ");
        selection = myScan.nextInt();

        switch(selection){

            case 1:
                Process.firstFitAllocation(jobBlockSize, memoryBlockSize);
                break;
            case 2:
                Process.bestFitAllocation(jobBlockSize, memoryBlockSize);
                break;
        }
    }
}
