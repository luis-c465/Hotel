package luisc;

import controlP5.ControlP5;
import controlP5.Textfield;
import java.time.LocalDate;
import java.util.Date;
import lib.Btn;
import lib.Obj;

/**
 * Displays a sidebar for the currently selected room
 * Allows the user to set a booking start and end date
 * If the room is already book it should say so
 */
public class BookingSidebar extends Obj {

    private Textfield startDate;
    private Textfield endDate;
    private Textfield bookerName;
    private BookBtn bookBtn;

    // Field constants
    private static final int safe = 20;
    private static final int f_x = 700;
    private static final int f_start_y = 100;
    private static final int f_end_y = 200;
    private static final int f_name_y = 350;

    private static final int f_w = 250;
    private static final int f_h = 50;
    private static final int f_gap = 50;
    private static final int f_bg_c = 0xff374151;
    private static final int f_txt_c = 0xffffffff;
    private static final int f_label_c = 0xff64748b;

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

        // Update the fields
        startDate.hide();
        endDate.hide();
        bookerName.hide();

        if (room == null) {
            showNothingSelected();
        } else if (room.dirty || room.currBooked) {
            showAlreadyBooked();
        } else {
            showStartBooking();
        }
    }

    @Override
    protected void _setup() {
        addFields();

        bookBtn = new BookBtn(m);
        bookBtn.setup();
    }

    protected void showBackground() {
        push();

        p.fill(bg_c);
        p.rectMode(c.CORNERS);
        p.rect(x, App.w, 0, App.h);

        pop();
    }

    protected void showStartBooking() {
        startDate.show();
        endDate.show();
        bookerName.show();

        bookBtn.update();

        if (bookBtn.clicked) {
            p.println("Booking room: " + room);
        }

        p.textSize(30);
        p.text(
            "Room: " + room.floor + room.number,
            xs,
            padding,
            max_txt_w,
            max_txt_h
        );
    }

    protected void showAlreadyBooked() {
        p.shapeMode(c.CORNER);
        p.shape(a.ban, xs + icon_size, ys, icon_size, icon_size);

        p.fill(255);
        p.textSize(30);
        p.textAlign(c.CORNER);
        p.text(
            "This room cannot be booked!",
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

    protected void addFields() {
        startDate =
            m.cp5
                .addTextfield("startDate")
                .setPosition(f_x, f_start_y)
                .setSize(f_w, f_h)
                .setFont(a.nunito_small)
                .setFocus(true)
                .setColor(f_txt_c)
                .setColorForeground(f_txt_c)
                .setColorBackground(f_bg_c)
                .setCaptionLabel("Booking Start Date")
                .setLabelVisible(true)
                .setColorCaptionLabel(f_label_c)
                .setValue(m.dateFormat.format(m.today))
                .hide();
        startDate
            .getCaptionLabel()
            .align(ControlP5.LEFT, ControlP5.BOTTOM_OUTSIDE)
            .setPaddingX(0)
            .setSize(15)
            .setFont(a.nunito_small);

        Date end = new Date();
        end.setTime(end.getTime() + 2 * 24 * 60 * 60 * 1000);
        endDate =
            m.cp5
                .addTextfield("endDate")
                .setPosition(f_x, f_end_y)
                .setSize(f_w, f_h)
                .setFont(a.nunito_small)
                .setColor(f_txt_c)
                .setColorForeground(f_txt_c)
                .setColorBackground(f_bg_c)
                .setCaptionLabel("Booking End Date")
                .setLabelVisible(true)
                .setColorCaptionLabel(f_label_c)
                .setValue(m.dateFormat.format(LocalDate.now().plusDays(1)))
                .hide();

        endDate
            .getCaptionLabel()
            .align(ControlP5.LEFT, ControlP5.BOTTOM_OUTSIDE)
            .setPaddingX(0)
            .setSize(15)
            .setFont(a.nunito_small);

        bookerName =
            m.cp5
                .addTextfield("bookerName")
                .setPosition(f_x, f_name_y)
                .setSize(f_w, f_h)
                .setFont(a.nunito_small)
                .setColor(f_txt_c)
                .setColorForeground(f_txt_c)
                .setColorBackground(f_bg_c)
                .setCaptionLabel("Booker Name")
                .setLabelVisible(true)
                .setColorCaptionLabel(f_label_c)
                .setValue("100")
                .hide();

        bookerName
            .getCaptionLabel()
            .align(ControlP5.LEFT, ControlP5.BOTTOM_OUTSIDE)
            .setPaddingX(0)
            .setSize(15)
            .setFont(a.nunito_small);
    }

    protected void updateFields() {
        // TODO: Add some form of conditional for showing and hiding these fields!
        startDate.show();
        endDate.show();
    }

    public BookingSidebar(App app) {
        super(app);
    }

    private class BookBtn extends Btn {

        @Override
        protected void _setup() {
            icon = p.loadShape("receipt.svg");
            icon_space = 100;

            w = 300;
            x = 700;
            y = f_name_y + 150 + h;

            txt = "Book room & Pay";
            txt_space = 30;
            txt_size = 25;

            cornerToCenter();
        }

        public BookBtn(App a) {
            super(a);
        }
    }
}
