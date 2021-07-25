package io.github.exortions.dotenv;

public class EnvParameterNotFoundException extends Exception {

    public EnvParameterNotFoundException(String message) {
        super(message);
    }

    public EnvParameterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
