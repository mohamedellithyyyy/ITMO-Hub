import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public final class Main {

    private static final DateTimeFormatter FMT =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final Area AREA = new VariantArea();
    private static final History HISTORY = new History();

    public static void main(String[] args) throws IOException {
        FCGIInterface fcgi = new FCGIInterface();
        while (fcgi.FCGIaccept() >= 0) {
            long t0 = System.currentTimeMillis();
            String body = handle(System.getenv("QUERY_STRING"), t0);
            respond(body);
        }
    }

    private static String handle(String query, long t0) {
        String now = LocalDateTime.now().format(FMT);
        Map<String, String> p = parseQuery(query);

        try {
            Validation.Parsed v = Validation.validateForm(
                p.get("x"), p.get("y"), p.get("r"));

            boolean hit = AREA.contains(v.x, v.y, v.r);
            long elapsed = System.currentTimeMillis() - t0;

            HISTORY.add(new History.Entry(v.x, v.y, v.r, hit, now, elapsed));
            return Json.ok(v.x, v.y, v.r, hit, now, elapsed, HISTORY.snapshot());

        } catch (Validation.ValidationException e) {
            long elapsed = System.currentTimeMillis() - t0;
            return Json.error(e.getMessage(), now, elapsed, HISTORY.snapshot());
        }
    }

    private static void respond(String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        String headers =
            "Content-Type: application/json; charset=utf-8\r\n" +
            "Content-Length: " + bytes.length + "\r\n" +
            "\r\n";
        System.out.write(headers.getBytes(StandardCharsets.UTF_8));
        System.out.write(bytes);
        System.out.flush();
    }

    private static Map<String, String> parseQuery(String qs) {
        Map<String, String> m = new HashMap<>();
        if (qs == null || qs.isEmpty()) return m;
        for (String pair : qs.split("&")) {
            int i = pair.indexOf('=');
            String k = i < 0 ? pair : pair.substring(0, i);
            String v = i < 0 ? ""    : pair.substring(i + 1);
            m.put(decode(k), decode(v));
        }
        return m;
    }

    private static String decode(String s) {
        try { return java.net.URLDecoder.decode(s, StandardCharsets.UTF_8.name()); }
        catch (Exception e) { return s; }
    }
}