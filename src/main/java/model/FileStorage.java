package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;



public class FileStorage {

    private Map<String, List<UserFile>> userFiles = new HashMap<>();

    public void uploadFile(String username, MultipartFile file) {
        UserFile userFile = new UserFile();
        userFile.setFileName(file.getOriginalFilename());
        userFile.setFileType(file.getContentType());
        userFile.setSize(file.getSize());
        userFile.setUploadTime(LocalDateTime.now());

        if (!userFiles.containsKey(username)) {
            userFiles.put(username, new ArrayList<>());
        }
        userFiles.get(username).add(userFile);
    }

    public List<UserFile> getUserFiles(String username) {
        return userFiles.getOrDefault(username, new ArrayList<>());
    }
}

