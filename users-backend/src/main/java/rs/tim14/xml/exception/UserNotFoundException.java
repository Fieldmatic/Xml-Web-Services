package rs.tim14.xml.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Korisnik sa datim emailom ne postoji!");
    }
}
