package com.geekbrains.java.lesson14.homework;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) throws Exception{
        File file = new File("src/main/java/com/geekbrains/java/lesson14/homework/task1.txt");
        System.out.println(countOfSubstrings(file, "ava".getBytes()));

        File file1 = mergeFilesInDirectory(new File("src/main/java/com/geekbrains/java/lesson14/homework"));
        BufferedReader reader = new BufferedReader(new FileReader(file1, StandardCharsets.US_ASCII));
        reader.lines().forEach(e -> System.out.println(e));

        File file2 = new File("src/main/java/com/geekbrains/java/lesson14/homework/123");
        deleteFile(file2);
    }

    //task1
    public static long countOfSubstrings(File file, byte[] bytes) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            byte[] src = in.readAllBytes();
            long counter = 0;
            for (int i = 0; i < src.length; i++) {
                boolean flag = true;
                int m = i;
                for (int j = 0; j < bytes.length; j++) {
                    if (m >= src.length || src[m] != bytes[j]) {
                        flag = false;
                        break;
                    }
                    m++;
                }
                if (flag) {
                    counter++;
                }
            }
            return counter;
        }
    }

    //task2
    private static final Pattern fileNamePattern = Pattern.compile(".*\\.txt");
    public static File mergeFilesInDirectory(File directory) throws IOException {
        if (!directory.exists() || !directory.isDirectory()) {
            throw new RuntimeException("Wrong input");
        }

        File result = new File(directory.getAbsolutePath() + "/" + directory.getName() + "TextContent.txt");
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(result))) {
            List<File> files = Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                    .filter(e -> e.isFile())
                    .filter(e -> fileNamePattern.matcher(e.getName()).find())
                    .collect(Collectors.toList());
            for (File file : files) {
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                    out.write(in.readAllBytes());
                }
            }
        }
        return result;
    }

    //task3
    public static void deleteFile(File file) {
        if (!file.exists())
            return;

        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                deleteFile(f);
            }
        }
        file.delete();
    }
}
