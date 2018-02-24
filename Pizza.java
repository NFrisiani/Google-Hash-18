public class Pizza {
  int rows, columns, min, max;
  Cell [][] pizza;

  public Pizza (int givenRows, int givenColumns, int givenMin,
                int givenMax, boolean [][] givenPizza) {
    rows = givenRows;
    columns = givenColumns;
    min = givenMin;
    max = givenMax;
    pizza = new Cell[rows][columns];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++ ) {
        pizza[i][j] = new Cell(i, j, givenPizza[i][j], false);
      }
    }
    printPizza();
  }

  public void printPizza () {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (pizza[i][j].tomato) {
          System.out.print("T");
        } else {
          System.out.print("M");
        }
      }
      System.out.println();
    }
  }
}
