package luisc;

import lib.Clickable;

/**
 * Displays a room to the user
 *
 * Shows floor and room number
 * Shows price, if the room is dirty or booked and when the booking ends
 */
public class RoomViewer extends Clickable {

    public static final int top_margin = 100;

    public Room r;
    // The index of the room viewer in the containing array
    public int i = -1;

    public static final int canBeBooked_c = 0xff86efac;

    // Background color and radius when the room is currently selected
    public static final int bg_c = 0xff475569;
    public static final int bg_r = 20;

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
        // Reference check if they are the same
        if (m.bSidebar.room == r) {
            push();
            p.fill(bg_c);
            p.rectMode(c.CORNER);
            p.rect(left, top - margin - h, w, h, bg_r);
            pop();
        }

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
        p.text("" + r.formatPrice(), leftSide + space, topSide);
    }

    private void showInfo() {
        if (!r.currBooked && !r.dirty) {
            p.fill(canBeBooked_c);
        }

        p.text(
            r.dirty ? "Dirty" : r.currBooked ? "Booked" : "Not booked!",
            leftSide + space,
            topSide + padding
        );

        if (r.currBooked) {
            p.text(
                "Booked until " + m.dateFormat.format(r.bookings.get(0).end),
                leftSide + space,
                topSide + padding * 2
            );
        }
    }

    @Override
    protected void _setup() {
        canMove = true;

        w = App.w / 2 - margin * 2;
        h = 80;

        x = margin;
        y = margin + (i + 1) * (gap + h);

        cornerToCenter();
        checkIfVisible();
    }

    @Override
    protected void updateCorners() {
        super.updateCorners();

        leftSide = left + padding;
        topSide = top + padding - (h + gap - padding / 2);
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

    @Override
    protected void onClick() {
        m.bSidebar.room = r;
        p.println(r);
    }
}
