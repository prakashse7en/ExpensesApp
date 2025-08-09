package com.digital.userprofile;

import org.springframework.boot.SpringApplication;

public class TestApplication {

  public static void main(String[] args) {
    SpringApplication
      .from(UserProfileApplication::main)
      .with(ContainersConfig.class)
      .run(args);
  }
}
