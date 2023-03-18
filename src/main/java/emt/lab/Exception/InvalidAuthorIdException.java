package emt.lab.Exception;

public class InvalidAuthorIdException extends RuntimeException{
    public InvalidAuthorIdException(Long id) {
        super(String.format("Author with id %d doesn't exist", id));
    }
}
