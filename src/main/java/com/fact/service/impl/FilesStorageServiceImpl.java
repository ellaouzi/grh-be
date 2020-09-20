package com.fact.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import com.fact.service.FilesStorageService;
import com.fact.util.GlobalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
@Autowired
    GlobalProperties globalProperties;

    @Override
    public void init() {
        try {
                Path root = Paths.get(globalProperties.getUPLOADED_FACTURES());

            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Path root = Paths.get(globalProperties.getUPLOADED_FACTURES());

            // Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            Files.copy(file.getInputStream(),  root.resolve(file.getOriginalFilename()),  StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path root = Paths.get(globalProperties.getUPLOADED_FACTURES());

            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        Path root = Paths.get(globalProperties.getUPLOADED_FACTURES());
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public void deleteFile(String fileName) {
        Path root = Paths.get(globalProperties.getUPLOADED_FACTURES()+"/"+ fileName);
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            Path root = Paths.get(globalProperties.getUPLOADED_FACTURES());
            return Files.walk( root, 1).filter(path -> !path.equals(root)).map(root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}