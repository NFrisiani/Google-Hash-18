import java.util.Scanner;

public class Main
{
	public static void main(String [] args)
	{
		Scanner sc = new Scanner(System.in);
		int rows = sc.nextInt();
		int columns = sc.nextInt();
		int vehicles = sc.nextInt();
		int numberOfRides = sc.nextInt();
    int bonus = sc.nextInt();
		int steps = sc.nextInt();
		sc.nextLine();

    Ride[] rides = new Ride[numberOfRides];

    int a, b, x, y, s, t;
		for (int i = 0; i < numberOfRides; i++) {
      a = sc.nextInt();
      b = sc.nextInt();
      x = sc.nextInt();
      y = sc.nextInt();
      s = sc.nextInt();
      t = sc.nextInt();

      Intersection start = new Intersection(a, b);
      Intersection finish = new Intersection(x, y);
      rides[i] = new Ride(start, finish, s, t);

      sc.nextLine();
    }

    Car[] cars = new Car[vehicles];
    for (int i = 0; i < vehicles; i++) {
      cars[i] = new Car();
    }

    // START
    System.out.println("Finish");
	}
}
