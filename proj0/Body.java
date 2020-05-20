public class Body {
    public static final double G = 6.67e-11;
    /** x and y location */
    public double xxPos;
    public double yyPos;
    /** x and y velocity */
    public double xxVel;
    public double yyVel;
    /** mass */
    public double mass;
    /** filename of body's configuration */
    public String imgFileName;

    /** Constructor of Body with Parameters */
    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** Constructor of Body with object */
    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /** Calculate the Distance to another Body */
    public double calcDistance(Body b) {
        double dx = Math.abs(xxPos - b.xxPos);
        double dy = Math.abs(yyPos - b.yyPos);
        return Math.sqrt(dx*dx + dy*dy);
    }

    /** Calculate the Force with another Body */
    public double calcForceExertedBy(Body b) {
        double distance = calcDistance(b);
        return G*mass*b.mass/(distance*distance);
    }

    /** Calculate the Force with another Body in X */
    public double calcForceExertedByX(Body b) {
        double dx = b.xxPos - xxPos;
        return calcForceExertedBy(b)*dx/calcDistance(b);
    }

    /** Calculate the Force with another Body in Y */
    public double calcForceExertedByY(Body b) {
        double dy = b.yyPos - yyPos;
        return calcForceExertedBy(b)*dy/calcDistance(b);
    }

    /** Calculate the NetForces in X */
    public double calcNetForceExertedByX(Body[] bodies) {
        double force = 0;
        for (Body b : bodies) {
            if (!this.equals(b)) {
                force = force + calcForceExertedByX(b);
            }
        }
        return force;
    }

    /** Calculate the NetForces in Y */
    public double calcNetForceExertedByY(Body[] bodies) {
        double force = 0;
        for (Body b : bodies) {
            if (!this.equals(b)) {
                force = force + calcForceExertedByY(b);
            }
        }
        return force;
    }

    /** Update the velocity and position */
    public void update(double dt, double fX, double fY) {
        double ax = fX/mass;
        double ay = fY/mass;
        xxVel += ax*dt;
        yyVel += ay*dt;
        xxPos += xxVel*dt;
        yyPos += yyVel*dt;
    }

    /** Draw the current planet on the canvas */
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
    }
}
