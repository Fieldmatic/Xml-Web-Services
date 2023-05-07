package rs.tim14.xml.rdf;

public class FusekiRepository {

    public static String getRdfString(String id) throws Exception {
        return FusekiReader.getRdfString(id);
    }

    public static String getJsonString(String id) throws Exception {
        return FusekiReader.getJsonString(id);
    }
}
