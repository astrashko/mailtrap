package com.railsware.mailtrap.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Base64;

public class Utils {
    public static String imageToBase64(File file) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(file);
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public static File loadFile(String fileName) throws URISyntaxException {
        URL resource = Utils.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found!");
        } else {
            return new File(resource.toURI());
        }
    }
}
