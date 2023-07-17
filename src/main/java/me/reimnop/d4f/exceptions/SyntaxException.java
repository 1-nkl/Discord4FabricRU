package me.reimnop.d4f.exceptions;

public class SyntaxException extends Exception {
    private final String message;

    public SyntaxException(int index) {
        message = "Недопустимый синтаксис ограничений в " + index + "!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
