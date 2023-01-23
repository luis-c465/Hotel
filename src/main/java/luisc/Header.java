package luisc;

import controlP5.ControlP5;
import controlP5.Slider;
import processing.core.PShape;

/**
 * Contains buttons for switching between different
 */
public class Header extends Obj {

  public static final int safe = 10;
  public static final int gap = 350;

  public PausedButton pausedBtn;

  public Slider intervalSlider;

  public static final int icon_size = 30;

  public static final int dead_x = 450;
  public static final int dead_x_txt = 470;
  public static final int dead_y = 25;
  public static final int dead_y_txt = 30;

  public static final int ticks_x = 450;
  public static final int ticks_x_txt = 470;
  public static final int ticks_y = 60;
  public static final int ticks_y_txt = 65;

  @Override
  protected void _setup() {
    pausedBtn = new PausedButton(m);

    pausedBtn.setup();

    intervalSlider =
      m.cp5
        .addSlider("ticks per second")
        .setPosition(safe, safe)
        .setSize(400, 40)
        .setRange(1, 60)
        .setValue(5)
        .setNumberOfTickMarks(60)
        .snapToTickMarks(true)
        .showTickMarks(false)
        .setDecimalPrecision(0)
        .hide();

    // reposition the Label for controller 'slider'
    intervalSlider
      .getValueLabel()
      .align(ControlP5.LEFT, ControlP5.BOTTOM_OUTSIDE)
      .setPaddingX(0)
      .setSize(30)
      .setFont(a.nunito_small);
    intervalSlider
      .getCaptionLabel()
      .align(ControlP5.RIGHT, ControlP5.BOTTOM_OUTSIDE)
      .setPaddingX(0)
      .setSize(30)
      .setFont(a.nunito_small);
  }

  @Override
  protected void _update() {
    pausedBtn.update();

    if (!m.startUp.done) {
      intervalSlider.hide();
    } else {
      intervalSlider.show();
    }

    showDead();
    showTicks();

    m.conways.interval = 1_000 / Math.round(intervalSlider.getValue());
  }

  /**
   * Shows a running count of the number of dead cells
   */
  private void showDead() {
    preShow();
    p.shape(a.skull, dead_x, dead_y);
    p.text(String.valueOf(m.numDead), dead_x_txt, dead_y_txt);
  }

  /**
   * Shows a running count of the number of game ticks done
   */
  private void showTicks() {
    preShow();
    p.shape(a.tick, ticks_x, ticks_y);
    p.text(String.valueOf(m.numTicks), ticks_x_txt, ticks_y_txt);
  }

  private void preShow() {
    p.textSize(20);
    p.shapeMode(p.CENTER);
    p.textAlign(p.CORNER);
    p.fill(255);
  }

  // First button the header
  private class PausedButton extends Btn {

    private PShape pause;
    private PShape play;

    private String pau = "Pause";
    private String pla = "Play";

    @Override
    protected void _setup() {
      pause = p.loadShape("clock-pause.svg");
      play = p.loadShape("clock-play.svg");

      icon = pause;
      w = 200;
      x = App.w - w - safe;
      y = safe + h;

      txt = pau;

      cornerToCenter();
    }

    @Override
    protected void postUpdate() {
      super.postUpdate();

      if (m.paused) {
        icon = play;
        txt = pla;
      } else {
        icon = pause;
        txt = pau;
      }
    }

    @Override
    protected void _update() {
      super._update();

      if (clicked) {
        m.paused = !m.paused;
      }
    }

    public PausedButton(App a) {
      super(a);
    }
  }

  public Header(App app) {
    super(app);
  }
}
