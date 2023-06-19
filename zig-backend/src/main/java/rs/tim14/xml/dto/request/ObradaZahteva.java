package rs.tim14.xml.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "imeSluzbenika",
        "prezimeSluzbenika",
        "odbijen",
        "razlogOdbijanja",
})
public class ObradaZahteva {
    private String id;
    private String imeSluzbenika;
    private String prezimeSluzbenika;
    private boolean odbijen;
    private String razlogOdbijanja;
}
