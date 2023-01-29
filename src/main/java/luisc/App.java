package luisc;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.security.AnyTypePermission;
import controlP5.ControlP5;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import lib.TransitionIn;
import lib.TransitionOut;
import processing.core.PApplet;
import processing.event.MouseEvent;

/**
 * Hello world!
 */
public final class App extends PApplet {

    public XStream x = new XStream();
    public PersistenceStrategy strategy;
    public XmlList<Room> rooms;

    public DateTimeFormatter dateFormat = Room.getDateFormatter();
    public DateTimeFormatter dateInputFormat = BookingSidebar.getDateInputFormatter();
    public LocalDate today = LocalDate.now();

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
    public Intro intro = new Intro(this);
    public RoomsViewer rViewer = new RoomsViewer(this);
    public BookingSidebar bSidebar = new BookingSidebar(this);
    public Confirm confirm = new Confirm(this);

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

        String folder = System.getProperty("user.home");
        folder += File.separator + ".luisc.hotel";
        File customDir = new File(folder);

        if (customDir.exists()) {
            System.out.println(customDir + " already exists");
        } else if (customDir.mkdirs()) {
            System.out.println(customDir + " was created");
        } else {
            System.out.println(customDir + " was not created");
        }

        // Setup DB
        x.addPermission(AnyTypePermission.ANY);
        strategy = new FilePersistenceStrategy(new File(folder), x);

        rooms = new XmlList<Room>(strategy);
        updateRooms();

        println(rooms.toString());

        // addTestHotel();

        // Setup variables and assets
        a.setup(this);
        cp5 = new ControlP5(this);

        // SETUP CLASSES
        header.setup();
        intro.setup();
        startUp.setup();

        rViewer.setup();
        bSidebar.setup();
        confirm.setup();
    }

    /**
     * Updates the currBooked property on all the rooms
     */
    private void updateRooms() {
        if (rooms.size() == 0) {
            addRooms();
            return;
        }

        LocalDate today = LocalDate.now();
        for (int i = 0; i < rooms.size(); i++) {
            Room r = rooms.get(i);
            // If the room has more than one booking
            // and the start of the first booking comes before today
            // then say it is currently booked
            if (
                r.bookings.size() > 0 &&
                r.bookings.get(0).start.compareTo(today) > 0
            ) {
                r.currBooked = true;
            } else {
                r.currBooked = false;
            }

            rooms.set(i, r);
        }
    }

    @Override
    public void draw() {
        background(bg);
        fill(255);

        rViewer.update();
        bSidebar.update();

        confirm.update();
        // intro.update();
        // if (doingIntro) {
        //     return;
        // }

        // startUp.update();
        // // If the startup is not done do not continue on to the rest of the program
        // if (!startUp.done) {
        //     return;
        // }

        // header.update();
        // conways.update();
    }

    /**
     * Should only be called if there is no rooms in the room list
     */
    private void addRooms() {
        for (int i = 0; i < 10; i++) {
            Room r = new Room();
            r.floor = (int) random(1, 10);
            r.number = i;
            r.price = (int) random(300, 1000);

            r.bookings = new ArrayList<Booking>();

            rooms.add(r);
        }
    }

    private void addTestHotel() {
        Room h = new Room();
        h.floor = 9;
        h.number = 1;
        h.price = 500;
        h.dirty = true;

        Booking b = new Booking();
        b.start = LocalDate.now();
        b.end = LocalDate.now();
        b.by = "Joe fungus";

        h.bookings = new ArrayList<Booking>();
        h.bookings.add(b);

        rooms.add(h);
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
    public void mouseWheel(MouseEvent e) {
        int c = e.getCount();
        rViewer.mouseWheel(c);
    }
}
