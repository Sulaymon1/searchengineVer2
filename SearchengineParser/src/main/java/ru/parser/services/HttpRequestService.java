package ru.parser.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Date 19.10.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
public class HttpRequestService {

    public void sendMailByHttp(String topic,String message){
        String url = "http://localhost:8093/sendMail?topic="+topic.replace(" ", "_")+"&message="+message.replace(" ", "_").replace("/", "_");

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
