package com.railsware.mailtrap.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.railsware.mailtrap.exception.APIException;
import com.railsware.mailtrap.mail.*;
import com.railsware.mailtrap.utils.Utils;
import lombok.Builder;
import lombok.Builder.Default;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.sasl.AuthenticationException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Builder
public class MailTrapClient {
    private static Logger logger = LogManager.getLogger(MailTrapClient.class);
    private final String token;
    @Default
    private final String url = "https://send.api.mailtrap.io/api/send";

    private HttpClient client;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void setClient(HttpClient client) {
        this.client = client;
    }

    public String send(Mail mail) throws AuthenticationException, URISyntaxException, APIException {
        HttpResponse<String> response;
        try {
            HttpClient client = this.client == null ? HttpClient.newHttpClient() : this.client;
            String body = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mail);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .headers("Accept", "application/json", "Authorization", String.format("Bearer %s", token), "Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            logger.info("Sending email:");
            logger.info("From: " + mail.from());
            logger.info("To: " + mail.to());
            logger.info("Subject: " + mail.subject());

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("Status code of response: " + response.statusCode());

        if (response.statusCode() == 200) {
            logger.info("Response body: " + response.body());
            return response.body();
        } else if (response.statusCode() == 401) {
            throw new AuthenticationException();
        } else {
            throw new APIException();
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException, APIException {
        MailTrapClient mailTrapClient = MailTrapClient.builder().token("bdf9c489c612bf0172154a827f62e166").build();

        File file = Utils.loadFile("nature.jpeg");
//        File file = Utils.loadFile("index.html");
        String toBase64 = Utils.imageToBase64(file);

        Mail mail = Mail.builder()
                .from(Address.builder().email("str1985@demomailtrap.com").build())
                .to(List.of(Address.builder().email("str1985@gmail.com").name("Andrii Strashko").build()))
                .subject("Smoke testing of java sdk for mailtrap app")
                .text("Congratulations, your email was delivered successfully.")
                .html("<p>Congratulations, your email was delivered <strong>successfully</strong>.</p>")
                .attachments(List.of(Attachment.builder().content(toBase64).filename("nature.jpeg").type(Type.IMAGE).disposition(Disposition.INLINE).content_id("nature.jpeg").build()))
                //.attachments(List.of(Attachment.builder().content(toBase64).filename("index.html").type(Type.TEXT).disposition(Disposition.ATTACHMENT).build()))
                .build();

        mailTrapClient.send(mail);
    }
}
