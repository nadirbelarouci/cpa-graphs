package fr.sorbonne;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    public static Path createNewFile(String name) {
        try {
            Path newPath = Paths.get(name);
            if (!Files.exists(newPath))
                Files.createFile(newPath);
            return newPath;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
