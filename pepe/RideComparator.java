import java.util.Comparator;
public class RideComparator implements Comparator<Ride> {
    @Override
    public int compare(Ride o1, Ride o2) {
      Integer a = new Integer(o1.timeStart);
      Integer b = new Integer(o2.timeStart);
      return a.compareTo(b);
    }
}
