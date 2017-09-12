package processor;

import StringProcInterface.IStringProcessor;
import requests.RequestObject;
import results.ParseResult;
import results.ResultObject;

/**
 * Created by tyler on 9/12/2017.
 */

public class StringProcessor implements IStringProcessor{
    private static StringProcessor ourInstance = null;

    private StringProcessor(){

    }
    public static StringProcessor getInstance() {
        if(ourInstance == null) {
            ourInstance = new StringProcessor();
        }
        return ourInstance;
    }


    @Override
    public ResultObject ToLowerCase(RequestObject obj) {
        return new ResultObject(obj.getStringToChange().toLowerCase());
    }

    @Override
    public ResultObject Trim(RequestObject obj) {
        return new ResultObject(obj.getStringToChange().trim());
    }

    @Override
    public ParseResult ParseInt(RequestObject obj) {
        try {
            return new ParseResult(Integer.parseInt(obj.getStringToChange()));
        }
        catch (Exception e){
            return new ParseResult(e.toString());
        }
    }

    @Override
    public Object Execute() {
        return null;
    }
}
