package dummy.com.sampleauthumclient.models;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.StringUtils;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
public class AuthumResponse<T>{
    public enum AuthumResponseStatus {
        SUCCESS("success"),
        USER_INVALID("user_invalid"),
        USER_ALREADY_REGISTERED("user_already_registered"),
        LOGIN_INVALID("login_invalid"),
        UNKNOWN("unknown");

        private String mText;
        AuthumResponseStatus(String text) {
            mText = text;
        }

        public static AuthumResponseStatus getAuthumResponse(String value) {
            AuthumResponseStatus status = UNKNOWN;
            for (AuthumResponseStatus responseStatus : AuthumResponseStatus.values()) {
                if (StringUtils.equalsIgnoreCase(value, responseStatus.mText)) {
                    status = responseStatus;
                    break;
                }
            }
            return status;
        }
    }

    @SerializedName("code")
    private int code;
    @SerializedName("status")
    private String status;
    @SerializedName("value")
    private T value;


    public String getValue() {
        return (String)value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AuthumResponse() {
    }

}
