package com.railsware.mailtrap.mail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    @Test
    void emailTest() {
        Address address = new Address("str1985@gmail.com", "Andrii Strashko");
        assertEquals("str1985@gmail.com", address.email());
        assertEquals("Andrii Strashko", address.name());
    }

    @Test
    void nameIsNull() {
        Address address = new Address("str1985@gmail.com", null);
        assertEquals("str1985@gmail.com", address.email());
    }

    @Test
    void shouldThrowException() {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            new Address(null, null);
        });
        assertEquals("email is marked non-null but is null", exception.getMessage());
    }
}