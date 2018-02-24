import java.util.ArrayList;

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

  public void cut () {
    ArrayList<Point []> solutions = new ArrayList<Point []>();
    boolean finished = false;
    Point coordinate = new Point(0, 0);

    while (!finished) {
      if (isValid(coordinate)) {
        Point [] newSolution = new Point [2];
        newSolution[0] = coordinate;
        newSolution[1] = getBottomRight(coordinate);
        solutions.add(newSolution);
      }
    }
  }

  public Point getBottomRight (Point coordinate) {
    return new Point(coordinate.x, coordinate.y + max);
  }

  public boolean isValid (Point coordinate) {
    int tomatoes = 0;
    int mushrooms = 0;
    int x = coordinate.x;
    int y = coordinate.y;

    for (int col = y; col < y + max; col++) {
      if (pizza[x][col].tomato) {
        tomatoes++;
      } else {
        mushrooms++;
      }
    }

    if (tomatoes >= min && mushrooms >= min) {
      return true;
    }
    return false;
  }

  public Point getNextPosition (Point coordinate, boolean valid)
  {
    int x1 = coordinate.x;
    int y1 = coordinate.y;
    int x2, y2;
    if (valid) {
      x2 = x1 + max;
      if (x2 > rows) {
        y2 = y1 + 1;
        x2 = 0;
      }
    } else {
      x2 = x1 + 1;
      if (x2 > rows) {
        y2 = y1 + 1;
        x2 = 0;
      }
    }

    if (y2 > columns) {
      return null;
    }

    return new Point(x2, y2);
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
