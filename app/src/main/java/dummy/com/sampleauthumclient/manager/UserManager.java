package dummy.com.sampleauthumclient.manager;

import com.google.gson.Gson;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import dummy.com.sampleauthumclient.data.http.AuthumClient;
import dummy.com.sampleauthumclient.models.AuthumResponse;
import dummy.com.sampleauthumclient.models.User;

/**
 * Created on 7/19/15.
 *
 * @author Skye Schneider
 */
@EBean(scope = EBean.Scope.Singleton)
public class UserManager {

    @Bean
    AuthumClient mAuthumClient;

    public void authenicate(String email, AuthumAuthenticationCallback callback) {
        AuthumResponse response = mAuthumClient.authenticate("sample_authum_client", email);

        if (response == null) {
            callback.authenticationFailure();
            return;
        }

        else if (response.getCode() >= 0 ) {
            User user = new Gson().fromJson(response.getValue(), User.class);
            callback.authenticationSuccess(user);
        }
    }

    public static abstract class AuthumAuthenticationCallback {
        public abstract void authenticationSuccess(User user);
        public abstract void authenticationFailure();
    }
}
