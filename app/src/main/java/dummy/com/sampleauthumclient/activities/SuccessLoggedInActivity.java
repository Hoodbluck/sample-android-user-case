package dummy.com.sampleauthumclient.activities;

import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import dummy.com.sampleauthumclient.R;

/**
 * Created on 7/19/15.
 *
 * @author Skye Schneider
 */
@EActivity(R.layout.activity_success)
public class SuccessLoggedInActivity extends BaseActivity {
    @ViewById(R.id.welcome_text)
    TextView mWelcomeText;

    @Extra
    String mWelcomeMessage;

    @AfterViews
    public void afterViews() {
        mWelcomeText.setText(mWelcomeMessage);
    }
}
