import java.util.Scanner;

public class Banker {

    public static void main(String[] args) {
        int processNum, typeNum, maxNum;

        Scanner input = new Scanner(System.in);

        System.out.println("Please type the following values ");
        System.out.println("Number of processes");
        processNum = input.nextInt();

        System.out.println("Number of resource types");
        typeNum = input.nextInt();

        System.out.println("Number of total resources");
        int[] total = new int[typeNum];

        for(int i = 0; i < typeNum;  i++){
            total[i] = input.nextInt();
        }

        int[][] max = new int[processNum][typeNum];
        int[][] allocation = new int[processNum][typeNum];

        System.out.println("Max number of each resource type");

        for(int i = 0; i < processNum; i++){
            for (int j = 0; j < typeNum; j++) {
                max[i][j] = input.nextInt();
            }
        }


        System.out.println("Number of allocated resources by the system");

        for(int i = 0; i < processNum; i++){
            for (int j = 0; j < typeNum; j++) {
                allocation[i][j] = input.nextInt();
            }
        }

        System.out.println("Total system resources are: ");

        for(char c = 'A'; c < 'A' + typeNum; c++){
            System.out.print(new String(String.valueOf(c)) + "\t");
        }
        System.out.println();

        for(int resource : total){
            System.out.print(resource + "\t");
        }
        System.out.println("\n");

        System.out.println("Processes (maximum resources): ");

        for(char c = 'A'; c < 'A' + typeNum; c++){
            System.out.print("\t" + new String(String.valueOf(c)));
        }
        System.out.println();

        for(int i = 0; i < processNum; i++){
            System.out.print("P" + (i+1) + "\t");
            for(int j = 0; j < typeNum; j++){
                System.out.print((i+1) + max[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Processes (currently allocated resources): ");

        for(char c = 'A'; c < 'A' + typeNum; c++){
            System.out.print("\t" + new String(String.valueOf(c)));
        }
        System.out.println();

        for(int i = 0; i < processNum; i++){
            System.out.print("P" + (i+1) + "\t");
            for(int j = 0; j < typeNum; j++){
                System.out.print(allocation[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

    }
}
