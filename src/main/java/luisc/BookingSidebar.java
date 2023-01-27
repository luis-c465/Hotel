package luisc;

import lib.Obj;

/**
 * Displays a sidebar for the currently selected room
 * Allows the user to set a booking start and end date
 * If the room is already book it should say so
 */
public class BookingSidebar extends Obj {

    private static final int shape_fill_c = 0xfff87171;
    private static final int max_txt_h = 100;
    private static final int max_txt_w = 280;
    private static final int icon_size = 80;
    public static final int padding = 20;
    public static final int x = 700;
    public int xs = x + padding;
    public int ys = padding;
    public static final int bg_r = 25;
    private static final int bg_c = 0xff334155;

    private int text_start = padding + 100 * 1;
    private int text_start_2 = padding + 100 * 2;
    // private int text_start_3 = padding + 100 * 3;
    public Room room;

    @Override
    protected void _update() {
        showBackground();

        if (room == null) {
            showNothingSelected();
        } else if (room.dirty || room.bookingEnds != null) {
            showAlreadyBooked();
        } else {
            showStartBooking();
        }
    }

    @Override
    protected void _setup() {
        // guh
    }

    protected void showBackground() {
        push();

        p.fill(bg_c);
        p.rectMode(c.CORNERS);
        p.rect(x, App.w, 0, App.h);

        pop();
    }

    protected void showStartBooking() {
        p.text("todo some code here, placeholder!", xs, ys);
    }

    protected void showAlreadyBooked() {
        p.shapeMode(c.CORNER);
        p.shape(a.ban, xs + icon_size, ys, icon_size, icon_size);

        p.fill(255);
        p.textSize(30);
        p.textAlign(c.CORNER);
        p.text(
            "This room is already booked or dirty!",
            xs,
            text_start,
            max_txt_w,
            max_txt_h
        );

        p.textSize(20);
        p.text(
            "Please select a different room to book",
            xs,
            text_start_2,
            max_txt_w,
            max_txt_h
        );
    }

    protected void showNothingSelected() {
        p.shapeMode(c.CORNER);
        p.shape(a.x, xs + icon_size, ys, icon_size, icon_size);

        p.fill(255);
        p.textSize(30);
        p.textAlign(c.CORNER);
        p.text("No room selected", xs, text_start, max_txt_w, max_txt_h);

        p.textSize(20);
        p.text(
            "Click on a hotel on the left to begin booking it",
            xs,
            text_start_2,
            max_txt_w,
            max_txt_h
        );
    }

    public BookingSidebar(App app) {
        super(app);
    }
}
