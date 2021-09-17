package com.eainde.ddd.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserIdTest {

    @Test
    void objectCreation() {
        final UserId userId = ImmutableUserId.builder().value(31).build();
        assertEquals(31, userId.value().intValue());
    }

    @Test
    void testExpectedException() {
        IllegalStateException thrown =
                Assertions.assertThrows(
                        IllegalStateException.class,
                        () -> {
                            ImmutableUserId.builder().value(0).build();
                        });
        assertTrue(thrown.getMessage().contains("user id cannot be 0"));
    }

}