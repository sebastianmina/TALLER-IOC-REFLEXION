package edu.escuelaing.arep.app.SeudoSpark;

import java.io.File;
import java.util.function.BiFunction;
import org.eclipse.jetty.client.HttpRequest;
import org.eclipse.jetty.client.HttpResponse;


public class SeudoSpark {
    
    /**
     * 
     * @param route
     * @param body
     */
    public static void get(String route, BiFunction<HttpRequest, HttpResponse, String> body){
        SeudoSparkServer server = SeudoSparkServer.getInstance();
        server.get(route, body);
    }

    /**
     * Coloca el puerto con su respectivo servidor
     * @param port
     */
    public static void port(int port){
        SeudoSparkServer server = SeudoSparkServer.getInstance();
        server.port(port);
    }

    /**
     * Corre el servidor http
     */
    public static void startServer(){
        SeudoSparkServer server = SeudoSparkServer.getInstance();
        server.startServer();
    }

}
