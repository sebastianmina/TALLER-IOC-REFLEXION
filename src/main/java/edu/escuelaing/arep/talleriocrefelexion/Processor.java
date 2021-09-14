package edu.escuelaing.arep.app;

//import java.net.*;
import org.eclipse.jetty.client.HttpRequest;
import org.eclipse.jetty.client.HttpResponse;


public interface Processor {
    public String handle(String path, HttpRequest req, HttpResponse resp);
}
