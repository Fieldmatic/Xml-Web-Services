package rs.tim14.xml.exception;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "exceptionResponse")
public class ExceptionResponse {
    private String message;


    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}