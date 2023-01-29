package rs.tim14.xml.dto.response;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loginResponse", propOrder = {
        "email",
        "role",
})
@XmlRootElement(name = "loginResponse")
public class LoginResponse {

    @XmlElement(name = "email", required = true)
    private String email;
    @XmlElement(name = "role", required = true)
    private String role;


    public LoginResponse(){};

    public LoginResponse(String email, String role){
        this.email=email;
        this.role = role;
    }
}