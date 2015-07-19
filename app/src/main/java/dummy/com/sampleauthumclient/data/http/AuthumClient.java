package dummy.com.sampleauthumclient.data.http;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import dummy.com.sampleauthumclient.models.AuthumResponse;

/**
 * Created on 7/19/15.
 *
 * @author Skye Schneider
 */
@EBean(scope = EBean.Scope.Singleton)
public class AuthumClient {

    @RestService
    AuthumInterface mAuthumInterface;

    public AuthumResponse authenticate(String appId, String email) {
        return mAuthumInterface.authenticate(appId, email);
    }

    public AuthumResponse registerClient(dummy.com.sampleauthumclient.models.AuthumClient client) {
        return mAuthumInterface.registerClient(client);
    }
}
