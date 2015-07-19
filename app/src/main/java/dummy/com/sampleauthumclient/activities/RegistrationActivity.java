package dummy.com.sampleauthumclient.activities;

import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
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
    EditText mfirstNameInput;
    @ViewById(R.id.last_name_input)
    EditText mlastNameInput;
    @ViewById(R.id.email_input)
    EditText memailInput;

    @AfterViews
    public void afterViews() {
        mfirstNameInput.setText(mUser.getFirstName());
        mlastNameInput.setText(mUser.getLastName());
        memailInput.setText(mUser.getEmail());
    }
}
