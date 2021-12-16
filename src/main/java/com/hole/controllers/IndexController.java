package com.hole.controllers;

import com.hole.dto.util.IndexDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping
@Api(tags = "Index", authorizations = { @Authorization(value="accessEverything")})
public class IndexController {

  @Value("${hole.api.version}")
  private String version;
  
  @Value("${hole.api.documentation}")
  private String docs;

  @GetMapping
  public ResponseEntity<IndexDTO> index() {
    return ResponseEntity.ok(
      new IndexDTO(version, docs)
    );
  }

}
