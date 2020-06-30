import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Map;
import java.util.List;

public class GET {
    static HttpClient httpClient = HttpClient.newBuilder().build();

    public static void main(String[] args) throws
            IOException, URISyntaxException, InterruptedException {
        String url = "https://www.sina.com.cn";
        HttpRequest request = HttpRequest.newBuilder(new URI(url))
            .header("User-Agent", "Java HttpClient")
            .header("Accept", "*/*")
            .timeout(Duration.ofSeconds(5))
            .version(Version.HTTP_2)
            .build();

        HttpResponse<String> response = httpClient.send(
            request, HttpResponse.BodyHandlers.ofString());
        Map<String, List<String>> headers = response.headers().map();
        for (String header : headers.keySet()) {
            System.out.println(header + ": " + headers.get(header).get(0));
        }
        System.out.println(response.body().substring(0, 1024) + "...");
    }
}
