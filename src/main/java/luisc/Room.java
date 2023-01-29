package luisc;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.Date;

/**
 * Data class for information about a class
 */
@XStreamAlias("room")
public class Room {

    public int floor = -1;
    public int number = -1;
    public double price = -1;
    public boolean dirty = false;
    public boolean currBooked = false;

    @XStreamImplicit
    public ArrayList<Booking> bookings;

    @Override
    public String toString() {
        return "" + floor + "" + number + " $" + formatPrice();
    }

    public String formatPrice() {
        return String.format("%.2f", price);
    }

    @XStreamAlias("booking")
    public static class Booking {

        public Date start;
        public Date end;
        public Date endDirty;
        public String by;
    }
}
