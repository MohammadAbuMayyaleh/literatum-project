package com.atypon.literatumproject.backstage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class UnZipSubmissionServiceImpl implements UnZipSubmissionService {

    public void unZipSubmissionFile(String inputPath, String outputPath) {
        File directory = new File(outputPath);
        // create output directory if it doesn't exist
        if (!directory.exists()) directory.mkdirs();
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try (FileInputStream fileInputStream = new FileInputStream(inputPath);
             ZipInputStream zipInputStream = new ZipInputStream(fileInputStream)) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                File newFile = new File(outputPath + File.separator + fileName);
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                int len;
                while ((len = zipInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, len);
                }
                fileOutputStream.close();
                //close this ZipEntry
                zipInputStream.closeEntry();
                zipEntry = zipInputStream.getNextEntry();
            }
            //close last ZipEntry
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
