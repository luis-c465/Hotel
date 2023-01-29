package luisc;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.AbstractList;

@XStreamAlias("booking")
public class Booking {

    public LocalDate start;
    public LocalDate end;
    public String by;

    // @Override
    // public int compareTo(Booking o) {
    //     if (o.)
    // }

    // private static boolean hasOverlap(Interval t1, Interval t2) {
    //     return !t1.end.isBefore(t2.begin) && !t1.begin.isAfter(t2.end);
    // }

    public static boolean hasOverlap(Booking t1, Booking t2) {
        return !t1.end.isBefore(t2.start) && !t1.start.isAfter(t2.end);
    }

    public boolean noAlreadyBooked(AbstractList<Booking> bookings) {
        for (Booking b : bookings) {
            if (hasOverlap(b, this)) {
                return false;
            }
        }

        return true;
    }

    public String toString(DateTimeFormatter f) {
        return f.format(start) + " - " + f.format(end);
    }
}
