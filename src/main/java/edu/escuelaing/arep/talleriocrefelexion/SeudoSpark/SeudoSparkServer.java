package edu.escuelaing.arep.app.SeudoSpark;

//import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
//import java.util.function.Supplier;

import edu.escuelaing.arep.app.HttpServer;
import edu.escuelaing.arep.app.Processor;
import org.eclipse.jetty.client.HttpRequest;
import org.eclipse.jetty.client.HttpResponse;

public class SeudoSparkServer implements Processor {
    private int httpPort;
    private static SeudoSparkServer instance = new SeudoSparkServer();
    Map<String, BiFunction<HttpRequest, HttpResponse, String>> functions = new HashMap(); 
    HttpServer server = new HttpServer();

    private SeudoSparkServer() {
        server.registerProcessor("/",this);
    }

    public static SeudoSparkServer getInstance() {
        return instance;
    }


    /**
     * Obtiene el request que se pide
     * @param route
     * @param sup
     */
    public void get(String route, BiFunction<HttpRequest, HttpResponse, String> sup) {
        functions.put(route, sup);
    }
    
    /** 
     * Empieza el servidor
     */
    public void startServer() {
        try {
            server.startServer(httpPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Coloca el puerto del servidor
     * @param serverPort server port
     */
    public void port(int serverPort) {
        this.httpPort = serverPort;
    }

    /**
     * @param path path that request the client
     * @param req request of this path
     * @param resp response of this request
     */
    public String handle(String path, HttpRequest req, HttpResponse resp) {
        if (functions.containsKey(path)) {
            return httpHeader() + functions.get(path).apply(req , resp);
        }
        return httpError();
    }

    /**
     * Obtiene el http error
     * @return String
     */
    private String httpError() {
        return "HTTP/1.1 200 OK\r\n"
        + "Content-Type: text/html\r\n"
         + "\r\n"
         + "<!DOCTYPE html>\n"
         + "<html>\n"
         + "<head>\n"
         + "<meta charset=\"UTF-8\">\n"
         + "<title>Title of the document</title>\n"
         + "</head>\n"
         + "<body>\n"
         + "<h1>Error</h1>\n"
         + "</body>\n"
         + "</html>\n";
    }

    /**
     * Retorna un header correcto
     * @return
     */
    private String httpHeader() {
        return "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n";
    }
}
