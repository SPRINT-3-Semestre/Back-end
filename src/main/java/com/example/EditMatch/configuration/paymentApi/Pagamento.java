package com.example.EditMatch.configuration.paymentApi;

import com.example.EditMatch.entity.Response;
import com.mifmif.common.regex.Generex;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Locale;

public class Pagamento {
    public static StringBuilder enviarPagamento(String token, String nomePagante, double valor) {
        StringBuilder responseString = null;
        String valorFormatado = String.format(Locale.US, "%.2f", valor); // Formata o valor com duas casas decimais
        String payload = "{\r\n"
                + "  \"calendario\": {\r\n"
                + "    \"expiracao\": 3600\r\n"
                + "  },\r\n"
                + "  \"devedor\": {\r\n"
                + "    \"cpf\": \"12345678909\",\r\n"
                + "    \"nome\": \"" + nomePagante + "\"\r\n"
                + "  },\r\n"
                + "  \"valor\": {\r\n"
                + "    \"original\": \"" + valorFormatado + "\"\r\n"
                + "  },\r\n"
                + "  \"chave\": \"63d55e6e-b49a-4f08-ae86-2ca2bdddfa89\",\r\n"
                + "  \"solicitacaoPagador\": \"Cobrança dos serviços prestados.\"\r\n"
                + "}";

        try {
            String txid;
            Generex generex = new Generex("[a-zA-Z0-9]{26,35}");
            txid = generex.random();
            URL url = new URL("https://api-pix-h.gerencianet.com.br/v2/cob/" + txid);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Accept", "application/json, application/*+json");

            OutputStream os = conn.getOutputStream();
            os.write(payload.getBytes());
            os.flush();

            InputStreamReader reader = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(reader);

            StringBuilder responseBuilder = new StringBuilder();
            String response;
            System.out.println("Enviando " + payload);
            while ((response = br.readLine()) != null) {
                responseBuilder.append(response); // Adiciona cada linha da resposta ao StringBuilder
                System.out.println("Recebido " + response);
            }

            responseString = responseBuilder;

            conn.disconnect();
            return responseString;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar pagamento");
        }
        return responseString;
    }

    public static StringBuilder enviarQrCode(String token, Integer id) {
        StringBuilder qrCodeResponse = new StringBuilder();
        try {
            URL url = new URL("https://api-pix-h.gerencianet.com.br/v2/loc/" + id + "/qrcode");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                qrCodeResponse.append(line);
            }
            conn.disconnect();
            return qrCodeResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qrCodeResponse;
    }
}
