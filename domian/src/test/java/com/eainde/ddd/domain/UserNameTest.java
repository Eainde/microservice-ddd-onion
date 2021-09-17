package com.eainde.ddd.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserNameTest {

  @Test
  void objectCreation() {
    final UserName username = ImmutableUserName.builder().value("asdfghjkjhgfds").build();
    assertEquals("asdfghjkjhgfds", username.value());
  }

  @Test
  void testExpectedException() {
    IllegalStateException thrown =
        Assertions.assertThrows(
            IllegalStateException.class,
            () -> {
              ImmutableUserName.builder()
                  .value(
                      "qwertyuiokmnbvcxzaqwertyuiolkjhgfdsaqwertyuioplmnbvcxzsaqwertyuiwertyuioiuytrtyuiojhgbnvcfgyuiuytdfghjkh")
                  .build();
            });
    assertTrue(thrown.getMessage().contains("User Name"));
    assertTrue(thrown.getMessage().contains("must be less than or equals"));
  }
}
