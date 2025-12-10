<<<<<<<< HEAD:src/main/java/com/example/competition_organizer/exception/ResourceNotFoundException.java
package com.example.competition_organizer.exception;
========
package competitionOrganizer.exception;
>>>>>>>> upstream/main:src/main/java/competitionOrganizer/exception/ResourceNotFoundException.java

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
