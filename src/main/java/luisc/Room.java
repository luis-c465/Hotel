package luisc;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Date;

/**
 * Data class for information about a class
 */
@XStreamAlias("room")
public class Room {

    public int floor = -1;
    public int number = -1;
    public double price = -1;
    public Date bookingStarts;
    public Date bookingEnds;
    public boolean dirty = false;
    public boolean booked = false;
    public String bookedBy;

    @Override
    public String toString() {
        return "" + floor + "" + number + "  $" + price;
    }
}
