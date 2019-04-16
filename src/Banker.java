package com.company;

import java.util.LinkedList;
import java.util.Scanner;

public class Banker {

    private int typeNum;
    private int processNum;
    private static boolean findSol = false;

    private int getProcessNum() {
        return processNum;
    }

    private void setProcessNum(int processNum) {
        this.processNum = processNum;
    }

    private int getTypeNum() {
        return typeNum;
    }

    private void setTypeNum(int typeNum) {
        this.typeNum = typeNum;
    }

    private Banker(){

    }

    public static void main(String[] args) {
        Banker banker = new Banker();

        Scanner input = new Scanner(System.in);

        System.out.println("Please type the following values ");
        System.out.println("Number of processes");
        banker.setProcessNum(input.nextInt());

        System.out.println("Number of resource types");
        banker.setTypeNum(input.nextInt());

        int processNum = banker.getProcessNum();
        int typeNum = banker.getTypeNum();

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

        int[] available = new int[typeNum];

        for(int i = 0; i < typeNum; i++){
            available[i] = total[i];

            for(int j = 0; j < processNum; j++){
                available[i] -= allocation[j][i];
            }
        }

        int[][] need = new int[processNum][typeNum];

        for(int i = 0; i < processNum; i++){
            for(int j = 0; j < typeNum; j++){
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }

        System.out.println("Total system resources are: ");

        for(char c = 'A'; c < 'A' + typeNum; c++){
            System.out.print(c + "\t");
        }
        System.out.println();

        for(int resource : total){
            System.out.print(resource + "\t");
        }
        System.out.println("\n");

        System.out.println("Available system resources are: ");

        for(char c = 'A'; c < 'A' + typeNum; c++){
            System.out.print(c + "\t");
        }
        System.out.println();

        for(int resource : available){
            System.out.print(resource + "\t");
        }
        System.out.println("\n");

        System.out.println("Processes (maximum resources): ");

        for(char c = 'A'; c < 'A' + typeNum; c++) System.out.print("\t" + c);
        System.out.println();

        for(int i = 0; i < processNum; i++){
            System.out.print("P" + (i+1) + "\t");
            for(int j = 0; j < typeNum; j++){
                System.out.print(max[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Processes (currently allocated resources): ");

        for(char c = 'A'; c < 'A' + typeNum; c++){
            System.out.print("\t" + c);
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

        System.out.println("Processes (possibly needed resources): ");

        for(char c = 'A'; c < 'A' + typeNum; c++){
            System.out.print("\t" + c);
        }
        System.out.println();

        for(int i = 0; i < processNum; i++){
            System.out.print("P" + (i+1) + "\t");
            for(int j = 0; j < typeNum; j++){
                System.out.print(need[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Determining safe sequence");

        LinkedList<Integer> safeSequence = new LinkedList<>();
        boolean[] takenProcess = new boolean[processNum];

        banker.safeSequence(takenProcess, allocation, max, need, available, safeSequence);
//        if(!findSol){System.out.println("No valide sequence: DEADLOCK");}
    }

    private boolean isAvailable(int processId, int[][] max, int[][] need, int[] available){

        boolean availableFlag = true;
        int typeNum = getTypeNum();

        for(int i = 0; i < typeNum; i++)
            if (need[processId][i] > available[i]) {
                availableFlag = false;
            }
        return availableFlag;
    }

    private void safeSequence(boolean[] takenProcess, int[][] allocated, int[][] max, int[][] need, int[] available, LinkedList<Integer> safeSeq){
        int processNum = getProcessNum();
        int typeNum = getTypeNum();
        for (int i = 0; i < processNum; i++){
            if(!takenProcess[i] && isAvailable(i, max, need, available)){
                takenProcess[i] = true;

                for(int j = 0; j < typeNum; j++){
                    available[j] += allocated[i][j];
                }
                safeSeq.add(i);

                safeSequence(takenProcess, allocated, max, need, available, safeSeq);
                takenProcess[i] = false;

                for(int j = 0; j < typeNum; j++){
                    available[j] -= allocated[i][j];
                }

                if(safeSeq.size() == processNum ){
                    findSol=true;
                    System.out.print("(");
                    for(int process : safeSeq){
                        System.out.print("P" + (process+1));

                        if(safeSeq.indexOf(process) != safeSeq.size()-1){
                            System.out.print(", ");
                        } else {
                            System.out.print(")"+"\n");
                        }
                    }
                }
            }

        }
        if (safeSeq.size() <processNum){
            System.out.println("No solutions: DEADLOCK");
        }


    }
}

