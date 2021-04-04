package com.example.codese_spring.exception;

public class ResourceAlreadyExistException extends RuntimeException {

  public ResourceAlreadyExistException(String message) {
    super("Resource with resourceName={" + message + "} already exists");
  }
}
