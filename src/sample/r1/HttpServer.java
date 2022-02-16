package sample.r1;
import java.net.*;
import java.io.*;
public class HttpServer {
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    int port;

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = new HttpServer();
    }

    public HttpServer() throws IOException {
        port = getPort();
        serverSocket = new ServerSocket(port);
        System.out.println("Port "+port);

        while(true) {
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>Title of the document</title>\n"
                    + "</head>"
                    + "<body>"
                    + "My Web Site"
                    + "</body>"
                    + "</html>";
            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        }
    }



    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set
    }
}
