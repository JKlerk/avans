package nl.jklerk.school;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Request {
    public static String getRequestBody(HttpExchange exc) throws IOException {
        InputStreamReader isr =  new InputStreamReader(exc.getRequestBody(),"utf-8");
        BufferedReader br = new BufferedReader(isr);

        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }

        br.close();
        isr.close();

        System.out.println(buf.toString());

        return buf.toString();
    }
}
