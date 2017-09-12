package results;

/**
 * Created by tyler on 9/12/2017.
 */

public class ParseResult extends ResultObject {
    private int result;
    public ParseResult(String errorMessage){
        super(errorMessage);
    }
    public ParseResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }
}
