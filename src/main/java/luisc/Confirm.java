package luisc;

import lib.Btn;
import lib.Obj;

/**
 * Asks the user to pay for the hotel room
 */
public class Confirm extends Obj {

    ContinueBtn contBtn;
    Room r;

    @Override
    protected void _setup() {
        contBtn = new ContinueBtn(m);
        contBtn.setup();
    }

    @Override
    protected void _update() {
        if (r == null) {
            return;
        }

        p.background(m.bg);

        contBtn.update();
        if (contBtn.clicked) {
            r = null;
            m.bSidebar.calc();
            return;
        }

        p.textSize(32);
        p.text("Booked a room for " + r.bookings.get(0).by, App.cw, App.ch);

        p.text("Room number is " + r.floor + r.number, App.cw, App.ch + 100);

        Booking b = r.bookings.get(0);
        int daysBetween = b.start.until(b.end).getDays();
        double price = r.price * daysBetween;

        p.fill(a.success);
        p.text(
            "Amount to pay is $" + String.format("%.2f", price),
            App.cw,
            App.ch + 150
        );
    }

    private class ContinueBtn extends Btn {

        @Override
        public void _setup() {
            x = App.cw;
            y = 800;
            w = 200;

            txt = "Continue";
            c = 0xff0ea5e9;
            txt_size = 20;
            txt_c = 255;
            txt_space = 30;

            icon = p.loadShape("play.svg");
            icon_space = 60;
            icon_y_mod = 5;
        }

        public ContinueBtn(App app) {
            super(app);
        }
    }

    public Confirm(App a) {
        super(a);
    }
}
