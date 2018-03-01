import java.util.Scanner;
import java.util.ArrayList;

public class Main
{
  static int time = 0;
  static int steps;
	public static void main(String [] args)
	{
		Scanner sc = new Scanner(System.in);
		int rows = sc.nextInt();
		int columns = sc.nextInt();
		int vehicles = sc.nextInt();
		int numberOfRides = sc.nextInt();
    int bonus = sc.nextInt();
		steps = sc.nextInt();
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
      rides[i] = new Ride(start, finish, s, t, i);

      sc.nextLine();
    }

    Car[] cars = new Car[vehicles];
    for (int i = 0; i < vehicles; i++) {
      cars[i] = new Car(i);
    }

    // START
    run(cars, rides);
	}

  public static void run (Car[] cars, Ride[] rides) {
    ArrayList<ArrayList<Integer>> solution = new ArrayList<ArrayList<Integer>>(cars.length);
    for(int i = 0; i < cars.length; i++)  {
      solution.add(new ArrayList<Integer>());
    }
    int availableRides = rides.length;

    while (time < steps || availableRides == 0) {
      for (int i = 0; i < cars.length; i++) {
        if (cars[i].available) {
          int nextRide = closestRide(cars[i], rides);
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
      System.out.print(sol.size());
      for (int ride : sol) {
        System.out.print(" " + ride);
      }
      System.out.println();
    }
  }

  public static int closestRide (Car car, Ride[] rides) {
    Ride closest = null;
    for (Ride r : rides) {
      if (r.available) {
        closest = r;
        break;
      }
    }
    if (closest != null) {
      for(int i = 1; i < rides.length; i++){
        if (rides[i].available && (getDistance(car.position, rides[i].start) < getDistance(car.position, closest.start))) {
          closest = rides [i];
        }
      }
    } else {
      return -1;
    }

    car.available = false;
    car.nextTimeAvailable = closest.timeStart + closest.distance;
    closest.available = false;
    return closest.index;
  }

  public static int nextLongestRide (Car car, Ride[] rides) {
    for (int i = 0; i < rides.length; i++) {
      if (rides[i].available && canFinishRide(car, rides[i])) {
        car.available = false;
        car.nextTimeAvailable = rides[i].timeStart + rides[i].distance;
        car.position = rides[i].finish;
        rides[i].available = false;
  			return i;
      }
    }
    return nextRide(car, rides);
  }

  public static boolean canFinishRide (Car car, Ride ride) {
    int carToRide = getDistance(car.position, ride.start);
    int rideDistance = getDistance(ride.start, ride.finish);
    return getOnTime(car.position, ride.start, ride.timeStart) &&
            ((carToRide + rideDistance) < (steps - time));
  }

  public static int nextRide (Car car, Ride[] rides) {
  	for (int i = 0; i < rides.length; i++) {
      System.out.println(getOnTime(car.position, rides[i].start, rides[i].timeStart));
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
