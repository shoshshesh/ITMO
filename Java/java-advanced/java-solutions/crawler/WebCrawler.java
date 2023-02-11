package info.kgeorgiy.ja.bondarev.crawler;

import info.kgeorgiy.java.advanced.crawler.Crawler;
import info.kgeorgiy.java.advanced.crawler.Document;
import info.kgeorgiy.java.advanced.crawler.Downloader;
import info.kgeorgiy.java.advanced.crawler.Result;
import info.kgeorgiy.java.advanced.crawler.CachingDownloader;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * Implementation of {@link Crawler} interface. Concurrently crawls websites with bfs algorithm.
 * @author Nikita Bondarev
 */
public class WebCrawler implements Crawler {

    private final ExecutorService downloadingsManager;
    private final ExecutorService extractionsManager;
    private final Downloader downloader;

    /**
     * Constructor for creating instances of {@link WebCrawler}.
     * @param downloader {@link Downloader} for downloading {@link Document} of website.
     * @param downloaders max number of concurrently downloading pages.
     * @param extractors max number of pages which links are concurrently being extracted.
     * @param perHost max number of pages concurrently downloading from one host (ignored in this version).
     */
    public WebCrawler(Downloader downloader, int downloaders, int extractors, int perHost) {
        this.downloader = downloader;
        this.downloadingsManager = Executors.newFixedThreadPool(downloaders);
        this.extractionsManager = Executors.newFixedThreadPool(extractors);
    }


    @Override
    public Result download(String url, int depth) {
        final ConcurrentHashMap<String, IOException> errors = new ConcurrentHashMap<>();
        final Set<String> downloaded = ConcurrentHashMap.newKeySet();
        // :NOTE: ArrayDeque
        Set<String> queue = ConcurrentHashMap.newKeySet();
        queue.add(url);
        while (depth > 0) {
            final Phaser counter = new Phaser(1);
            final Set<String> nextQueue = ConcurrentHashMap.newKeySet();
            for (String nextUrl : queue) {
                parallelDownloading(nextUrl, depth, downloaded, nextQueue, errors, counter);
            }
            counter.arriveAndAwaitAdvance();
            queue = nextQueue;
            depth--;
        }
        downloaded.removeAll(errors.keySet());
        return new Result(new ArrayList<>(downloaded), errors);
    }

    private void parallelDownloading(String url, int depth, Set<String> downloaded,
                                     Set<String> queue, Map<String, IOException> errors, Phaser counter) {
        counter.register();
        downloadingsManager.submit(() -> {
            try {
                Document document = downloader.download(url);
                downloaded.add(url);
                if (depth == 1) {
                    return;
                }
                counter.register();
                extractionsManager.submit(() -> {
                    try {
                        for (String nextUrl : document.extractLinks()) {
                            if (downloaded.add(nextUrl)) {
                                queue.add(nextUrl);
                            }
                        }
                    } catch (IOException e) {
                        errors.put(url, e);
                    } finally {
                        counter.arrive();
                    }
                });
            } catch (IOException e) {
                errors.put(url, e);
            } finally {
                counter.arrive();
            }
        });
    }

    @Override
    public void close() {
        closeService(downloadingsManager);
        closeService(extractionsManager);
    }

    private void closeService(ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
                System.out.println("Service termination is taking too much time. It's forcibly stopped.");
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waiting for termination of this service: " + e.getMessage());
        }
        executorService.shutdownNow();
    }

    /**
     * Main method of {@link WebCrawler} which crawls websites starting with given url.
     * Arguments: url is required; depth, downloads, extractors, perHost are optional (if absent, replaced with 1).
     * @param args arguments of command line.
     */
    public static void main(String[] args) {
        if (args == null || args.length == 0 || args.length > 5) {
            System.err.println("Wrong number of arguments. Expected: <url> depth? downloads? extractors? perHost?." +
                    "<url> is required, others are optional and will be replaced with the default value (1) in case" +
                    " of their absence.");
            return;
        }

        final WebCrawler webCrawler;
        final int[] parsedArgs;

        try {
            parsedArgs = parseArgs(args);
            webCrawler = new WebCrawler(new CachingDownloader(),
                    parsedArgs[2], parsedArgs[3], parsedArgs[4]);

        } catch (IOException ioException) {
            System.err.println("Cannot create an instance of CachingDownloader.");
            return;
        } catch (NumberFormatException e) {
            System.err.println("Expected an integer value." + e.getMessage());
            return;
        }

        final Result result = webCrawler.download(args[0], parsedArgs[1]);

        System.out.println(result.getDownloaded().isEmpty() ? "" : "Downloaded:");
        for (String downloadedUrl : result.getDownloaded()) {
            System.out.println(downloadedUrl);
        }

        System.out.println(result.getErrors().isEmpty() ? "" : "Errors:");
        for (Map.Entry<String, IOException> entry : result.getErrors().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getMessage());
        }
        webCrawler.close();
    }

    private static int[] parseArgs(String[] args) {
        final int DEFAULT_VALUE = 1;
        final int[] parsedArgs = new int[5];
        for (int i = 1; i < 5; i++) {
            parsedArgs[i] = args.length > i ? Integer.parseInt(args[i]) : DEFAULT_VALUE;
        }
        return parsedArgs;
    }
}