package dummy.com.sampleauthumclient.data.http;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientHeaders;
import org.androidannotations.api.rest.RestClientRootUrl;
import org.androidannotations.api.rest.RestClientSupport;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
@Rest(rootUrl = "http://authum.hoodbluck.com",
        converters = { StringHttpMessageConverter.class, GsonHttpMessageConverter.class},
        interceptors = RestHeadInterceptor.class)
public interface AuthumInterface extends RestClientRootUrl, RestClientSupport, RestClientHeaders {

        @Get("/helpme")
        public void getUser(String email);

}
