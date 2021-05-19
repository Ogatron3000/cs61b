import java.util.ArrayList;

public class NBody {
    public static double readRadius(String file) {
        In in = new In(file);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String file) {
        In in = new In(file);
        int planetNum = in.readInt();
        in.readDouble();

        ArrayList<Planet> planetsList = new ArrayList<Planet>();
        for (int i = 0; i < planetNum; i++) {
            Planet newPlanet = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                                            in.readDouble(), in.readDouble(), in.readString());
            planetsList.add(newPlanet);
        }

        Planet[] planets = new Planet[planetsList.size()];
        return planetsList.toArray(planets);
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double universeRadius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time < T) {

            ArrayList<Double> xForcesList = new ArrayList<Double>();
            ArrayList<Double> yForcesList = new ArrayList<Double>();

            for (Planet p : planets) {
                xForcesList.add(p.calcNetForceExertedByX(planets));
                yForcesList.add(p.calcNetForceExertedByY(planets));
            }

            // convert array lists to arrays
            Double[] xForces = new Double[xForcesList.size()];
            xForcesList.toArray(xForces);
            Double[] yForces = new Double[yForcesList.size()];
            yForcesList.toArray(yForces);

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.setScale(-universeRadius, universeRadius); // here or before or no?
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", universeRadius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
