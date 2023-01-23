package luisc;

import controlP5.ControlP5;
import processing.core.PApplet;

/**
 * Hello world!
 */
public final class App extends PApplet {

  // * CONSTANTS
  public static final int h = 1000;
  public static final int w = 1000;

  public static final int ch = 500;
  public static final int cw = 500;

  // * COLORS
  public static final int bg = 0xff1e293b;

  // * VARIABLES
  public boolean doingIntro = true;
  public boolean doingStartUp = true;
  public boolean transitioning = false;
  public int numCols = -1;
  public int numRows = -1;
  public boolean paused = false;

  public long numDead = 0;
  public long numTicks = 0;

  // Should be calculated at runtime

  // * Util classes
  public Assets a = new Assets();

  // * Library classes
  public ControlP5 cp5;

  // Game classes
  public Header header = new Header(this);
  public StartUp startUp = new StartUp(this);
  public Conways conways = new Conways(this);
  public Intro intro = new Intro(this);

  // Transition classes
  public TransitionIn transIn = new TransitionIn(this);
  public TransitionOut transOut = new TransitionOut(this);

  @Override
  public void settings() {
    size(1000, 1000);
  }

  @Override
  public void setup() {
    procSet();

    // Setup variables and assets
    a.setup(this);
    cp5 = new ControlP5(this);

    // SETUP CLASSES
    header.setup();
    intro.setup();
    startUp.setup();
    conways.setup();
  }

  @Override
  public void draw() {
    background(bg);
    fill(255);

    intro.update();
    if (doingIntro) {
      return;
    }

    startUp.update();
    // If the startup is not done do not continue on to the rest of the program
    if (!startUp.done) {
      return;
    }

    header.update();
    conways.update();
  }

  public void slider(float interval) {
    conways.interval = Math.round(interval);
  }

  public static final String[] appletArgs = { "--display=1", "luisc.App" };

  public static void main(String[] args) {
    runSketch(appletArgs, null);
  }

  /**
   * Sets the default settings for drawing with processing
   */
  public void procSet() {
    background(0);
    shapeMode(CENTER);
    textAlign(CENTER);
    imageMode(CENTER);
    noStroke();

    // Default fill color is white
    fill(255);
  }

  @Override
  public void keyPressed() {
    conways.KeyPressed();
  }
}
