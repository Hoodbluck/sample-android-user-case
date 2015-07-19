package dummy.com.sampleauthumclient.activities;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import dummy.com.sampleauthumclient.R;
import dummy.com.sampleauthumclient.data.http.AuthumClient;
import dummy.com.sampleauthumclient.db.UserDatabase;
import dummy.com.sampleauthumclient.manager.UserManager;
import dummy.com.sampleauthumclient.models.User;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @Bean
    UserDatabase mUserDatabase;

    @Bean
    UserManager mUserManager;

    @Bean
    AuthumClient mAuthumClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Click(R.id.authum_button)
    public void onAuthumButtonClick() {
        //TODO see if user is in database
        String URL = "content://com.hoodbluck.authum.provider/email";
        Uri email = Uri.parse(URL);
        Cursor c = getContentResolver().query(email, null, null, null, null);
        if (c == null) {
            return;
        }

        if (!c.moveToFirst()) {
            Toast.makeText(this, " no content yet!", Toast.LENGTH_LONG).show();
        }else{
            registerClient(c.getString(0));

        }
        c.close();

    }

    @Background
    public void registerClient(String email) {
        dummy.com.sampleauthumclient.models.AuthumClient client = new dummy.com.sampleauthumclient.models.AuthumClient();
        client.setClientId("sample_authum_client");
        client.setName("Sample App");
        mAuthumClient.registerClient(client);
        authenticateUser(email);
    }

    private void authenticateUser(String email) {
        mUserManager.authenicate(email, new UserManager.AuthumAuthenticationCallback() {
            @Override
            public void authenticationSuccess(User user) {
                User u = mUserDatabase.getUser(user.getEmail());
                if (u == null) {
                    launchRegistrationActivity(user);
                } else {
                    launchSuccessActivity(user);
                }
            }

            @Override
            public void authenticationFailure() {

            }
        });


    }

    @UiThread
    protected void launchSuccessActivity(User user) {
        SuccessLoggedInActivity_.intent(LoginActivity.this)
                .mWelcomeMessage("Welcome back, "+user.getFirstName())
                .start();
    }

    @UiThread
    protected void launchRegistrationActivity(User user) {
        RegistrationActivity_.intent(LoginActivity.this)
                .mUser(user)
                .start();
    }
}

