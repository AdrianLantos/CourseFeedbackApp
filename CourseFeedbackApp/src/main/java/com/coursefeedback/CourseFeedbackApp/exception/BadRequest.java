package com.coursefeedback.CourseFeedbackApp.exception;

public class BadRequest extends RuntimeException{
    public BadRequest(String message) {
        super(message);
    }
}
