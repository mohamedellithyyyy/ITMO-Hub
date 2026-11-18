public final class VariantArea implements Area {

    @Override
    public boolean contains(double x, double y, double r) {
        double h = r / 2.0;

        // I: quarter circle, radius R/2
        if (x >= 0 && y >= 0) {
            return x * x + y * y <= h * h;
        }

        // IV: rectangle [0, R/2] × [-R, 0]
        if (x >= 0 && y <= 0) {
            return x <= h && y >= -r;
        }

        // III: triangle (-R,0),(0,0),(0,-R/2); on/above y = -(x+R)/2
        if (x <= 0 && y <= 0) {
            return x >= -r && 2.0 * y >= -(x + r);
        }

        // II: outside
        return false;
    }
}