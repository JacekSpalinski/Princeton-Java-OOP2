
public class ColorHSB {

    private final int ch, cs, cb; // hue, saturation and brightness

    // Creates a color with hue h, saturation s, and brightness b.
    public ColorHSB(int h, int s, int b) {

        // Check for exceptions
        if (h < 0 || h > 360) {
            throw new IllegalArgumentException("the hue must be between 0 and 360");
        }
        if (s < 0 || s > 100) {
            throw new IllegalArgumentException("the saturation must be between 0 and 100");
        }
        if (b < 0 || b > 100) {
            throw new IllegalArgumentException("the saturation must be between 0 and 100");
        }

        ch = h;
        cs = s;
        cb = b;
    }

    // Returns a string representation of this color, using the format (h, s, b).
    public String toString() {
        return "(" + ch + ", " + cs + ", " + cb + ")";
    }

    // Is this color a shade of gray?
    public boolean isGrayscale() {
        if (cs == 0 || cb == 0) return true;
        return false;
    }

    // Returns the squared distance between the two colors.
    public int distanceSquaredTo(ColorHSB that) {
        if (that == null) {
            throw new IllegalArgumentException("Argument can't be null");
        }
        int dist;
        dist = Math.min((this.ch - that.ch) * (this.ch - that.ch),
                        (360 - Math.abs(this.ch - that.ch)) * (360 - Math.abs(this.ch - that.ch))) +
                (this.cs - that.cs) * (this.cs - that.cs) +
                (this.cb - that.cb) * (this.cb - that.cb);
        return dist;
    }

    public static void main(String[] args) {
        int h = Integer.parseInt(args[0]);
        int s = Integer.parseInt(args[1]);
        int b = Integer.parseInt(args[2]);

        // Final values for the closest color from the file
        // that will be assigned to HSB color
        String fName = null;
        int fh = 0;
        int fs = 0;
        int fb = 0;

        // Values for each color in the file
        String cName;
        int hue;
        int sat;
        int bright;

        int minDist = Integer.MAX_VALUE;
        int curDist;

        while (!StdIn.isEmpty()) {
            cName = StdIn.readString();
            hue = StdIn.readInt();
            sat = StdIn.readInt();
            bright = StdIn.readInt();
            curDist = Math.min((h - hue) * (h - hue),
                               (360 - Math.abs(h - hue)) * (360 - Math.abs(h - hue)))
                    + (s - sat) * (s - sat)
                    + (b - bright) * (b - bright);
            // if current distance between colors is smaller than min distance,
            // assign values for this color to our final values
            if (curDist < minDist) {
                minDist = curDist;
                fName = cName;
                fh = hue;
                fs = sat;
                fb = bright;
            }
        }

        ColorHSB c1 = new ColorHSB(fh, fs, fb);
        StdOut.println(fName + " " + c1);
    }
}
