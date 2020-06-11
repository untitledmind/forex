import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class Rest {
    private static URL url;
    private static String readLine;
    private static HttpURLConnection conn;
    private static BufferedReader input;
    private static StringBuffer response;

    public static String get(String http, Map<String, Object> params) throws IOException {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            s.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        url = new URL(http + "?" + s.toString().substring(0, s.length() - 1));
        readLine = null;
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            input = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
            response = new StringBuffer();

            while ((readLine = input.readLine()) != null) {
                response.append(readLine);
            }
            input.close();

            return response.toString();
        } else {
            System.out.println("GET NOT WORKED");
            return "Get not worked";
        }
    }
}