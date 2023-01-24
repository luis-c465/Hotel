package lib;

import luisc.App;

/**
 * Class for objects which transition between frames in the app
 */
public abstract class Transitionable extends Obj {

    // Set to true to begin transitioning!
    public boolean trans = false;
    // Done transitioning
    public boolean middle = false;
    public boolean done = false;

    public boolean paused = false;

    protected void postSetup() {
        super.postSetup();

        m.transIn.reset();
        m.transOut.reset();
    }

    protected void preUpdate() {
        super.preUpdate();

        if (middle && trans) {
            transition();
        }
    }

    protected void postUpdate() {
        super.postUpdate();

        if (!middle && trans) {
            transition();
        }
    }

    private void transition() {
        if (paused) return;

        if (!m.transIn.done) {
            m.transIn.update();

            if (m.transIn.beforeDone) {
                middle = true;
                shouldUpdate = false;
                onTransOutBegin();
            }
        } else {
            if (!m.transOut.done) {
                m.transOut.update();
            }
        }

        if (m.transIn.done && m.transOut.done) {
            // When the trans is done
            onDone();
        }
    }

    protected void clean() {
        m.transIn.reset();
        m.transOut.reset();
    }

    protected void onDone() {
        trans = false;
        done = true;
        middle = false;

        clean();
    }

    // Method which inheriting classes should implement
    // for cleanup
    protected void onTransOutBegin() {}

    public Transitionable(App app) {
        super(app);
    }
}
