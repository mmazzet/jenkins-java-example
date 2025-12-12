package com.example.controller;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;

public class VersionControllerTest {

  @Test
  public void testGetVersion() {
    VersionController controller = new VersionController();

    controller.setVersion("1.0.0");
    controller.setBuildTimestamp("2025-12-11 18:00:00");

    Map<String, String> result = controller.getVersion();

    assertEquals("1.0.0", result.get("version"));
    assertEquals("2025-12-11 18:00:00", result.get("buildTimestamp"));
  }
}