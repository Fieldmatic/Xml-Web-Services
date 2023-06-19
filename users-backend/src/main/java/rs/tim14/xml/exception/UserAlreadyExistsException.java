package rs.tim14.xml.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("Korisnik sa datim emailom vec postoji!");
    }
}
