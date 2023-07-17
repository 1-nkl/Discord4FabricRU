package me.reimnop.d4f.exceptions;

public class ChannelException extends Exception {
    private final String message;

    public ChannelException(Long id) {
        message = "Не удалось найти канал с id '" + id + "'!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
