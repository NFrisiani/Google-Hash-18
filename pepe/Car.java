public class Car {
  Intersection position = new Intersection(0, 0);
  boolean available = true;
  int nextTimeAvailable = 0;
  int index;

  public Car (int index) {
    this.index = index;
  }

  public void updateCarPosition (Ride r) {
  	this.position = r.finish;
  }

  public void updateCarState (Ride r) {
  	this.available = !this.available;
  }

  public void completeRide (Ride r, int time) {
  	// r.finish = time + r.distance;
  	// while (time < finish)
  	// {
  	// 	//do nothing
  	// }

  	updateCarPosition(r);
  	updateCarState(r);
  }

  public String toString() {
    return ("Car -> Available: " + available + ", nextTimeAvailable: " + nextTimeAvailable +
                        ", position: " + position);
  }
}
