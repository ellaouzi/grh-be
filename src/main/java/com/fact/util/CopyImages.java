package com.fact.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CopyImages {

    public static void main (String[] args) throws IOException {
        String targetPhotos ="C://Utilisateur//surface//Documents//ONSSA//PHOTOS//";

       for(String img: allImageIn("C://Utilisateur//surface//Documents//ONSSA//LISTE ET PHOTOS DU PERSONNEL DR1//"))
       {
          System.out.println(img);
//           System.out.println(img.substring(img.lastIndexOf("\\")));
          System.out.println(img.substring(img.lastIndexOf("\\")).replaceAll("[^0-9]", "")+".jpg");

           String imgpath = targetPhotos + img.substring(img.lastIndexOf("\\")).replaceAll("[^0-9]", "")+".png";
           System.out.println(imgpath);
           OutputStream os = new FileOutputStream(imgpath);
           Files.copy(Paths.get(img), os);
           os.close();

       }
    }
    static List<String> allImageIn(String folder){
        int count =1;
        List<String> images = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(folder))) {

            List<String> result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".JPG"))
                   // .filter(f -> f.endsWith(".jpg"))
                    .collect(Collectors.toList());
            result.forEach(images::add);
        } catch (IOException e) {
            e.printStackTrace();
         }
        return images;
    }
}
