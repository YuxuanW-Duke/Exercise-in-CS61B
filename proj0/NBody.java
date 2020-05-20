public class NBody {

    public static double readRadius(String imgFileName) {
        In in = new In("./data/planets.txt");
        in.readDouble();
        return in.readDouble();
    }


    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        Body[] bodies = new Body[N];
        in.readDouble();
        for (int i = 0; i < N; i++) {
            bodies[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(),
                        in.readDouble(), in.readDouble(), in.readString());
        }
        return bodies;
    }

    public static void main(String[] args) {
        //Read data from file.
//        double T = Double.parseDouble(args[0]);
//        double dt = Double.parseDouble(args[1]);
//        String filename = args[2];
        double T = 157788000.0;
        double dt = 25000.0;
        String filename = "data/planets.txt";
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        double time = 0;

        //Start drawing
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");

        while (time <= T) {

            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];

            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            for (Body b : bodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(5);
            time += dt;
        }
    }

}
