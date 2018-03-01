import java.util.Scanner;

public class Main
{
  static int time = 0;
  static Ride[] rides;
	public static void main(String [] args)
	{
    int time = 0;
		Scanner sc = new Scanner(System.in);
		int rows = sc.nextInt();
		int columns = sc.nextInt();
		int vehicles = sc.nextInt();
		int numberOfRides = sc.nextInt();
    int bonus = sc.nextInt();
		int steps = sc.nextInt();
		sc.nextLine();

    rides = new Ride[numberOfRides];

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
      System.out.println(rides[i].distance);

      sc.nextLine();
    }

    Car[] cars = new Car[vehicles];
    for (int i = 0; i < vehicles; i++) {
      cars[i] = new Car();
    }

    // START
    System.out.println("Finish");
	}

  public static Ride nextRide (Car car) {
  	for (int i = 0; i < rides.length - 1; i++) {
  		if (rides[i].available && getOnTime(car.position, rides[i].start, rides[i].timeStart)) {
        car.available = false;
  			return rides[i];
  		}
  	}
  	return null;
  }

  public static int getDistance (Intersection s, Intersection f) {
    return Math.abs((Math.abs(s.x) - Math.abs(f.x)) + (Math.abs(s.y) - Math.abs(f.y)));
  }

  public static boolean getOnTime (Intersection f, Intersection s, int startTime) {
    return (getDistance(f, s) < (startTime - time));
  }
}
