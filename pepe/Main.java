import java.util.Scanner;
import java.util.ArrayList;

public class Main
{
  static int time = 0;
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
    run(cars, rides, steps);
	}

  public static void run (Car[] cars, Ride[] rides, int steps) {
    ArrayList<ArrayList<Integer>> solution = new ArrayList<ArrayList<Integer>>(cars.length);
    for(int i = 0; i < cars.length; i++)  {
      solution.add(new ArrayList<Integer>());
    }
    int availableRides = rides.length;

    while (time < steps || availableRides == 0) {
      for (int i = 0; i < cars.length; i++) {
        if (cars[i].available) {
          int nextRide = nextRide(cars[i], rides);
          if (nextRide > -1) {
            solution.get(i).add(nextRide);
            availableRides--;
          }
        } else {
          if (cars[i].nextTimeAvailable == time) {
            cars[i].available = true;
          }
        }
      }
      time++;
    }
    for (ArrayList<Integer> sol : solution) {
      System.out.println(sol.size() + " " + sol);
    }
  }

  public static int nextRide (Car car, Ride[] rides) {
  	for (int i = 0; i < rides.length - 1; i++) {
  		if (rides[i].available && getOnTime(car.position, rides[i].start, rides[i].timeStart)) {
        car.available = false;
        car.nextTimeAvailable = rides[i].timeStart + rides[i].distance;
        car.position = rides[i].finish;
        rides[i].available = false;
  			return i;
  		}
  	}
  	return -1;
  }

  public static int getDistance (Intersection s, Intersection f) {
    return Math.abs((Math.abs(s.x) - Math.abs(f.x)) + (Math.abs(s.y) - Math.abs(f.y)));
  }

  public static boolean getOnTime (Intersection f, Intersection s, int startTime) {
    return (getDistance(f, s) < (startTime - time));
  }
}
