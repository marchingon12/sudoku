//August Ho

package sudoku.SudokuField;
/**
* This class is for creating a sudoku field with a variety of methods.
* This class is set to public as it is needed from the Main.java file,
* and it contains methods to place a value onto the playing field.
*
* @author         August Ho <august.ho@stud.uni-hannover.de>
* @version        2020 May 08
*/
public class SudokuField {
  /**This variable stores the board values*/
  private byte[][] board;
  /**This variable stores the marker values*/
  private byte[][][] marker;

  /**
   * This is a constructor method that creates the new sudoku field
   *
   * @author         August Ho <august.ho@stud.uni-hannover.de>
   * @version        2020 May 08
   */
  public SudokuField(byte[][] board){
    if(board.length != 9){
        System.exit(2);
    }
    this.board = board;
    this.marker = new byte[9][9][9];
  }

 /**
  * This method draws the sudoku board with the given rules.
  * @return drawBoard Board printed in terminal as String
  */
  public String str(){
    //draw column numbers as new String
    String drawBoard = "\n |0 1 2|3 4 5|6 7 8|\n";
    //add devider for board to starting String
    drawBoard = drawBoard + "-+-----+-----+-----+\n";

    for(int i = 0; i < 9; i++){
      drawBoard = drawBoard + "" + i + "|";
      for(int j = 0; j < 9; j++) {

        if(board[i][j] == -1) {
          //if value in cell = -1 print...
          drawBoard = drawBoard + " ";
        } else {
          //else print value in cell
          drawBoard = drawBoard + board[i][j];
        }

        //for each designated column (for deviders) print...
        if(j==2 || j==5 || j==8) {
         drawBoard = drawBoard + "|";
        } else {
         drawBoard = drawBoard + " ";
        }
      }

      //for each designated row (for deviders) print...
      if(i==2 || i==5 | i==8) {
        drawBoard = drawBoard + "\n-+-----+-----+-----+\n";
      } else {
        drawBoard = drawBoard + "\n |     |     |     |\n";
      }
    }
    return drawBoard;
  }

 /**
  * This method checks if a certain value has been used in a row.
  * Not needed to be made public; only called within the SudokuField class.
  * @param r Row number.
  * @param x Value in cell.
  */

  boolean usedRow(int r, byte x) {
    boolean usedInRow = false;

    for(int i = 0; i < 9; i++) {
      if(this.board[r][i] == x) {
          usedInRow = true;
      }
    }
    return usedInRow;
  }


 /**
  * This method checks if a certain value has been used in a column.
  * Not needed to be made public; only called within the SudokuField class.
  * @param c Column number.
  * @param x Value in cell.
  */

  boolean usedColumn(int c, byte x){
    boolean usedInColumn = false;

    for(int i = 0; i < 9; i++) {
      if(this.board[i][c] == x){
        usedInColumn = true;
      }
    }
    return usedInColumn;
  }


/**
 * This method if a certain value has been used in a box (3x3).
 * Not needed to be made public; only called within the SudokuField class.
 * @param r Row number.
 * @param c Column number.
 * @param x Value in cell.
 */

  boolean usedBox(int r, int c, byte x) {
    boolean usedInBox = false;
    //for every 3 rows one box
    int boxRow = r - r % 3;
    //for every 3 columns one box
    int boxColumn = c - c % 3;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (this.board[boxRow + i][boxColumn + j] == x) {
          usedInBox = true;
        }
      }
    }
    return usedInBox;
  }

/**
 * This method checks if a certain cell is empty.
 * Not needed to be made public; only called within the SudokuField class.
 * calls other boolean functions
 * @param i row number
 * @param j column number
 * @param x Value in cell.
 */
  boolean isCellEmpty(int i, int j, byte x) {
    boolean isCellEmpty = true;
    //if in row i the given value x is used
    if(usedRow(i, x)) {
      isCellEmpty = false;
    }
    //if in column j the given value x is used
    if(usedColumn(j, x)) {
      isCellEmpty = false;
    }
    //if in row i and in column j the given value x is used
    if(usedBox(i, j, x)) {
      isCellEmpty = false;
    }
    return isCellEmpty;
  }


/**
 * This method sets the input value into a cell.
 * @param i row number.
 * @param j column number.
 * @param x Value in cell.
 */
  public void inputValue(int i, int j, byte x) {
    if(isCellEmpty(i, j, x)) {
      this.board[i][j] = x;
      } else {
      System.out.println("Invalid entry\n");  //if cell is not empty the print...
    }
  }

/**
 * This method returns the value in the cell
 * @param i row number.
 * @param j column number.
 * @return byte value of cell
 */
  public byte getCell(int i, int j) {
    return this.board[i][j];
  }


  /**
   * This method stores a value in the marker board.
   * This method is set to public as it is called from the Main.java file.
   * @param i row number.
   * @param j column number.
   * @param x Value in cell.
   */
  public void mark(int i, int j, byte x) {

    //check if given value x has already been set in marker board.
    boolean setInMarker = false;
    for (int a = 0; a < this.marker[i][j].length; a++) {
     if (this.marker[i][j][a] == x) {
       setInMarker = true;
     }
    }

    if(setInMarker = true) {
    int a = 0;
    boolean marked = true;

    while(marked) {
     if(this.marker[i][j][a] == 0){
       this.marker[i][j][a] = x;
       marked = false;
     }
     a++;
    }
    System.out.print(String.format("Marked Value %d for (%d:%d)\n", x, i, j));
    } else {
    System.out.print(String.format("Value %d was already marked for (%d:%d)\n", x, i, j));
    }
 }


 /**
  * This method unmarks a value in the marker board.
  * This method is set to public as it is called from the Main.java file.
  * @param i row number.
  * @param j column number.
  * @param x Value in cell.
  */
  public void unmark(int i, int j, byte x){
    //given x found in marker board?
    boolean setInMarker = false;
    for (int a = 0; a < this.marker[i][j].length; a++) {
      if (this.marker[i][j][a] == x) {
        setInMarker = true;
      }
    }
    if(setInMarker = true) {
      for(int a = 0; a < marker[i][j].length; a++) {
        if(this.marker[i][j][a] == x) {
          this.marker[i][j][a] = -1;
        }
      }
    } else {
      System.out.print(String.format("Unmarked Value %d for (%d:%d)\n", x, i, j));
    }
  }
  /**
  *This method returns the marks of a cell in a string.
  * @param i row number.
  * @param j column number.
  * @return String marked values on board "[]"
  */

  public String viewMark(int i, int j) {
  //initialise empty String
  String drawMarker = "";
    for(int a = 0; a < marker[i][j].length; a++){
      if(this.marker[i][j][a] > 0) {
        drawMarker = drawMarker + this.marker[i][j][a] + ", ";
      }
    }
    drawMarker = "[" + drawMarker;
    //removes surplus comma and space from end of String
    if (drawMarker.endsWith(", ")) {
      drawMarker = drawMarker.substring(0, drawMarker.length() - 2);
    }
    return drawMarker + "]";
  }
}
