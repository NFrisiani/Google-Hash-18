import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

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

    ArrayList<Ride> rides = new ArrayList<Ride>(numberOfRides);

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
      rides.add(new Ride(start, finish, s, t, i));

      sc.nextLine();
    }

    Car[] cars = new Car[vehicles];
    for (int i = 0; i < vehicles; i++) {
      cars[i] = new Car(i);
    }

    // START
    run(cars, rides);
	}

  public static void run (Car[] cars, ArrayList<Ride> rides) {
    ArrayList<ArrayList<Integer>> solution = new ArrayList<ArrayList<Integer>>(cars.length);
    for(int i = 0; i < cars.length; i++)  {
      solution.add(new ArrayList<Integer>());
    }
    int availableRides = rides.size();
    // rides.sort(new RideComparator());
    Collections.sort(rides, new Comparator<Ride>() {
      public int compare(Ride o1, Ride o2) {
        Integer x1 = (Integer)o1.timeStart;
        Integer x2 = (Integer)o2.timeStart;
        int sComp = x1.compareTo(x2);

        if (sComp != 0) {
          return sComp;
        } else {
          Integer y1 = (Integer)o1.timeFinish;
          Integer y2 = (Integer)o2.timeFinish;
          return y1.compareTo(y2);
        }
      }
    });

    while (time < steps && availableRides != 0) {
      for (int i = 0; i < cars.length; i++) {
        if (cars[i].nextTimeAvailable == time) {
          cars[i].available = true;
        }
        if (cars[i].available) {
          int nextRide = closestRide(cars[i], rides);
          if (nextRide > -1) {
            solution.get(i).add(nextRide);
            availableRides--;
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

  public static int closestRide (Car car, ArrayList<Ride> rides) {
    Ride closest = null;
    for (Ride r : rides) {
      if (r.timeStart < time) r.available = false;
      if (r.available) {
        closest = r;
        break;
      }
    }
    if (closest != null) {
      for(int i = 1; i < rides.size(); i++){
        if (rides.get(i).available && (getDistance(car.position, rides.get(i).start) < getDistance(car.position, closest.start))) {
          closest = rides.get(i);
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

  public static int nextLongestRide (Car car, ArrayList<Ride> rides) {
    for (int i = 0; i < rides.size(); i++) {
      if (rides.get(i).available && canFinishRide(car, rides.get(i))) {
        car.available = false;
        car.nextTimeAvailable = rides.get(i).timeStart + rides.get(i).distance;
        car.position = rides.get(i).finish;
        rides.get(i).available = false;
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

  public static int nextRide (Car car, ArrayList<Ride> rides) {
  	for (int i = 0; i < rides.size(); i++) {
      System.out.println(getOnTime(car.position, rides.get(i).start, rides.get(i).timeStart));
  		if (rides.get(i).available && getOnTime(car.position, rides.get(i).start, rides.get(i).timeStart)) {
        car.available = false;
        car.nextTimeAvailable = rides.get(i).timeStart + rides.get(i).distance;
        car.position = rides.get(i).finish;
        rides.get(i).available = false;
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
