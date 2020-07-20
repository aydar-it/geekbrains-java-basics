package com.geekbrains.java.lesson15.homework;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainApp {

    //task1
    public static long countOfSubstrings(String filePath, byte[] bytes) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        long counter = 0;
        int bytesRead = channel.read(buffer);
        while (bytesRead != -1) {
            buffer.flip();
            boolean flag = true;
            int i = 0;
            if (buffer.limit() == buffer.capacity()) {
                while (buffer.hasRemaining()) {
                    if (bytes[i] != buffer.get()) {
                        flag = false;
                        break;
                    }
                    i++;
                }
            } else {
                flag = false;
            }

            if (flag) {
                counter++;
                buffer.clear();
            } else {
                buffer.rewind();
                buffer.get();
                buffer.compact();
            }
            bytesRead = channel.read(buffer);
        }
        return counter;
    }

    //task2
    private static final Pattern fileNamePattern = Pattern.compile(".*\\.txt");
    public static Path mergeFilesInDirectory(Path directory) throws IOException {
        Path result = Files.createFile(Paths.get(directory.toString() + "TextContent.txt"));
        Files.walkFileTree(directory, new SimpleFileVisitor<>(){
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if (dir.equals(directory)) {
                    return FileVisitResult.CONTINUE;
                }
                return FileVisitResult.SKIP_SUBTREE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (fileNamePattern.matcher(file.toString()).find()) {
                    Files.write(result, Files.readAllBytes(file), StandardOpenOption.APPEND);
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return result;
    }

    //task3
    public static List<Path> getBigFiles(String directory, int byteSize) throws IOException {
        List<Path> paths = new ArrayList<>();
        Path path = Paths.get(directory);
        Files.walkFileTree(path, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (attrs.size() >= byteSize) {
                    paths.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return paths;
    }
}
