package dummy.com.sampleauthumclient.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 7/19/15.
 *
 * @author Skye Schneider
 */
public class AuthumClient implements Parcelable {
    @SerializedName("clientId")
    private String clientId;
    @SerializedName("name")
    private String name;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.clientId);
        dest.writeString(this.name);
    }

    public AuthumClient() {
    }

    protected AuthumClient(Parcel in) {
        this.clientId = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<AuthumClient> CREATOR = new Parcelable.Creator<AuthumClient>() {
        public AuthumClient createFromParcel(Parcel source) {
            return new AuthumClient(source);
        }

        public AuthumClient[] newArray(int size) {
            return new AuthumClient[size];
        }
    };
}
