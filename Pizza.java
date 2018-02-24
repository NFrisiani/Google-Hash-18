public class Pizza {
  int rows, columns, min, max;
  boolean [][] pizza;

  public Pizza (int givenRows, int givenColumns, int givenMin,
                int givenMax, boolean [][] givenPizza) {
    this.rows = givenRows;
    this.columns = givenColumns;
    this.min = givenMin;
    this.max = givenMax;
    this.pizza = givenPizza;
    printPizza();
  }

  public void printPizza () {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        System.out.print(this.pizza[i][j]);
      }
      System.out.println();
    }
  }
}
