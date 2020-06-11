import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Forex {
    public static void main(String[] args) throws IOException {
        HashMap<String,Object> params = new HashMap<>();
        params.put("base","USD");
        List<String> currencies = Arrays.asList("CNY","EUR","HKD","GBP","AUD","JPY");
        params.put("symbols", currencies.stream().map(i -> i.toString())
            .collect(Collectors.joining(",")));
        String response = Rest.get("https://api.exchangeratesapi.io/latest", params);
        System.out.println(response);
    }
}