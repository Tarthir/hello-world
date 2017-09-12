package stringprocessor;

import com.encoder.Encoder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import requests.RequestObject;
import results.ParseResult;
import results.ResultObject;

/**
 * Created by tyler on 9/12/2017.
 */
public class ClientCommunicator {
    private static ClientCommunicator ourInstance = new ClientCommunicator();

    public static ClientCommunicator getInstance() {
        return ourInstance;
    }

    private ClientCommunicator() {
    }

    public Object send(URL url, RequestObject request) {
        try {
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);    // There is a request body

            http.addRequestProperty("Accept", "application/json");

            Encoder encoder = new Encoder();
            encoder.encode(request, http.getOutputStream());

            http.connect();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                if(url.toString().contains("/parseInt")){
                    return encoder.decodeParseResult(http.getInputStream());
                }
                return encoder.decodeResultObject(http.getInputStream());

            } else {

                System.out.println("ERROR: " + http.getResponseMessage());
                if(url.toString().contains("/parseInt")){
                    return encoder.decodeParseResult(http.getInputStream());
                }
                return encoder.decodeResultObject(http.getErrorStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }

}
