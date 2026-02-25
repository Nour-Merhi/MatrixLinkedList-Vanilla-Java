import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> [] arr = new ArrayList[4];
        arr[0] = new ArrayList<>(Arrays.asList(10,11,12,13));
        arr[1] = new ArrayList<>(Arrays.asList(14,15,16,17));
        arr[2] = new ArrayList<>(Arrays.asList(18,19,20,21));
        arr[3] = new ArrayList<>(Arrays.asList(22,23,24,25));

        MatrixLinkedList m1 = new MatrixLinkedList(4,4);
        m1.createMatrix(arr);
        System.out.println("\n......Creating Matrix......");
        System.out.println("Linked List Matrix: ");
        System.out.println(m1);

        System.out.println("......Testing getting certain rows and columns......");
        System.out.println("Getting row 2: \n" + m1.getRow(2));
        System.out.println("Getting column 1: \n" + m1.getColumn(1));

        System.out.println("____________________________________________________");
        System.out.println("\n......Testing Sum and Average Methods......");
        System.out.println("Sum of row 1 = " + m1.rowSum(1));
        System.out.println("Average of column 3 = " + m1.columnAverage(3));

        System.out.println("____________________________________________________");
        System.out.println("\n......Testing Find, ScalarMultiply, Replace Methods......");
        System.out.println("Finding data 8 in the matrix... \n" + m1.find(8) );

        System.out.println("Replacing data at row 2 column 3 by 20...");
        m1.replaceData(2,3,20);
        System.out.print(m1);

        m1.scalarMultiply(2);
        System.out.print("\nMultiplying the whole matrix by scalar: \n" + m1);
        System.out.println("____________________________________________________");

        ArrayList<Integer> [] array = new ArrayList[4];
        array[0] = new ArrayList<>(Arrays.asList(17,18,19,20));
        array[1] = new ArrayList<>(Arrays.asList(21,22,23,24));
        array[2] = new ArrayList<>(Arrays.asList(31,32,33,34));
        array[3] = new ArrayList<>(Arrays.asList(13,14,15,16));

        MatrixLinkedList m2 = new MatrixLinkedList(4,4);
        m2.createMatrix(array);
        System.out.println("\n......Testing addition of matrix m2 to m1......");
        System.out.println("Matrix m1: \n" + m1);
        System.out.println("Matrix m2: \n" + m2);
        m1.add(m2);
        System.out.println("Matrix m1 after adding m2 to it:  \n" + m1);

        System.out.println("......Testing adding a matrix with different dimensions from m1......");
        ArrayList<Integer> [] arrays = new ArrayList[4];
        arrays[0] = new ArrayList<>(Arrays.asList(17,18,19,20,30));
        arrays[1] = new ArrayList<>(Arrays.asList(21,22,23,24,32));
        arrays[2] = new ArrayList<>(Arrays.asList(31,32,33,34,33));
        arrays[3] = new ArrayList<>(Arrays.asList(13,14,15,16,34));

        MatrixLinkedList m3 = new MatrixLinkedList(4,5);
        m3.createMatrix(arrays);
        System.out.println("Matrix m1: \n" + m1);
        System.out.println("Matrix m3: \n" + m3);
        m1.add(m3);
        System.out.println("Matrix m1 after adding m3 to it:  \n" + m1);

    }
}