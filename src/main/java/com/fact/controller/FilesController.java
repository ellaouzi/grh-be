package com.fact.controller;

import com.fact.dto.FileInfo;
import com.fact.dto.ResponseMessage;
import com.fact.service.FilesStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.naming.SizeLimitExceededException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@CrossOrigin("http://localhost:4200")
public class FilesController {

    @Autowired
    FilesStorageService storageService;

    private DataSize maxFileSize = DataSize.ofKilobytes(1000);
    // @RolesAllowed({ MENU_ADMIN_CMDB_MODEL_MANAGEMENT })
    @PostMapping("/upload")
    public  ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @RequestParam("fileName") String fileName,
                                                       @RequestParam("numero") String numero,
                                                       @RequestParam("lotId") String lotId,
                                                       @RequestParam("factureId") String factureId,
                                                       @RequestParam("contratId") String contratId,
                                                       @RequestParam("datePvRecep") String datePvRecep,
                                                       @RequestParam("designation") String designation,
                                                       @RequestParam("montant") String montant)
            throws SizeLimitExceededException {
        String message = "";

        return ResponseEntity.status(HttpStatus.OK).body(message);


    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
            return new FileInfo(Long.valueOf(0), filename, url,"DESIGNATION", true);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }
    @GetMapping("/filesOfContrat/{contratId}")
    public ResponseEntity<List<FileInfo>> getListFilesOfContrat(@PathVariable("contratId") Long contratId) {
        List<FileInfo> fileInfos = new ArrayList<>();

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


}