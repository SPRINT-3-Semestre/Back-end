package com.example.EditMatch.configuration.paymentApi;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONObject;

public class Auth {

    Dotenv dotenv = Dotenv.load();
    public final String client_id = dotenv.get("CLIENT_ID");
    public final String client_secret = dotenv.get("CLIENT_SECRET");
    public final String basicAuth = Base64.getEncoder().encodeToString(((client_id+':'+client_secret).getBytes()));


    public String geraToken() {
        String token="";
        try {
            //Diretório em que seu certificado em formato .p12 deve ser inserido
            System.setProperty("javax.net.ssl.keyStore", "producao-559243-editmatch.p12");
            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            URL url = new URL ("https://pix.api.efipay.com.br/oauth/token");
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Basic "+ basicAuth);
            conn.setSSLSocketFactory(sslsocketfactory);
            String input = "{\"grant_type\": \"client_credentials\"}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            InputStreamReader reader = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(reader);

            String response;
            StringBuilder responseBuilder = new StringBuilder();
            while ((response = br.readLine()) != null) {
                System.out.println(response);
                responseBuilder.append(response);
            }
            try {
                JSONObject jsonObject = new JSONObject(responseBuilder.toString());
                token = jsonObject.getString("access_token");
            } catch (Exception e) {
                // TODO: handle exception
            }
            conn.disconnect();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return token;

    }
}