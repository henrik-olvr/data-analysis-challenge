package com.henrikolvr.dataanalysis.dao;

import com.henrikolvr.dataanalysis.exception.UnableToReadFileException;
import com.henrikolvr.dataanalysis.exception.UnableToWriteFileException;
import com.henrikolvr.dataanalysis.exception.FolderNotFoundException;
import com.henrikolvr.dataanalysis.model.Report;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDAO {

    private static final String userRootPath = System.getProperty("user.home");
    private static final String inFile = userRootPath + "/data/in/";
    private static final String outFile = userRootPath + "/data/out/";

    public List<String> getAllFilesFromFolder() {
        try {
            Stream<Path> walkFiles = Files.walk(Paths.get(inFile));
            return walkFiles.filter(Files::isRegularFile).map(m -> m.toString()).collect(Collectors.toList());
        } catch (Exception e) {
            throw new FolderNotFoundException("Error when searching for folder!");
        }
    }

    public List<String> readFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (Exception e) {
            throw new UnableToReadFileException("Unable to read file!");
        }
    }

    public void writeFile(Report report) {
        String[] newFileName = report.getFileName().split(".dat");
        try (FileWriter fw = new FileWriter(outFile+newFileName[0]+".done.dat"); PrintWriter pw = new PrintWriter(fw)){
            pw.write(report.toString());
        } catch (IOException e) {
            throw new UnableToWriteFileException("Unable to write file!");
        }
    }
}
