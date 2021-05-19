public class Planet {

    static final double g = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                    double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double r2 = dx * dx + dy * dy;
        return Math.sqrt(r2);
    }

    public double calcForceExertedBy(Planet p) {
        double r2 = this.calcDistance(p) * this.calcDistance(p);
        return (Planet.g * this.mass * p.mass) / r2;
    }

    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    }

    // 'this.' is not needed, just makes things more clear
    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double total = 0;
        for (Planet p : planets) {
            if (p.equals(this)) {
                continue;
            }
            total += calcForceExertedByX(p);
        }
        return total;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double total = 0;
        for (Planet p : planets) {
            if (p.equals(this)) {
                continue;
            }
            total += calcForceExertedByY(p);
        }
        return total;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel = xxVel + (dt * aX);
        yyVel = yyVel + (dt * aY);
        xxPos = xxPos + (dt * xxVel);
        yyPos = yyPos + (dt * yyVel);
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
