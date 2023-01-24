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
}
