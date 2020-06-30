import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.List;

public class POST {
    static HttpClient httpClient = HttpClient.newBuilder().build();

    public static void main(String[] args) throws
            IOException, URISyntaxException, InterruptedException {
        String url = "http://www.example.com/login";
        String body = "username=bob&password=123456";
        HttpRequest request = HttpRequest.newBuilder(new URI(url))
                // 设置Header:
                .header("Accept", "*/*")
                .header("Content-Type", "application/x-www-form-urlencoded")
                // 设置超时:
                .timeout(Duration.ofSeconds(5))
                // 设置版本:
                .version(Version.HTTP_2)
                // 使用POST并设置Body:
                .POST(BodyPublishers.ofString(body, StandardCharsets.UTF_8)).build();
        HttpResponse<String> response = httpClient.send(
            request, HttpResponse.BodyHandlers.ofString());
        String s = response.body();
        Map<String, List<String>> headers = response.headers().map();
        for (String header : headers.keySet()) {
            System.out.println(header + ": " + headers.get(header).get(0));
        }
        System.out.println(s);
    }
}