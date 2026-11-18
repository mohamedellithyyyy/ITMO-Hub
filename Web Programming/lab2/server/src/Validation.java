import java.util.regex.Pattern;

public final class Validation {

    private Validation() {}

    /** Допустимые R из index.html: data-r="1|2|3|4". */
    private static final double[] R_ALLOWED = {1, 2, 3, 4};

    private static final Pattern NUMBER = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static final class Parsed {
        public final double x, y, r;
        public final String xString, yString, rString;
        Parsed(double x, double y, double r, String xs, String ys, String rs) {
            this.x = x; this.y = y; this.r = r;
            this.xString = xs; this.yString = ys; this.rString = rs;
        }
    }

    public static final class ValidationException extends RuntimeException {
        public ValidationException(String msg) { super(msg); }
    }

    public static Parsed validateForm(String xRaw, String yRaw, String rRaw) {
        double x = parseNumber(xRaw, "X", -4, 4);
        double y = parseNumber(yRaw, "Y", -3, 3);
        double r = parseR(rRaw);
        return new Parsed(x, y, r, xRaw, yRaw, rRaw);
    }

    private static double parseNumber(String raw, String name, double min, double max) {
        if (raw == null || raw.isEmpty() || !NUMBER.matcher(raw).matches()) {
            throw new ValidationException("Некорректное значение " + name + ".");
        }
        double v = Double.parseDouble(raw);
        if (v < min || v > max) {
            throw new ValidationException(
                name + " должен быть в диапазоне от " + fmt(min) + " до " + fmt(max) + ".");
        }
        return v;
    }

    private static double parseR(String raw) {
        if (raw == null || raw.isEmpty() || !NUMBER.matcher(raw).matches()) {
            throw new ValidationException("Выберите значение R.");
        }
        double v = Double.parseDouble(raw);
        for (double allowed : R_ALLOWED) {
            if (Double.compare(allowed, v) == 0) return v;
        }
        throw new ValidationException("Недопустимое значение R.");
    }

    private static String fmt(double d) {
        return d == (long) d ? String.valueOf((long) d) : String.valueOf(d);
    }
}