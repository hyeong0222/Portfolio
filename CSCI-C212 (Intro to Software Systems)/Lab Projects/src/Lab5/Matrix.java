package Lab5;
////////////////////////////////////////////////////////////////////////////////////
//
//  Lab 5
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Feb 13th
//
//
//////////////////////////////////////////////////////////////////////////////////

public class Matrix {
	private double[][] matrix;
	private final int NUMROW; 
	private final int NUMCOL;
	
	
	// A method that would define variables NUMROW and NUMCOL, which would indicate the length of rows and columns, respectively.
	public Matrix(double[][] m) { 
		NUMROW = m.length;
		NUMCOL = m[0].length;
		
		matrix = m;
	}
	
	
	// A method that would take a matrix and return the string value of it.
	public String toString() {
		String temp = "";
		temp += "[";
		
		// This loop would concatenate each values of the matrix into pre-defined string 'temp'.
		for (int row = 0; row < NUMROW; row++) {
			for (int col = 0; col <NUMCOL; col++) {
				temp += matrix[row][col];	
				// After concatenating each value into the string, unless its the last value of the row, the comma sign will be added afterwards.
				if (col != (NUMCOL-1)) {
					temp += ", ";
				}
			}
			// After the last value of a row is concatenated into the string, next row values will start to be concatenated at the next line.
			if (row != (NUMROW - 1)) {
				temp += "\n";
			}
		}
		
		temp += "]";
		return temp;
	}
	
	
	// This method would take a matrix, then transpose it and return as a new matrix.
	public Matrix transposeMatrix() { 
		double[][] transpose = new double [NUMCOL][NUMROW];
		
		// This loop would switch the row value and the column value of a value in a matrix to transpose
		for (int row = 0; row < NUMCOL; row++) {
			for (int col = 0; col < NUMROW; col++) {
				transpose [row][col] = matrix [col][row];
			}
		}
		return new Matrix (transpose);
	}
	
	
	// This method would take a matrix, identifies the positions of its diagonal values, then changes the values above the diagonal to 0.  
	public Matrix getUpperDiagonal() { 
		double[][] upperDiagonal = new double [NUMROW][NUMCOL];
		
		// Since the column number and row number of diagonal values are identical to each other, this for loop will identify and change
		// the values to 0 if the row number is smaller than column number
		for(int row = 0; row < NUMROW; row++) {
			for (int col = 0; col < NUMCOL; col++) {
				if (col > row) {
					upperDiagonal [row][col] = 0.0;
				}
				else upperDiagonal [row][col] = matrix [row][col];
			}
		}
		return new Matrix (upperDiagonal);
	} 
	
	
	// This method would take a matrix, identifies the positions of its diagonal values, then changes the values below the diagonal to 0.  
	public Matrix getLowerDiagonal() {
		double[][] lowerDiagonal = new double [NUMROW][NUMCOL];
		
		// Since the column number and row number of diagonal values are identical to each other, this for loop will identify and change
		// the values to 0 if the column number is smaller than row number
		for(int row = 0; row < NUMROW; row++) {
			for (int col = 0; col < NUMCOL; col++) {
				if (col < row) {
					lowerDiagonal [row][col] = 0.0;
				}
				else lowerDiagonal [row][col] = matrix [row][col];
			}
		}
		return new Matrix (lowerDiagonal);
	} 
	
	
	// This method would combine getUpperDiagonal method and getLowerDiagonal method to change all values of the matrix to 0 except
	// for the diagonal values.
	public Matrix getDiagonal() { 
		return this.getUpperDiagonal().getLowerDiagonal();
	} 
	
	
	// Like getDiagonal method, this method would change all values of the matrix to 0 except for the anti-diagonal values.
	// Anti-diagonal is the diagonal from the bottom left to upper right.
	public Matrix getAntiDiagonal () { 
		double[][] antiDiagonal = new double [NUMROW][NUMCOL];
		int antiDiagCol = NUMCOL;
		
		// This loop would compare the column numbers of two different matrix. 
		for(int row = 0; row < NUMROW; row++) {
			for(int col = 0; col < NUMCOL; col++) {
				if (col != antiDiagCol - 1) {
					antiDiagonal [row][col] = 0.0;
				}
				else {
					antiDiagonal [row][col] = matrix [row][col];
					antiDiagCol--;
				}
			}
		}
		return new Matrix (antiDiagonal);
	} 
	
	
	// This method would compare the length of the row and the column, and return a boolean value.  
	public boolean isSquareMatrix() {
		return (NUMROW == NUMCOL);
	} 		
		
	
	// This method would return True if the given matrix is an identity matrix.
	// Identity matrix is a square matrix with value 1s on the diagonal, and 0s on the rest.
	public boolean isIdentityMatrix () { 
		
		int matrixRow = NUMROW;
		int matrixCol = NUMCOL;
		double [][] m1 = new double[matrixRow][matrixCol];

		for (int i = 0; i < matrixRow; i++) {
			for (int j = 0; j < matrixCol; j++) {
				m1[i][j] = 1;
			}
		}
		
		Matrix temp = new Matrix(m1);
		m1 = temp.getDiagonal().matrix;
		temp = new Matrix(m1);
		
		return (isSquareMatrix() && isEqual(temp));
	}
		

	// This method would compare each values within two different matrix, and return a boolean value.
	public boolean isEqual (Matrix m) {
		int mRow = m.matrix.length;
		int mCol = m.matrix[0].length;
		
		if (NUMROW == mRow && NUMCOL == mCol) {
			for (int i = 0; i < mRow; i++) {
				for (int j = 0; j < mCol; j++) {
					if (m.matrix[i][j] != matrix[i][j]) {
						return false;
					}
				}
			}
			return true;
		}
		else return false;
	}
	
	
	// This main method would be used as test cases.
	public static void main(String[] args) { 
		double [][] array = {
				{3, 1, 2, 3}, 
				{4, 5, 6, 7},
				{8, 9, 1, 1},
				{1, 1, 1, 1}};
		Matrix m = new Matrix(array);

		System.out.println(m);
		System.out.println(m.getDiagonal());
		
		double [][] array1 = {
				{3, 1, 2, 3}, 
				{4, 5, 6, 7},
				{8, 9, 1, 1},
				{1, 1, 1, 1}};
		Matrix m1 = new Matrix(array1);
		
		double [][] array2 = {
				{1, 0, 0, 0}, 
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1}};
		Matrix m2 = new Matrix(array2);
		
		System.out.println(m2.isIdentityMatrix());	
	}
	
}
