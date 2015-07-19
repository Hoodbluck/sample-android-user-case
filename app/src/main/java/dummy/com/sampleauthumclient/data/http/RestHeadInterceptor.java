package dummy.com.sampleauthumclient.data.http;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
public class RestHeadInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set("User-Agent", "com.hoodbluck.android");
        return execution.execute(request, body);
    }
}
