package sample.ej2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class URLReader {

    String url;

    public static void main(String[] args) throws Exception {
        URLReader reader = new URLReader();
    }

    public URLReader() throws IOException {
        url = scanURL();
        urlReader();
    }
    public String scanURL() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa la URL a buscar");
        String url = scanner.nextLine();
        while (true) {
            if (url != "") {
                scanner.close();
                return url;
            } else {
                url = scanner.nextLine();
            }
        }
    }

    public void urlReader() throws IOException {
        String site = url;
        URL siteURL = new URL(site);
        URLConnection urlConnection = siteURL.openConnection();

        Map<String, List<String>> headers = urlConnection.getHeaderFields();
        Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();

        for (Map.Entry<String, List<String>> entry : entrySet) {
            String headerName = entry.getKey();
            List<String> headerValues = entry.getValue();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(siteURL.openStream()))) {
            String inputLine = null;
            String save = "";
            while ((inputLine = reader.readLine()) != null) {
                save+= inputLine;
            }
            String path = "resultado.html";
            Files.write(Paths.get(path), save.getBytes());

        } catch (IOException x) {
            System.err.println(x);
        }
        System.out.println("html generate");
    }
}
