import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MatrixLinkedList {
    private MatrixNode head;
    private int rows, columns;

    private static class MatrixNode {
        int data;
        MatrixNode right, down;

        private MatrixNode  (int data){
            this.data = data;
            right = null;
            down = null;
        }
    }
    public MatrixLinkedList (int rows, int columns){
        if(rows <=0 || columns <=0) throw new IllegalArgumentException("Rows and columns are invalid");
        head = null;
        this.rows = rows;
        this.columns = columns;
    }

    public void createMatrix(ArrayList <Integer> [] matrixData) {
        if (matrixData == null || matrixData.length == 0 || matrixData[0].isEmpty()) {
            throw new NoSuchElementException("Matrix is empty!!");
        }
        head = new MatrixNode(matrixData[0].get(0));
        MatrixNode current = head;

        for(int i=1; i<matrixData[0].size(); i++){
            current.right = new MatrixNode(matrixData[0].get(i));
            current =current.right;
        }
        MatrixNode start = head;

        for(int i=1; i< matrixData.length; i++){
            start.down = new MatrixNode(matrixData[i].get(0));
            current = start.down;

            MatrixNode downLink = start.right;
            for(int j=0; j< matrixData[0].size(); j++){
                if (j + 1 < matrixData[i].size()) {
                    current.right = new MatrixNode(matrixData[i].get(j + 1));
                }
                if (current.right != null) {
                    current = current.right;
                }
                downLink.down = current;
                if(downLink.right != null) {
                    downLink = downLink.right;
                }
            }
            start = start.down;
        }
    }

    public ArrayList<Integer> getRow(int rowIndex){
        validColumnIndex(rowIndex);
        ArrayList <Integer> arrayRow = new ArrayList<>();
        MatrixNode rowNode = getRowNode(rowIndex);
        arrayRow.add(0,rowNode.data);
        for (int i=1; i<columns && rowNode.right != null; i++) {
            arrayRow.add(i,rowNode.right.data);
            rowNode = rowNode.right;
        }
        return arrayRow;
    }
    private MatrixNode getRowNode(int rowIndex){
        MatrixNode rowNode = head;
        for (int i=0; i<rowIndex && rowNode.down != null; i++){
            rowNode = rowNode.down;
        }
        return rowNode;
    }
    public ArrayList<Integer> getColumn (int colIndex) {
        validColumnIndex(colIndex);
        ArrayList <Integer> arrayCol = new ArrayList<>();
        MatrixNode colNode = getColumnNode(colIndex);
        arrayCol.add(colNode.data);
        while (colNode.down != null){
            colNode = colNode.down;
            arrayCol.add(colNode.data);
        }
        return arrayCol;
   }
    private MatrixNode getColumnNode (int colIndex){
        MatrixNode colNode = head;
        for (int i=0; i<colIndex && colNode.right != null; i++){
            colNode = colNode.right;
        }
        return colNode;
    }


    public int rowSum (int rowIndex) {
        validRowIndex(rowIndex);
        ArrayList <Integer> sumOfRow = getRow(rowIndex);
        return sum(sumOfRow, 0);
    }
    public double columnAverage(int colIndex){
        validColumnIndex(colIndex);
        ArrayList <Integer> avgOfColumn = getColumn(colIndex);
        double avg = sum(avgOfColumn, 0);
        return avg/ rows;
    }
    private int sum(ArrayList<Integer> myList, int index){
        if(index == myList.size() ) return 0;
        return myList.get(index) + sum(myList, index +1);
    }


    public boolean find(int data){
        MatrixNode node = head;
       for(int i=0; i< rows; i++){
           MatrixNode current = node;
           for(int j= 0; j<columns; j++){
               if(node.data == data){
                   return true;
               }
               node = node.right;
           }
           node = current.down;
       }
       return false;
    }


    public void replaceData(int rowIndex, int colIndex, int newData){
        validRowIndex(rowIndex);
        validColumnIndex(colIndex);
        MatrixNode rowNode = getRowNode(rowIndex);
        rowNode = getNode(rowNode, colIndex, 0);
        rowNode.data = newData;
    }
    private MatrixNode getNode(MatrixNode node,int colIndex, int index){
        if(index == colIndex) return node;
        return getNode(node.right, colIndex, index +1);
    }

    @Override
    public String toString() {
        MatrixNode node = head;
        StringBuilder result = new StringBuilder();
        for(int i=0; i< rows; i++){
            result.append("| ");
            MatrixNode current = node;
            while(node != null) {
                result.append(node.data).append(" | ");
                node = node.right;
            }
            result.append("\n");
            if (current != null) {
                node = current.down;
            }
        }
        return result.toString();
    }
    private void matrixNotEmpty(){
        if(head == null){
            throw new NoSuchElementException("Matrix is empty");
        }
    }

    private void validColumnIndex(int colIndex){
        matrixNotEmpty();
        if(colIndex < 0 || colIndex >= columns){
            throw new IndexOutOfBoundsException("Invalid column!! " + colIndex);
        }
    }
    private void validRowIndex(int rowIndex){
        matrixNotEmpty();
        if(rowIndex < 0 || rowIndex >= rows){
            throw new IndexOutOfBoundsException("Invalid row!! " + rowIndex);
        }
    }

    public void scalarMultiply(int scalar) {
        MatrixNode node = head;
        int scalarNode;
        for(int i =0 ; i< rows; i++){
            MatrixNode current = node;
            for(int j=0 ; j< columns; j++){
                scalarNode = scalar * node.data;
                replaceData(i,j,scalarNode);
                node = node.right;
            }
            node = current.down;
        }
    }

    public void add(MatrixLinkedList matrix){
        if(matrix.rows != rows || matrix.columns != columns) {
            System.out.println("Invalid Addition: Matrices must have same dimensions...");
            System.out.println("So, the first matrix will be the same!!");
            return;
        }

        MatrixNode firstMatrixNode = head;
        MatrixNode secondMatrixNode = matrix.head;

        for(int i=0; i< rows; i++){
            MatrixNode start = firstMatrixNode;
            MatrixNode start2 = secondMatrixNode;
            while (firstMatrixNode != null){
                firstMatrixNode.data += secondMatrixNode.data;
                firstMatrixNode = firstMatrixNode.right;
                secondMatrixNode = secondMatrixNode.right;
            }

            if (start != null) {
                firstMatrixNode = start.down;
            }
            secondMatrixNode = start2.down;
        }
    }




}
