package StringProcInterface;

import requests.RequestObject;
import results.ParseResult;
import results.ResultObject;

/**
 * Created by tyler on 9/12/2017.
 */

public interface IStringProcessor {

    ResultObject ToLowerCase(RequestObject obj);

    ResultObject Trim(RequestObject obj);

    ParseResult ParseInt(RequestObject obj);

    Object Execute();
}
