package luisc;

import lib.Transitionable;

/**
 * Asks the user to pay for the hotel room
 */
public class Confirm extends Transitionable {

    Room r;

    @Override
    protected void _setup() {
        // TODO Auto-generated method stub
        super._setup();
    }

    @Override
    protected void _update() {
        r = m.bSidebar.room;

        p.textSize(32);
        p.text("Booked a room for " + r.bookings.get(0).by, App.cw, App.ch);

        p.text("Room number is " + r.floor + r.number, App.cw, App.ch + 100);

        p.fill(a.success);
        p.text("Amount to pay is $" + r.formatPrice(), App.cw, App.ch + 300);
    }

    public Confirm(App a) {
        super(a);
    }
}
