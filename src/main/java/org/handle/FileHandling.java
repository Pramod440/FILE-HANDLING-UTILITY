package org.handle;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandling {
    private static final String FILE_PATH = "sample.txt";

    public static void main(String[] args) {
        try {
            writeFile( "Dear Hiring Team,\n\n" +
                    "I sincerely appreciate the opportunity to intern at your(CODETECH IT Solutions) esteemed organization.\n" +
                    "This experience will help me enhance my skills and gain valuable insights.\n" +
                    "I am so much excited while doing the tasks and learnt some new stuffs during this journey.\n" +
                    "Thank you for this wonderful opportunity.\n\n" +
                    "Best Regards,\n[Pramod]");
            readFile();
            modifyFile("intern", "work as a trainee");
            modifyFile("organization", "company");
            modifyFile("Pramod", "B PRAMODKUMARA");
            readFile(); // Read again to verify modifications
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void writeFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(content);
            System.out.println("File written successfully.");
        }
    }

    private static void readFile() throws IOException {
        System.out.println("\nReading File:");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    private static void modifyFile(String target, String replacement) throws IOException {
        Path path = Paths.get(FILE_PATH);
        List<String> lines = Files.readAllLines(path);

        List<String> modifiedLines = lines.stream()
                .map(line -> line.replaceAll(target, replacement))
                .collect(Collectors.toList());

        Files.write(path, modifiedLines);
        System.out.println("\nFile modified successfully: Replaced '" + target + "' with '" + replacement + "'.");
    }
}