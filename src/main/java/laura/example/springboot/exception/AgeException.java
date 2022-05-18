package laura.example.springboot.exception;

public class AgeException extends RuntimeException{

    public AgeException(String message){
        super(message);
    }

}
