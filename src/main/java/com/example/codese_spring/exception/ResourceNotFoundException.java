package com.example.codese_spring.exception;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String id) {
    super("Resource with resourceName={" + id + "} already exists");
  }
}
