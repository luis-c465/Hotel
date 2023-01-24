package luisc;

import lib.Clickable;

/**
 * Displays a room to the user
 *
 * Shows floor and room number
 * Shows price, if the room is dirty or booked and when the booking ends
 */
public class RoomViewer extends Clickable {

    public Room r;
    // The index of the room viewer in the containing array
    public int i = -1;

    public static final int margin = 20;
    public static final int padding = 20;
    public static final int gap = 30;
    public static final int space = 50;

    public int leftSide = -1;
    public int topSide = -1;

    // Calculated variables
    public String rString = null;

    // Drawing constants
    public static final int roomNumberWidth = 200;

    public static final int cost_c = 0xff4ade80;

    @Override
    protected void preUpdate() {
        checkIfVisible();
        super.preUpdate();
    }

    @Override
    protected void _update() {
        p.textAlign(c.LEFT, c.CENTER);
        showRoomNumber();

        p.textSize(16);
        showPrice();
        showInfo();
    }

    private void showRoomNumber() {
        // Draws the line dividing the room number and other info
        p.strokeWeight(5);
        p.line(x, y, x, y + h);

        p.textSize(32);
        p.text(rString, leftSide, topSide);
    }

    private void showPrice() {
        p.text("" + String.format("%.2f", r.price), leftSide + space, topSide);
    }

    private void showInfo() {
        p.text(
            r.dirty ? "Dirty" : "Booked",
            leftSide + space,
            topSide + padding
        );

        p.text(
            "Booked until " + m.dateFormat.format(r.bookingEnds),
            leftSide + space,
            topSide + padding * 2
        );
    }

    @Override
    protected void _setup() {
        canMove = true;

        x = margin;
        y = margin + i * (gap + h);
        w = App.w - margin * 2;
        h = 200;

        cornerToCenter();
        // checkIfVisible();
    }

    @Override
    protected void updateCorners() {
        super.updateCorners();

        leftSide = left + padding;
        topSide = top + padding;
    }

    public void calc() {
        rString = "" + r.floor + "" + r.number;
    }

    public RoomViewer(App app) {
        super(app);
    }

    public RoomViewer(App app, Room r, int i) {
        super(app);
        this.r = r;
        this.i = i;

        setup();
        calc();
    }

    /**
     * Checks if the object is visible on the screen
     * If not temporarily disables updates for drawing and collodion on the object
     */
    public void checkIfVisible() {
        // Would be clearer as an if statement but this runs 60 times a second
        // so performance is an issue
        shouldCheck = bottom > 0 || top < App.h;
        shouldUpdate = shouldCheck;
    }
}
