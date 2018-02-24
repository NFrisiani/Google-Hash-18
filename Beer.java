import java.util.Scanner;
import java.util.ArrayList;

public class Beer
{
	static int rows, columns, min, max;

	public static void main(String [] args)
	{
		Scanner sc = new Scanner(System.in);
		rows = sc.nextInt();
		columns = sc.nextInt();
		min = sc.nextInt();
		max = sc.nextInt();
		sc.nextLine();

    boolean[][] pizza = new boolean[rows][columns];

		for (int i = 0; i < rows; i++) {
      String line = sc.next();
      for (int j = 0; j < columns; j++) {
        pizza[i][j] = line.charAt(j) == 'T';
      }
      sc.nextLine();
    }

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        System.out.print(pizza[i][j]);
      }
      System.out.println();
    }
	}
}
