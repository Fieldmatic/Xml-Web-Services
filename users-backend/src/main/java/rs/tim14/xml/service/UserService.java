package rs.tim14.xml.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.tim14.xml.dto.request.LoginRequest;
import rs.tim14.xml.dto.response.LoginResponse;
import rs.tim14.xml.exception.InvalidCredentialsException;
import rs.tim14.xml.exception.UserAlreadyExistsException;
import rs.tim14.xml.exception.UserNotFoundException;
import rs.tim14.xml.model.User;
import rs.tim14.xml.repository.Repo;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final Repo repo;

    public User register(User user) throws Exception {
        User existingUser = this.repo.getUserByEmail(user.getEmail());
        if (!Objects.isNull(existingUser))
            throw new UserAlreadyExistsException();
        repo.save("/db/users", user.getEmail().concat(".xml"), user, "./data/user.xsd");
        return user;
    }

    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        User existingUser = this.repo.getUserByEmail(loginRequest.getEmail());
        if (Objects.isNull(existingUser)) throw new UserNotFoundException();
        else if (existingUser.getPassword().equals(loginRequest.getPassword())) {
            return new LoginResponse(existingUser.getEmail(), existingUser.getRole().value());
        } else {
            throw new InvalidCredentialsException();
        }
    }
}
