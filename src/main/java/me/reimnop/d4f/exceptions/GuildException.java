package me.reimnop.d4f.exceptions;

public class GuildException extends Exception {
    private final String message;

    public GuildException(Long id) {
        message = "Не удалось найти сервер с id '" + id + "'!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
