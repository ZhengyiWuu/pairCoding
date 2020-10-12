package util;

public class MyException extends Exception{
    public MyException() {
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message) {
        super(message);
    }
}
