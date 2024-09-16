package com.rakesh.amdocs.assignment.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class InvoiceServices {

    private static final int CHUNK_SIZE = 1024 * 1024 * 100; // 100MB chunks (adjust as needed)
    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public void processLargeFileWithFuture(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        long fileSize = Files.size(path);
        long position = 0;
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {

            List<Future<Void>> futures = new ArrayList<>();
            // Split file into chunks
            while (position < fileSize) {
                long remaining = fileSize - position;
                long size = Math.min(CHUNK_SIZE, remaining);

                // Submit chunk to be processed by thread pool
                futures.add(executor.submit(new FileChunkProcessor(fileChannel, position, size)));
                position += size;
            }
            // Wait for all threads to complete
            for (Future<Void> future : futures) {
                future.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    public void processLargeFileWithCompletableFuture(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        long fileSize = Files.size(path);
        long position = 0;
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
            List<CompletableFuture> completableFutureList = new ArrayList<>();
            // Split file into chunks
            while (position < fileSize) {
                long remaining = fileSize - position;
                long size = Math.min(CHUNK_SIZE, remaining);
                long positionLocal = position;
                // Submit chunk to be processed by thread pool
                completableFutureList.add(CompletableFuture.supplyAsync(() -> new FileChunkProcessor(fileChannel,
                        positionLocal, size), executor));
                position += size;
            }
            // Wait for all threads to complete
            completableFutureList.forEach(CompletableFuture::join);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
