package lib;

import luisc.App;

/**
 * A customizable modal which shows information
 */
public abstract class Modal extends Clickable {

    protected int padding = 100;

    // the property should update is if the modal is visible

    @Override
    protected void preSetup() {
        w = App.w - padding * 2;
        h = App.h - padding * 2;
    }

    @Override
    protected void preUpdate() {
        super.preUpdate();

        if (shouldUpdate && p.mousePressed && !clicked) {
            shouldUpdate = false;
        }
    }

    public Modal(App a) {
        super(a);
    }
}
