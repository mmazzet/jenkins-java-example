package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;

@RestController
public class VersionController {

  @Value("${app.version}")
  private String version;

  @Value("${build.timestamp}")
  private String buildTimestamp;

  @GetMapping("/version")
  public Map<String, String> getVersion() {
    Map<String, String> map = new HashMap<>();
    map.put("version", version);
    map.put("buildTimestamp", buildTimestamp);
    return map;
  }

  void setVersion(String version) {
    this.version = version;
  }

  void setBuildTimestamp(String buildTimestamp) {
    this.buildTimestamp = buildTimestamp;
  }
}