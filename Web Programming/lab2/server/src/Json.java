import java.util.List;

public final class Json {

    private Json() {}

    public static String error(String message, String now, long scriptMs,
                               List<History.Entry> history) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"success\":false")
          .append(",\"error\":\"").append(escape(message)).append('"')
          .append(",\"serverTime\":\"").append(now).append('"')
          .append(",\"scriptMs\":").append(scriptMs)
          .append(",\"history\":");
        appendHistory(sb, history);
        sb.append('}');
        return sb.toString();
    }

    public static String ok(double x, double y, double r, boolean hit,
                            String now, long scriptMs,
                            List<History.Entry> history) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"success\":true")
          .append(",\"x\":").append(num(x))
          .append(",\"y\":").append(num(y))
          .append(",\"r\":").append(num(r))
          .append(",\"hit\":").append(hit)
          .append(",\"serverTime\":\"").append(now).append('"')
          .append(",\"scriptMs\":").append(scriptMs)
          .append(",\"history\":");
        appendHistory(sb, history);
        sb.append('}');
        return sb.toString();
    }

    private static void appendHistory(StringBuilder sb, List<History.Entry> list) {
        sb.append('[');
        for (int i = 0; i < list.size(); i++) {
            History.Entry e = list.get(i);
            if (i > 0) sb.append(',');
            sb.append("{\"x\":").append(num(e.x))
              .append(",\"y\":").append(num(e.y))
              .append(",\"r\":").append(num(e.r))
              .append(",\"hit\":").append(e.hit)
              .append(",\"time\":\"").append(e.time).append('"')
              .append(",\"scriptMs\":").append(e.scriptMs)
              .append('}');
        }
        sb.append(']');
    }

    private static String num(double d) {
        if (d == Math.rint(d)) return String.valueOf((long) d);
        return String.valueOf(d);
    }

    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}