package luisc;

import processing.core.PApplet;

/**
 * Abstract class for objects that will be drawn to the screen
 */
public abstract class Obj {

  protected Assets a;
  protected App m;
  protected PApplet p;
  protected boolean shouldUpdate = true;

  public Obj(App app) {
    this.a = app.a;
    this.m = app;
    this.p = app;
  }

  public final void setup() {
    // Do sone setup
    preSetup();

    _setup();

    postSetup();
  }

  /**
   * Should be used by super classes to setup variables
   */
  protected void preSetup() {}

  /**
   * Should be used by a super class to override variables after setup
   */
  protected void postSetup() {}

  /**
   * Should be overridden by a super class to setup stuff if needed
   */
  protected void _setup() {}

  /**
   * Draws and updates the object
   * Should not be overridden by a super class
   */
  public final void update() {
    preUpdate();

    if (shouldUpdate) {
      _update();
    }

    postUpdate();
  }

  protected abstract void _update();

  protected void preUpdate() {
    push();
  }

  protected void postUpdate() {
    pop();
  }

  protected final void push() {
    m.pushMatrix();
    m.pushStyle();
  }

  protected final void pop() {
    m.popMatrix();
    m.popStyle();
  }
}
