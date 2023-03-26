package generator;

import grammar.Grammar;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AbstractGenerator {
    protected final Path BASE_PATH = Path.of("src/main/java/my_gen");
    protected final String nameOfClass;
    protected final Grammar grammar;
    protected final Path path;

    public AbstractGenerator(Grammar grammar, String nameOfClass) {
        this.grammar = grammar;
        this.nameOfClass = nameOfClass;
        this.path = Path.of(BASE_PATH + "/" + grammar.getName() + "/" + nameOfClass + ".java");
    }

    protected String generatePackage() {
        return "package my_gen." + grammar.getName() + ";\n\n";
    }

    protected abstract String generateImports();

    protected String generateStartOfClass() {
        return "public class " + nameOfClass + " {\n\n";
    }

    protected abstract String generateFields();

    protected abstract String generateConstructors();

    protected abstract String generateMethods();

    public void generateClass() {
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            System.err.println("Cannot create directories for the file " + nameOfClass + ".java: " + e.getMessage());
        }
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            bufferedWriter.write(
                    generatePackage()
                            + generateImports()
                            + generateStartOfClass()
                            + generateFields()
                            + generateConstructors()
                            + generateMethods()
                            + "}"
            );
        } catch (IOException e) {
            System.err.println("Cannot create file " + nameOfClass + ".java: " + e.getMessage());
        }
    }
}
