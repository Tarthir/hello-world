package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * Created by tyler on 9/12/2017.
 */

public class ExecuteHandler implements HttpHandler {
    public ExecuteHandler(){}
    @Override
    /**This method handles the clear request from the server*/
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream respBody = exchange.getResponseBody();
        // ClearService service = new ClearService();
        //Encoder encoder = new Encoder();
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);//otherwise send Forbidden/BadRequest/etc as needed

                //ClearResult result = service.clear();
                //encoder.encode(result,respBody);

                respBody.close();
            }
            else{
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                //encoder.encode(new ClearResult("Should be a POST not GET"),respBody);
                respBody.close();
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            //encoder.encode(new ClearResult("Server Error"),respBody);
            respBody.close();
            //e.printStackTrace();
        }
    }
}
