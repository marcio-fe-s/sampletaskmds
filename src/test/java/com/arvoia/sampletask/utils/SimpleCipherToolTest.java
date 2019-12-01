package com.arvoia.sampletask.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleCipherToolTest {

  @Test
  public void testCipher()
  {
      SimpleCipherTool tool = new SimpleCipherTool("abc");
      String response = tool.cipher("aaa");
      
      assertEquals("bcd", response);
  }
  
  @Test
  public void testCipher2()
  {
      SimpleCipherTool tool = new SimpleCipherTool("abc");
      String response = tool.cipher("Sample task.");
      
      assertEquals("Scpqnh vdtm.", response);
  }
  
  @Test
  public void testverifyEnglishLowercaseAlphabet() {
  	assertTrue(SimpleCipherTool.verifyEnglishLowercaseAlphabet('a'));
      assertTrue(SimpleCipherTool.verifyEnglishLowercaseAlphabet('b'));
      assertTrue(SimpleCipherTool.verifyEnglishLowercaseAlphabet('x'));
      assertTrue(SimpleCipherTool.verifyEnglishLowercaseAlphabet('z'));
      assertFalse(SimpleCipherTool.verifyEnglishLowercaseAlphabet(';'));
      assertFalse(SimpleCipherTool.verifyEnglishLowercaseAlphabet('A'));
      assertFalse(SimpleCipherTool.verifyEnglishLowercaseAlphabet('A'));
      assertFalse(SimpleCipherTool.verifyEnglishLowercaseAlphabet('.'));
      assertFalse(SimpleCipherTool.verifyEnglishLowercaseAlphabet('ç'));
  }
  
  @Test
  public void testDecipher()
  {
      SimpleCipherTool tool = new SimpleCipherTool("abc");
      String response = tool.decipher("bcd");
      
      assertEquals("aaa", response);
  }
  
  @Test
  public void testDecipher2()
  {
      SimpleCipherTool tool = new SimpleCipherTool("abc");
      String response = tool.decipher("Scpqnh vdtm.");
      
      assertEquals("Sample task.", response);
  }
  
  @Test
  public void testDecipher3()
  {
      SimpleCipherTool tool = new SimpleCipherTool("arvoia");
      String response = tool.decipher("ilpeb://cxcivt.rxn/uodacbrkar/tsieufusozvet");
      
      assertEquals("https://github.com/csorbazoli/sampletaskmds", response);
  }
}
