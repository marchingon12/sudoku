//August Ho

package sudoku;
import SudokuField.*;
import java.util.Scanner;

public class Main{
  public static void main(String[] args) {

    byte[][] sudoku1 = {
      {-1,   6,  -1,  -1,  -1,  -1,   4,   2,   5},
      { 5,   7,  -1,  -1,  -1,  -1,  -1,   8,   1},
      {-1,  -1,  -1,   4,   3,  -1,   9,  -1,  -1},

      {-1,   5,  -1,   9,   2,  -1,  -1,   7,   4},
      {-1,  -1,  -1,   3,   8,   4,  -1,  -1,  -1},
      { 8,   4, -1,   5,   6,   7, -1,   9,  -1},

      {-1,  -1,   2,  -1,   1,  -1,  -1,  -1,  -1},
      {-1,   3,   9,  -1,  -1,   6,   7,  -1,   8},
      {-1,  -1,  -1,  -1,  -1,   9,   6,  -1,  -1}
    }; //-1 means empty cell

    //create a new board with the given values of soduko1
    SudokuField playBoard = new SudokuField(sudoku1);
    //print the board with values; -1 equals empty cells
    System.out.println(playBoard.str());

    Scanner scanner = new Scanner(System.in);
    while(true) {
      System.out.println("Enter command [mode-row-column-value]:");

      //initialise new string added next to first string (selected mode)
      String inputString = scanner.next();
      //splits inputString into smaller Strings; each assigned using an array method
      String[] Input = inputString.split("-");
      //assign second String for row number
      int i = Integer.parseInt(Input[1]);
      //assign second String for column number
      int j = Integer.parseInt(Input[2]);

      //if first String is mark
      if(Input[0].equals("mark")) {
        System.out.println(String.format("\nCommand: [mark %d %d %d]", i, j, Byte.parseByte(Input[3])));
        playBoard.mark(i,j,Byte.parseByte(Input[3]));
      }
      //if first String is unmark
      if(Input[0].equals("unmark")) {
        System.out.println(String.format("\nCommand: [unmark %d %d %d]", i, j, Byte.parseByte(Input[3])));
        playBoard.unmark(i,j,Byte.parseByte(Input[3]));
      }
      //if first String is viewmarks
      if(Input[0].equals("viewmarks")) {
        System.out.println(String.format("\nCommand: [viewmarks %d %d]", i, j));
        System.out.println(playBoard.viewMark(i,j));
      }
      //if first String is enter
      if(Input[0].equals("enter")) {
        System.out.println(String.format("\nCommand: [enter %d %d %d]", i, j, Byte.parseByte(Input[3])));
        playBoard.inputValue(i,j,Byte.parseByte(Input[3]));
      }
      //print board with new values / unchanged
      System.out.println(playBoard.str());
    }
  }
}
