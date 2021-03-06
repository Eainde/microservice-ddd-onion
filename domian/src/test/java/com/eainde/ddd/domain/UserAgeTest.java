package com.eainde.ddd.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserAgeTest {

  @Test
  void objectCreation() {
    final UserAge userAge = ImmutableUserAge.builder().value(31).build();
    assertEquals(31, userAge.value().intValue());
  }

  @Test
  void testExpectedException() {
    IllegalStateException thrown =
        Assertions.assertThrows(
            IllegalStateException.class,
            () -> {
              ImmutableUserAge.builder().value(0).build();
            });
    assertTrue(thrown.getMessage().contains("user age cannot be 0"));
  }
}
