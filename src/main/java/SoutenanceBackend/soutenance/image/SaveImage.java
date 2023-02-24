package SoutenanceBackend.soutenance.image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.web.multipart.MultipartFile;
public class SaveImage {
    public static String localhost = "http://127.0.0.1/";
    public static String serveruser = localhost + "Carriplan/images/";
    public static String userLocation = "C:/xampp/htdocs/Carriplan/images/";
    public static String serverchallenge = localhost + "Carriplan/challenge/";
    public static String challengeLocation = "C:/xampp/htdocs/Carriplan/challenge/";
    public static String serverSolution = localhost + "Carriplan/solution/";
    public static String solutionLocation = "C:/xampp/htdocs/DesCiwara/solution/";
    public static String save(MultipartFile file, String fileName, String folderName) {
        String src = "";
        String server = "";
        String location = "";

        if (folderName.equals("solution")) {
            location = solutionLocation;
            server = serverSolution;
        }else if
        (folderName.equals("challenge")) {
            location = challengeLocation;
            server = serverchallenge;
        } else {
            location = userLocation;
            server = serveruser;
        }

        try {
            Path filePath = Paths.get(location + fileName);

            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.copy(file.getInputStream(), filePath);
                src = server + fileName;
            } else {
                Files.delete(filePath);
                Files.copy(file.getInputStream(), filePath);
                src = server + fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
            src = null;
        }

        return src;
    }
}
