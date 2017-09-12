package handler;

import com.encoder.Encoder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import processor.StringProcessor;
import requests.RequestObject;
import results.ParseResult;

/**
 * Created by tyler on 9/12/2017.
 */

public class ParseIntHandler implements HttpHandler {
    public ParseIntHandler(){}
    @Override
    /**This method handles the clear request from the server*/
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream respBody = exchange.getResponseBody();
        Encoder encoder = new Encoder();
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);//otherwise send Forbidden/BadRequest/etc as needed
                RequestObject request = encoder.decodeRequestObj(exchange.getRequestBody());

                ParseResult result = StringProcessor.getInstance().ParseInt(request);
                encoder.encode(result, respBody);

                respBody.close();
            }
            else{
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                respBody.close();
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            respBody.close();
            //e.printStackTrace();
        }
    }
}
