package luisc;

import java.util.LinkedList;
import lib.Obj;

/**
 * Draws list of rooms to the screen which are intractable
 */
public class RoomsViewer extends Obj {

    public LinkedList<RoomViewer> rooms = new LinkedList<RoomViewer>();

    @Override
    protected void _update() {
        for (RoomViewer rv : rooms) {
            rv.update();
        }
    }

    @Override
    protected void _setup() {
        // TODO: Add support for filtering the array of rooms
        for (int i = 0; i < m.rooms.size(); i++) {
            rooms.add(new RoomViewer(m, m.rooms.get(i), i));
        }
    }

    public RoomsViewer(App a) {
        super(a);
    }

    public void mouseWheel(int c) {
        if (c == 0) {
            return;
        }

        // idk math moment
        if (c > 0) { // Scrolling down
            RoomViewer bottom = rooms.getLast();
            if (bottom.top + 200 > App.h) {
                incrementAll(-p.constrain(c, 0, bottom.bottom));
            }
        } else if (c < 0) { // Scrolling up
            RoomViewer top = rooms.getFirst();
            if (top.top - 100 < 0) {
                incrementAll(-p.constrain(c, top.top - 100, 0));
            }
        }
    }

    /**
     * Increments the y value of all the room viewers by y
     */
    public void incrementAll(int y) {
        for (RoomViewer rv : rooms) {
            rv.y += y;
        }
    }
}
