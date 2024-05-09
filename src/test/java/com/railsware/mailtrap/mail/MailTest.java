package com.railsware.mailtrap.mail;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MailTest {

    @Test
    void emailTest() {
        Mail mail = Mail.builder()
                .from(Address.builder().email("str1985@demomailtrap.com").build())
                .to(List.of(Address.builder().email("str1985@gmail.com").name("Andrii Strashko").build()))
                .subject("Smoke testing of java sdk for mailtrap app")
                .text("Congratulations, your email was delivered successfully.")
                .html("<p>Congratulations, your email was delivered <strong>successfully</strong>.</p>")
                .build();

        assertEquals("str1985@demomailtrap.com", mail.from().email());
        assertNull(mail.from().name());
        assertEquals("str1985@gmail.com", mail.to().get(0).email());
        assertEquals("Andrii Strashko", mail.to().get(0).name());
        assertEquals("Smoke testing of java sdk for mailtrap app", mail.subject());
        assertEquals("Congratulations, your email was delivered successfully.", mail.text());
        assertEquals("<p>Congratulations, your email was delivered <strong>successfully</strong>.</p>", mail.html());
        assertNull(mail.attachments());
    }

    @Test
    void emailEmptyTest() {
        Mail mail = Mail.builder()
                .from(Address.builder().email("str1985@demomailtrap.com").build())
                .to(List.of(Address.builder().email("str1985@gmail.com").name("Andrii Strashko").build()))
                .subject("Smoke testing of java sdk for mailtrap app")
                .build();

        assertEquals("str1985@demomailtrap.com", mail.from().email());
        assertNull(mail.from().name());
        assertEquals("str1985@gmail.com", mail.to().get(0).email());
        assertEquals("Andrii Strashko", mail.to().get(0).name());
        assertNull(mail.attachments());
    }

    @Test
    void shouldThrowExceptionFromNull() {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            Mail.builder()
                    .to(List.of(Address.builder().email("str1985@gmail.com").name("Andrii Strashko").build()))
                    .subject("Smoke testing of java sdk for mailtrap app")
                    .build();
        });
        assertEquals("from is marked non-null but is null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionToNull() {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            Mail.builder()
                    .from(Address.builder().email("str1985@demomailtrap.com").build())
                    .subject("Smoke testing of java sdk for mailtrap app")
                    .build();
        });
        assertEquals("to is marked non-null but is null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionSubjectNull() {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            Mail.builder()
                    .from(Address.builder().email("str1985@demomailtrap.com").build())
                    .to(List.of(Address.builder().email("str1985@gmail.com").name("Andrii Strashko").build()))
                    .build();
        });
        assertEquals("subject is marked non-null but is null", exception.getMessage());
    }
}