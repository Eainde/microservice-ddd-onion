package com.eainde.ddd.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserIdTest {

  @Test
  void objectCreation() {
    final UserId userId = ImmutableUserId.builder().value(31L).build();
    assertEquals(31, userId.value().intValue());
  }

  @Test
  void testExpectedException() {
    IllegalStateException thrown =
        Assertions.assertThrows(
            IllegalStateException.class,
            () -> {
              ImmutableUserId.builder().value(0L).build();
            });
    assertTrue(thrown.getMessage().contains("user id cannot be 0"));
  }
}
