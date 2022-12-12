package com.eroom.todo.exception;

import lombok.Getter;

public enum ExceptionCode {
    TODOS_NOT_FOUND(404, "Todos not found");
    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
