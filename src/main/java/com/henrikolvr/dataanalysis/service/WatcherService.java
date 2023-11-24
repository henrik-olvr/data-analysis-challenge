package com.henrikolvr.dataanalysis.service;

import java.io.IOException;
import java.nio.file.*;

public class WatcherService {
    private Path filePathIn;
    private FileAnalysisService fileAnalysisService;
    private WatchService watchService;
    private WatchKey watchKey;

    public WatcherService(FileAnalysisService fileAnalysisService) throws IOException {
        this.fileAnalysisService = fileAnalysisService;
        this.watchService = FileSystems.getDefault().newWatchService();
        this.filePathIn = Paths.get(System.getProperty("user.home") + "/data/in/");
        this.watchKey = filePathIn.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

    }

    public void startWatching() throws InterruptedException {
        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                fileAnalysisService.generateReport();
            }
            key.reset();
        }
    }

}
