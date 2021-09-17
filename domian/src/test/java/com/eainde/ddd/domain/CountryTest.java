package com.eainde.ddd.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {
    @Test
    void objectCreation() {
        final Country country = ImmutableCountry.builder().value("asdfghjkjhgfds").build();
        assertEquals("asdfghjkjhgfds", country.value());
    }

    @Test
    void testExpectedException() {
        IllegalStateException thrown =
                Assertions.assertThrows(
                        IllegalStateException.class,
                        () -> {
                            ImmutableCountry.builder()
                                    .value(
                                            "qwertyuiokmnbvcxzaqwertyuiolkjhgfdsaqwertyuioplmnbvcxzsaqwertyuiwertyuioiuytrtyuiojhgbnvcfgyuiuytdfghjkh")
                                    .build();
                        });
        assertTrue(thrown.getMessage().contains("User Country"));
        assertTrue(thrown.getMessage().contains("must be less than or equals"));
    }
}