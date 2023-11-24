package com.henrikolvr.dataanalysis;

import com.henrikolvr.dataanalysis.service.FileAnalysisService;
import com.henrikolvr.dataanalysis.service.WatcherService;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileAnalysisService fileAnalysisService = new FileAnalysisService();
        WatcherService watcherService = new WatcherService(fileAnalysisService);
        fileAnalysisService.generateReport();
        watcherService.startWatching();
    }
}
