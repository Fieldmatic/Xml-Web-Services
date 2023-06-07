package rs.tim14.xml.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.VrstaPriloga;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class UploadFile {

    public String execute(MultipartFile file, VrstaPriloga vrstaPriloga) {
        try {
            String fileName = getFileName(file, vrstaPriloga);
            saveFile("./zig-backend/src/main/resources/z1/" + fileName, file.getBytes());
            return fileName;
        } catch (IOException | NullPointerException exception) {
            System.out.println(exception.getMessage());
            return "";
        }
    }

    public static String getFileName(MultipartFile file, VrstaPriloga vrstaPriloga){
        String folderName;
        switch (vrstaPriloga) {
            case PUNOMOCJE:
                folderName = "punomocje";
                break;
            case SPISAK_ROBE:
                folderName = "spisakRobe";
                break;
            case IZGLED_ZNAKA:
                folderName = "izgledZnaka";
                break;
            case DOKAZ_O_UPLATI:
                folderName = "dokazUplate";
                break;
            case DOKAZ_PRAVA_PRVENSTVA:
                folderName = "dokazPravaPrvenstva";
                break;
            case OPSTI_AKT:
                folderName = "opstiAkt";
                break;
            default:
                folderName = "";
                break;
        }
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return folderName + "/" + (getNextId()) + "." + fileExtension;
    }

    public static long getNextId(){
        return ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
    }

    public static void saveFile(String path, byte[] bytes){
        try {
            File saveFile = new File(path);
            FileOutputStream stream = new FileOutputStream(saveFile);
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
