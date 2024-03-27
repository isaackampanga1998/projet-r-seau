import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        Serveur serveur = new Serveur();
        serveur.demarrer();
    }
}
