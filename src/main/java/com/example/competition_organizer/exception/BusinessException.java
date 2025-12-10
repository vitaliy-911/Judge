<<<<<<<< HEAD:src/main/java/com/example/competition_organizer/exception/BusinessException.java
package com.example.competition_organizer.exception;
========
package competitionOrganizer.exception;
>>>>>>>> upstream/main:src/main/java/competitionOrganizer/exception/BusinessException.java

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
