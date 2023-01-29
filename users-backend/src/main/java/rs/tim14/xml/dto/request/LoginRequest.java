package rs.tim14.xml.dto.request;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loginResponse", propOrder = {
        "email",
        "password",
})
@XmlRootElement(name = "loginRequest")
public class LoginRequest {

    @XmlElement(name = "email", required = true)
    private String email;
    @XmlElement(name = "password", required = true)
    private String password;


    public LoginRequest(){};

    public LoginRequest(String email, String password){
        this.email=email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
