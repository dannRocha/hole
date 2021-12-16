package com.hole.dto.util;

import lombok.Getter;

@Getter
public class IndexDTO {
  
  private final String version;
  private final String docs;

  public IndexDTO(String version, String docs) {
    this.version = version;
    this.docs = docs;
  }
}
