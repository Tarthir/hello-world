package stringprocessor;

import com.encoder.Encoder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import StringProcInterface.IStringProcessor;
import requests.RequestObject;
import results.ParseResult;
import results.ResultObject;

/**
 * Created by tyler on 9/12/2017.
 */

public class StringProcessorProxy implements IStringProcessor{

    private static StringProcessorProxy ourInstance = new StringProcessorProxy();

    public static StringProcessorProxy getInstance() {
        return ourInstance;
    }

    private StringProcessorProxy() {
        this.serverHost = "localhost";
        this.serverPort = "8080";
    }

    private String serverHost;
    private String serverPort;

    @Override
    public ResultObject ToLowerCase(RequestObject request) {

        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/toLowerCase");
            return (ResultObject) ClientCommunicator.getInstance().send(url,request);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultObject Trim(RequestObject request) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/trim");
            return (ResultObject) ClientCommunicator.getInstance().send(url,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ParseResult ParseInt(RequestObject request) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/parseInt");
            return (ParseResult)ClientCommunicator.getInstance().send(url,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object Execute() {
        return null;
    }
}
