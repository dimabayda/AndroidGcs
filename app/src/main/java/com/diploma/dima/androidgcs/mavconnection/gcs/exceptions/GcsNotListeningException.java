package com.diploma.dima.androidgcs.mavconnection.gcs.exceptions;

public class GcsNotListeningException extends Exception {
    @Override
    public String getMessage() {
        return "Please enable gcs listening!";
    }
}
