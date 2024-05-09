package com.railsware.mailtrap.client;

import com.railsware.mailtrap.exception.APIException;
import com.railsware.mailtrap.mail.Address;
import com.railsware.mailtrap.mail.Mail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MailTrapClientTest {
    private MailTrapClient mailTrapClient;

    @BeforeEach
    void setUp() {
        mailTrapClient = MailTrapClient.builder()
                .token("mailtrap_api_token")
                .build();
    }

    @Test
    public void sendTestOk() throws IOException, InterruptedException, APIException, URISyntaxException {
        HttpClient httpClient = mock(HttpClient.class);
        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn("Email sent successfully");

        when(httpClient.send(any(), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        mailTrapClient.setClient(httpClient);

        Mail mail = Mail.builder()
                .from(Address.builder().email("str1985@demomailtrap.com").build())
                .to(List.of(Address.builder().email("str1985@gmail.com").name("Andrii Strashko").build()))
                .subject("Smoke testing of java sdk for mailtrap app")
                .build();

        assertEquals("Email sent successfully", mailTrapClient.send(mail));
    }

    @Test
    public void sendTestUnauthorized() throws IOException, InterruptedException {
        HttpClient httpClient = mock(HttpClient.class);
        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpResponse.statusCode()).thenReturn(401);

        when(httpClient.send(any(), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        mailTrapClient.setClient(httpClient);

        Mail mail = Mail.builder()
                .from(Address.builder().email("str1985@demomailtrap.com").build())
                .to(List.of(Address.builder().email("str1985@gmail.com").name("Andrii Strashko").build()))
                .subject("Smoke testing of java sdk for mailtrap app")
                .build();

        assertThrows(AuthenticationException.class, () -> {
            mailTrapClient.send(mail);
        });
    }

    @Test
    public void sendTestBadRequest() throws IOException, InterruptedException {
        HttpClient httpClient = mock(HttpClient.class);
        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpResponse.statusCode()).thenReturn(400);

        when(httpClient.send(any(), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        mailTrapClient.setClient(httpClient);

        Mail mail = Mail.builder()
                .from(Address.builder().email("str1985@demomailtrap.com").build())
                .to(List.of(Address.builder().email("str1985@gmail.com").name("Andrii Strashko").build()))
                .subject("Smoke testing of java sdk for mailtrap app")
                .build();

        assertThrows(APIException.class, () -> {
            mailTrapClient.send(mail);
        });
    }
}