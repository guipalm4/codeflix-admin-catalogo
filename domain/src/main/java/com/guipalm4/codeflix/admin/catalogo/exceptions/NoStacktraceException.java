package com.guipalm4.codeflix.admin.catalogo.exceptions;

public class NoStacktraceException extends RuntimeException {

    public NoStacktraceException(final String message) {
        super(message, null);
    }

    public NoStacktraceException(final String message, Throwable cause) {
        super(message, cause, true, false);
    }
}
