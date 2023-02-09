package expression;

public class IncorrectExpression extends ExceptionExpression{
    public IncorrectExpression() {
    }

    public IncorrectExpression(String message) {
        super(message);
    }

    public IncorrectExpression(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectExpression(Throwable cause) {
        super(cause);
    }

    public IncorrectExpression(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
