package rs.tim14.xml.dto.response;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loginResponse", propOrder = {
        "email",
        "role",
        "ime",
        "prezime"
})
@XmlRootElement(name = "loginResponse")
public class LoginResponse {

    @XmlElement(name = "email", required = true)
    private String email;
    @XmlElement(name = "role", required = true)
    private String role;

    @XmlElement(name = "ime", required = true)
    private String ime;

    @XmlElement(name = "prezime", required = true)
    private String prezime;


    public LoginResponse() {
    }

    ;

    public LoginResponse(String email, String role, String ime, String prezime) {
        this.email = email;
        this.role = role;
        this.ime = ime;
        this.prezime = prezime;
    }
}