package dz13.expression;


public class DivByZero extends ExceptionExpression {
    public DivByZero() {
    }

    public DivByZero(String message) {
        super(message);
    }

    public DivByZero(String message, Throwable cause) {
        super(message, cause);
    }

    public DivByZero(Throwable cause) {
        super(cause);
    }

    public DivByZero(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
