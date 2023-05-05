package rs.tim14.xml.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "prijave")
@Getter
@Setter
@NoArgsConstructor
public class AllResponse {
    @XmlElementWrapper(name="lista_prijava")
    @XmlElement(name="prijava")
    Collection<PrijavaResponse> prijave;
}
