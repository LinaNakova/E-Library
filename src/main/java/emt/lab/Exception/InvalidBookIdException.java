package emt.lab.Exception;

public class InvalidBookIdException extends RuntimeException{
    public InvalidBookIdException(Long id) {
        super(String.format("Book with id %d doesn't exist", id));
    }
}
