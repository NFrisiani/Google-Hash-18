public class Ride {
  Intersection start, finish;
  int timeStart, timeFinish, distance;
  boolean available = true;
  int index;

  public Ride (Intersection start, Intersection finish, int timeStart, int timeFinish, int index) {
    this.start = start;
    this.finish = finish;
    this.timeStart = timeStart;
    this.timeFinish = timeFinish;
    this.distance = getDistance(start, finish);
    this.index = index;
  }

  public void setAvailability (boolean available) {
    this.available = available;
  }

  public int getDistance (Intersection s, Intersection f) {
    return Math.abs(((Math.abs(s.x) - Math.abs(f.x)) + (Math.abs(s.y) - Math.abs(f.y))));
  }
}
