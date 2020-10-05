
public class Clock {

    // Variables to keep track of max values for hours and minutes
    private static final int HPD = 24;
    private static final int MPD = 60;

    private int hh, mm; // values for int constructor


    // Creates a clock whose initial time is h hours and m minutes.
    public Clock(int h, int m) {

        // Check for exceptions
        if (h < 0 || h >= HPD) {
            throw new IllegalArgumentException("hours must be between 0 and 23");
        }
        if (m < 0 || m >= MPD) {
            throw new IllegalArgumentException("minutes must be between 0 and 59");
        }

        hh = h;
        mm = m;
    }

    // Creates a clock whose initial time is specified as a string, using the format HH:MM.
    public Clock(String s) {

        // Check for exceptions
        if (s.length() != 5) {
            throw new IllegalArgumentException("time length must be 5");
        }
        char char1 = s.charAt(0);
        char char2 = s.charAt(1);
        char char3 = s.charAt(2);
        char char4 = s.charAt(3);
        char char5 = s.charAt(4);
        if (!Character.isDigit(char1) || !Character.isDigit(char2) || char3 != ':'
                || !Character.isDigit(char4) || !Character.isDigit(char5)) {
            throw new IllegalArgumentException("wrong input format");
        }

        int h = Integer.parseInt(s.substring(0, 2));
        int m = Integer.parseInt(s.substring(3, 5));

        if (h < 0 || h >= HPD) {
            throw new IllegalArgumentException("hours must be between 0 and 23");
        }
        if (m < 0 || m >= MPD) {
            throw new IllegalArgumentException("minutes must be between 0 and 59");
        }

        hh = h;
        mm = m;
    }

    // Returns a string representation of this clock, using the format HH:MM.
    public String toString() {
        if (hh < 10 && mm < 10) return "0" + hh + ":0" + mm;
        if (hh < 10) return "0" + hh + ":" + mm;
        if (mm < 10) return hh + ":0" + mm;
        else return hh + ":" + mm;
    }

    // Is the time on this clock earlier than the time on that one?
    public boolean isEarlierThan(Clock that) {

        // Compare times, starting with hours
        if (this.hh < that.hh || (this.hh == that.hh && this.mm < that.mm)) return true;
        return false;
    }

    // Adds 1 minute to the time on this clock.
    public void tic() {

        mm++;
        if (mm == MPD) {
            mm = 0;
            hh++;
            if (hh >= HPD) hh -= HPD;
        }
    }

    // Adds Δ minutes to the time on this clock.
    public void toc(int delta) {

        // Check for exceptions
        if (delta < 0) {
            throw new IllegalArgumentException("delta can't be negative");
        }

        // Calculate how many minutes and hours to add by checking
        // how many 60-minutes intervals are in delta
        int mPlus = delta % MPD;
        int hPlus = (delta / MPD) % HPD;

        // Adding time with checking if we are not passing additional hours by adding minutes
        mm = mm + mPlus;
        if (mm >= MPD) {
            mm -= MPD;
            hPlus++;
        }
        hh = hh + hPlus;
        if (hh >= HPD) hh -= HPD;
    }


    public static void main(String[] args) {

        int h = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        String s = args[2];
        int delta = Integer.parseInt(args[3]);
        Clock time1 = new Clock(h, m);
        Clock time2 = new Clock(s);
        StdOut.println(time1);
        StdOut.println(time2);
        StdOut.println(time1.isEarlierThan(time2));
        time1.tic();
        time1.toc(delta);
    }
}
