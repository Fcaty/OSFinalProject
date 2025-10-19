public class Output {

    public static void greetUser(){
        System.out.println("==============================================================================");
        System.out.println("COMSCI 2101 - Operating Systems Final Project");
        System.out.println("Bustos, Rion Fauszt O.");
        System.out.println("Garcia, Justine Rey I.");
        System.out.println("Santiago, Neil Jacob E.");
        System.out.println("Siega, Vinze Norbert D.");
        System.out.println("Tablanza, Anne Margaret L.");
        System.out.println("Concept of Choice: Memory Management Allocation");
        System.out.println("Welcome to the program!");
        System.out.println("==============================================================================");
    }

    public static void displayMemTable(int[] memoryBlocks){
            System.out.println("+-----+-----------+");
        for(int i = 0; i < memoryBlocks.length - 1; i++) {
            System.out.printf ("|%5s|%11s|\n","M"+(i+1), memoryBlocks[i]+"K");
            System.out.println("+-----+-----------+");
        }
    }

    public static void displayJobTable(int[] jobBlocks){
        System.out.println("+-----+-----------+");
        for(int i = 0; i < jobBlocks.length; i++) {
            System.out.printf ("|%5s|%11s|\n","J"+(i+1), jobBlocks[i]+"K");
            System.out.println("+-----+-----------+");
        }
    }

    public static void displayJointTable(int[] jobMemory, int[] memoryBlocks, int[] jobBlocks){
        boolean hasPrinted[] = new boolean[memoryBlocks.length - 1];
        int storedIndex[] = new int[memoryBlocks.length - 1];
        int totalMemory = 0;
        int totalJob = 0;

        for(int i = 0; i < memoryBlocks.length; i++){
            totalMemory += memoryBlocks[i];
        }

        for(int jm = 0; jm < jobBlocks.length; jm++){
            System.out.println("+--------+--------------+-----+-----------+--------+-------------------------+");
            System.out.println("|                              STEP "+(jm+1)+"                                        |");
            System.out.println("+--------+--------------+-----+-----------+--------+-------------------------+");
            if(jobMemory[jm] == -1){
                System.out.println("Job "+(jm + 1)+" is waiting...\n\n\n");
                continue;
            }
            System.out.printf ("|%-8s|%-14s|%-5s|%-11s|%-8s|%-25s|\n"," Memory"," Memory block", " Job", " Job block"," Status"," Internal");
            System.out.printf ("|%-8s|%-14s|%-5s|%-11s|%-8s|%-25s|\n"," Loc"," size", " Loc", " size"," "," Fragmentation");
            System.out.println("+--------+--------------+-----+-----------+--------+-------------------------+");
            for(int m = 0; m < memoryBlocks.length - 1; m++){
                if(jobMemory[jm] == m) {
                    System.out.printf ("|%-8s|%-14s|%-5s|%-11s|%-8s|%-25s|\n"," M"+(m+1)+" ", memoryBlocks[m]+"K", "J"+(jm + 1), jobBlocks[jm]," BUSY ", memoryBlocks[m] - jobBlocks[jm]);
                    hasPrinted[m] = true;
                    storedIndex[m] = jm;
                    totalJob += jobBlocks[jm];
                } else if (hasPrinted[m]){
                    System.out.printf ("|%-8s|%-14s|%-5s|%-11s|%-8s|%-25s|\n"," M"+(m+1)+" ", memoryBlocks[m]+"K", "J"+(storedIndex[m] + 1), jobBlocks[storedIndex[m]]," BUSY ", memoryBlocks[m] - jobBlocks[storedIndex[m]]);
                } else {
                    System.out.printf ("|%-8s|%-14s|%-5s|%-11s|%-8s|%-25s|\n"," M"+(m+1)+" ", memoryBlocks[m]+"K", " ", " "," FREE ", " ");
                }
            }
            System.out.println("+--------+--------------+-----+-----------+--------+-------------------------+");
            System.out.printf("|%-23s|%-17s|%-34s|\n", " Total Available: "+totalMemory+"K ", " Total Used: "+totalJob+"K ", " ");
            System.out.println("+--------+--------------+-----+-----------+--------+-------------------------+");
        }
    }
}

/* For future Rion, please remove jobMemory[], you really didn't need it, instead:
    - declare a new array allocJobLocation[]
    - whenever a job is assigned to a memory location, STORE THE INDEX THERE.
    - use the index to retrieve old Job Memory values whenever called by a boolean array hasPrinted[]
* */
