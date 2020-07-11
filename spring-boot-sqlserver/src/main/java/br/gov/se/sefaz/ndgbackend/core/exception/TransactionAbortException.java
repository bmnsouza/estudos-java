package br.gov.se.sefaz.ndgbackend.core.exception;

public class TransactionAbortException extends Exception {

  private static final long serialVersionUID = 1L;

  public TransactionAbortException(String message) {
    super(message);
  }

  public TransactionAbortException(String parameter, String message) {
    super(new StringBuilder(parameter).append(": ").append(message).toString());
  }

}