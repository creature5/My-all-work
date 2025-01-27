package newToJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Rekursia7 {
	private static int k;
    private static int arraySize;
    private static int[] used;
    private static Integer[] combo;
    private static ArrayList<Integer[]> combinations;
    private static int sum;
    private static int[] numbers;

    public static void main(String[] args) {
        processInput();
        arraySize = numbers.length;
        used = new int[arraySize];
        combinations = new ArrayList<Integer[]>();
        for (int i = 1; i <= numbers.length; i++)
        {
            k = i;
            combo = new Integer[k];
            GenerateCombo(0);
        }

        ArrayList<Integer[]> finalList = new ArrayList<Integer[]>();
        finalList = removeDuplicates(combinations);

        for (Integer[] comb : finalList)
        {
            print(comb);
        }
    }

    private static void processInput()
    {
    	Scanner input = new Scanner(System.in);
        arraySize = input.nextInt();
        input.close();
        numbers = new int[arraySize];
        for (int i = 0; i < arraySize; i++)
        {
            numbers[i] = input.nextInt();
        }
        sum = input.nextInt();
    }

    static ArrayList<Integer[]> removeDuplicates(ArrayList<Integer[]> inputList)
    {
        ArrayList<Integer[]> finalList = new ArrayList<Integer[]>();
        for (Integer[] current : inputList)
        {
            if (!containsSow(finalList, current))
            {
                finalList.add(current);
            }
        }
        return finalList;
    }

    static boolean containsSow(ArrayList<Integer[]> list, Integer[] comparedValue)
    {
        for (Integer[] listValue : list)
        {
            if (Arrays.equals(listValue,comparedValue))
            {
                return true;
            }
        }
        return false;
    }

    static void GenerateCombo(int startIndex)
    {
        if (startIndex >= k)
        {
            if (sum(combo) == sum)
            {
                Integer[] tmpArr = new Integer[k];
                tmpArr = combo.clone();
                Arrays.sort(tmpArr);
                combinations.add(tmpArr);
            }
            return;
        }

        for (int i = 0; i < arraySize; i++)
        {
            if (used[i] == 0)
            {
                used[i] = 1;
                combo[startIndex] = numbers[i];
                GenerateCombo(startIndex + 1);
                used[i] = 0;
            }
        }
    }

    static void print(Integer[] arr)
    {
        System.out.print("{");
        for (int i = 0; i < arr.length; i++)
        {
        	System.out.print(" " + arr[i]);
        }
        System.out.println(" }");
    }

    static int sum(Integer[] x)
    {
        int result=0;
        for(int i=0;i<x.length;i++)
        {
            result+=x[i];
        }
        return result;
    }
      
}
