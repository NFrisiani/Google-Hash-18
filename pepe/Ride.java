public class Ride {
  Intersection start, end;
  int timeStart, timeFinish;

  public Ride (Intersection start, Intersection end, int timeStart, int timeFinish) {
    this.start = start;
    this.end = end;
    this.timeStart = timeStart;
    this.timeFinish = timeFinish;
  }
}
