package luisc;

import controlP5.Textfield;
import processing.core.PApplet;

/**
 * Class which displays the startup screen
 * Shows 2 inputs for both players names
 */
public class StartUp extends Transitionable {

  private Textfield numRows;
  private Textfield numCols;
  private StartGameBtn startGameBtn;
  private static final String instructions = "Enter the number of rows and colmus for the game!";
  private static final int txt_y = 200;
  private String errors = "";

  // * DRAWING CONSTANTS
  private static final int num_w = 200;
  private static final int num_h = 50;
  private int num_x = App.cw - num_w / 2;
  private static final int num_gap = 50;
  private static final int num_bg_c = 0xff374151;
  private static final int num_txt_c = 0xffffffff;
  private static final int name_label_c = 0xff64748b;

  private static final int errors_c = 0xfff87171;

  // Constants different for each input
  private int num_rows_y = App.ch - num_h - num_gap;
  private int num_cols_y = App.ch - num_h + num_gap;
  private int errors_y = App.ch + 150;

  @Override
  protected void _setup() {
    numRows =
      m.cp5
        .addTextfield("numRows")
        .setPosition(num_x, num_rows_y)
        .setSize(num_w, num_h)
        .setFont(a.nunito_small)
        .setFocus(true)
        .setColor(num_txt_c)
        .setColorForeground(num_txt_c)
        .setColorBackground(num_bg_c)
        .setCaptionLabel("Num Rows")
        .setLabelVisible(true)
        .setColorCaptionLabel(name_label_c)
        .setValue("100")
        .hide();
    numCols =
      m.cp5
        .addTextfield("numCols")
        .setPosition(num_x, num_cols_y)
        .setSize(num_w, num_h)
        .setFont(a.nunito_small)
        .setColor(num_txt_c)
        .setColorForeground(num_txt_c)
        .setColorBackground(num_bg_c)
        .setCaptionLabel("Num Cols")
        .setLabelVisible(true)
        .setColorCaptionLabel(name_label_c)
        .setValue("100")
        .hide();

    startGameBtn = new StartGameBtn(m);
    startGameBtn.setup();
  }

  @Override
  protected void _update() {
    if (done) {
      numRows.hide();
      numCols.hide();
      paused = true;
      return;
    }

    // Else show the text boxes
    numRows.show();
    numCols.show();
    startGameBtn.update();

    // Show instructions
    p.textSize(30);
    p.text(instructions, App.cw, txt_y);

    // Show errors text
    p.textFont(a.nunito);
    p.textSize(20);
    p.textAlign(PApplet.CENTER);
    p.fill(errors_c);
    p.text(errors, App.cw, errors_y);

    checkBtns();
  }

  // Check is the button was clicked
  private void checkBtns() {
    if (startGameBtn.clicked) {
      try {
        m.numRows = Integer.parseInt(numRows.getText());
        m.numCols = Integer.parseInt(numCols.getText());
      } catch (NumberFormatException e) {
        errors = "The inputs should be a number!";
        return;
      }

      if (m.numRows > 1000 || m.numCols > 1000) {
        errors = "The number or rows or cols cannot be > 1000!";
        return;
      }

      errors = "";

      numRows.remove();
      numCols.remove();

      // Begin transitioning!
      trans = true;

      m.conways.calc();
    }
  }

  @Override
  protected void onTransOutBegin() {
    onDone();
  }

  private class StartGameBtn extends Btn {

    @Override
    public void _setup() {
      x = App.cw;
      y = 800;
      w = 200;

      txt = "Start Game";
      c = 0xff0ea5e9;
      txt_size = 20;
      txt_c = 255;
      txt_space = 30;

      icon = p.loadShape("play.svg");
      icon_space = 60;
      icon_y_mod = 5;
    }

    public StartGameBtn(App app) {
      super(app);
    }
  }

  public StartUp(App app) {
    super(app);
  }
}
