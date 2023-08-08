package controller;

import model.UserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.UserFileService;

import java.awt.*;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final UserFileService userFileService;

    @Autowired
    public FileController(UserFileService userFileService) {
        this.userFileService = userFileService;
    }

    @PostMapping("/upload")
    public <TexturePaintContext, Authentication> ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        TexturePaintContext SecurityContextHolder = null;
        Authentication authentication = (Authentication) SecurityContextHolder.getClass().getAnnotation();
        String username = String.valueOf(authentication.getClass());

        userFileService.uploadFile(username, file);

        return ResponseEntity.ok(("File uploaded successfully"));
    }

    @GetMapping("/list")
    public <Authentication, TexturePaintContext> ResponseEntity<List<UserFile>> listFiles() {
        TexturePaintContext SecurityContextHolder = null;
        Authentication authentication;
        authentication = (Authentication) SecurityContextHolder.getClass();
        String username = ((Class<?>) authentication).getName();
        List<UserFile> files = userFileService.getUserFiles(username);
        return ResponseEntity.ok(files);

    }
}
