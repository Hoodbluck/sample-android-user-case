package dummy.com.sampleauthumclient.activities;

import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import dummy.com.sampleauthumclient.R;
import dummy.com.sampleauthumclient.models.User;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
@EActivity(R.layout.activity_registration)
public class RegistrationActivity  extends BaseActivity {

    @Extra
    User mUser;
    @ViewById(R.id.first_name_input)
    EditText mFirstNameInput;
    @ViewById(R.id.last_name_input)
    EditText mLastNameInput;
    @ViewById(R.id.email_input)
    EditText mEmailInput;

    @AfterViews
    public void afterViews() {
        mFirstNameInput.setText(mUser.getFirstName());
        mLastNameInput.setText(mUser.getLastName());
        mEmailInput.setText(mUser.getEmail());
    }

    @Click(R.id.register_button)
    public void onRegisterClick() {
        SuccessLoggedInActivity_.intent(this)
                .mUser(mUser)
                .start();

    }
}
