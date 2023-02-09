package dz13.expression;

public abstract class ExceptionExpression extends Exception{
    public ExceptionExpression() {
    }

    public ExceptionExpression(String message) {
        super(message);
    }

    public ExceptionExpression(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionExpression(Throwable cause) {
        super(cause);
    }

    public ExceptionExpression(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
