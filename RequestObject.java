package requests;

/**
 * Created by tyler on 9/12/2017.
 */

public class RequestObject {
    private String stringToChange;
    private String ErrorMessage;

    public RequestObject(String stringToChange) {
        this.stringToChange = stringToChange;
    }

    public String getStringToChange() {
        return stringToChange;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }
}
