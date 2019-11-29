package com.arvoia.sampletask.utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

class SimpleCipherToolTest {

  private SimpleCipherTool underTest;

  @Test
  public void testCipher() {
    // given
    // when
    String actual = underTest.cipher("");
    // then
    assertNotNull(actual);
  }

  @Test
  public void testDecipher() {
    // given
    // when
    String actual = underTest.decipher("");
    // then
    assertNotNull(actual);
  }

}
