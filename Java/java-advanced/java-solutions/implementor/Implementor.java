package info.kgeorgiy.ja.bondarev.implementor;

import info.kgeorgiy.java.advanced.implementor.Impler;
import info.kgeorgiy.java.advanced.implementor.ImplerException;
import info.kgeorgiy.java.advanced.implementor.JarImpler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.jar.JarOutputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;

/**
 * Implementation of {@link Impler} and {@link JarImpler} interfaces.
 * @author Nikita Bondarev
 */

public class Implementor implements Impler, JarImpler {

    /**
     * Opening curly brace in generated code.
     */
    private static final String OPEN_BRACKET = "{";
    /**
     * Closing curly brace in generated code.
     */
    private static final String CLOSE_BRACKET = "}";
    /**
     * Semicolon in generated code.
     */
    private static final String SEMICOLON = ";";
    /**
     * Suffix "Impl" for generated classes in generated code.
     */
    private static final String IMPL = "Impl";
    /**
     * Space in generated code.
     */
    private static final String SPACE = " ";
    /**
     * Line separator in generated code.
     */
    private static final String SEPARATOR = System.lineSeparator();

    /**
     * Tabulation symbol in generated code.
     */
    private static final String TAB = "\t";


    /**
     * Main method of Implementor class which generates an implementation of given interface or creates a jar-file with
     * compiled implementation of given interface according to its arguments.
     * For implementation: full_name_of_class, path.
     * For creating a jar-file: -jar, full_name_of_class, file_name.jar"
     * @param args arguments of command line.
     */
    public static void main(String[] args) {
        try {
            Implementor implementor = new Implementor();
            if (args != null && args.length == 2 && args[0] != null & args[1] != null) {
                implementor.implement(Class.forName(args[0]), Path.of(args[1]));
            } else if (args != null && args.length == 3 && args[0] != null && args[1] != null && args[2] != null) {
                implementor.implementJar(Class.forName(args[1]), Path.of(args[2]));
            } else {
                System.err.println("Wrong number of arguments." + SEPARATOR +
                        "Expected 2 for implementation: <full_name_of_class> <path>." + SEPARATOR +
                        "Expected 3 for creating jar-file: -jar <full_name_of_class> <file_name>.jar");
            }
        } catch (ImplerException e) {
            System.err.println("ImplerException occurred: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Cannot find class: " + e.getMessage());
        }

    }

    /**
     * Generate a {@link String} of package of given class.
     * @param clazz class which package we need to get.
     * @return an empty string if given class does not have a package, or {@link String} which contains
     * package of given class
     */

    private String generatePackage(Class<?> clazz) {
        final String packageName = clazz.getPackageName();
        if (packageName.isEmpty()) {
            return "";
        } else {
            return "package " + packageName + SEMICOLON + SEPARATOR;
        }
    }

    /**
     * Generate a {@link String} of heading of given class: package, "public class ", simple name with suffix "Impl",
     * "implements", interfaces.
     * @param clazz class which heading we need to get.
     * @return string of heading of given class with {@link System#lineSeparator()}.
     */

    private String generateHeading(Class<?> clazz) {
        return generatePackage(clazz)
                + "public " + "class " + clazz.getSimpleName() + IMPL + " implements "
                + clazz.getCanonicalName() + SPACE + OPEN_BRACKET + SEPARATOR;
    }

    /**
     * Generate a {@link String} of all {@link Exception exceptions} which can be thrown by given {@link Method}.
     * @param method method which exceptions we need to get.
     * @return an empty string if method does not throw any exceptions, or string of its all exceptions.
     */
    private String generateExceptions(Method method) {
        Class<?>[] exceptionTypes = method.getExceptionTypes();
        if (exceptionTypes.length == 0) {
            return "";
        }
        StringBuilder exceptionsStr = new StringBuilder("throws");
        Arrays.stream(exceptionTypes).forEach(clazz -> exceptionsStr.append(SPACE).append(clazz.getCanonicalName()));
        exceptionsStr.append(SPACE);
        return exceptionsStr.toString();
    }

    /**
     * Generate a surrounded by brackets {@link String} of all {@link Class types} of {@link Parameter parametes}
     * and {@link Parameter#getName() their names}.
     * @param method method which parameters we need to get.
     * @return string of parameters.
     */
    private String generateParameters(Method method) {
        final StringBuilder parametersStr = new StringBuilder("(");
        final Class<?>[] parameterTypes = method.getParameterTypes();
        final Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameterTypes.length; i++) {
            parametersStr.append(parameterTypes[i].getCanonicalName()).append(" ")
                    .append(parameters[i].getName()).append(", ");
        }
        if (parameters.length != 0) {
            parametersStr.delete(parametersStr.length() - 2, parametersStr.length());
        }
        parametersStr.append(") ");
        return parametersStr.toString();
    }

    /**
     * Generate a {@link String} of tabulation symbols repeated given amount of times.
     * @param amount how many tabulation symbols are needed.
     * @return a string of given amount of tabulation symbols.
     */
    private String generateTabulation(int amount) {
        return "\t".repeat(amount);
    }

    /**
     * Generate a {@link String} which contains standard implementation of given {@link Method}.
     * @param method method which implementation we need to get.
     * @return an empty string if return type of given method is void,
     * or a string of "return ", default value of return type, {@value SEMICOLON}.
     */
    private String generateRealisation(Method method) {
        final Class<?> returnType = method.getReturnType();
        if (returnType.equals(void.class)) {
            return "";
        }
        if (returnType.isPrimitive()) {
            return returnType.equals(boolean.class) ? "return false;" : "return 0;";
        } else {
            return "return null;";
        }
    }

    /**
     * Generate a {@link String} of {@link Modifier modifiers} of given {@link Method} without transient and abstract.
     * @param method method which modifiers we need to get.
     * @return a string of modifiers of given method with {@value SPACE} after them.
     */
    private String generateModifiers(Method method) {
        return Modifier.toString(method.getModifiers() & ~Modifier.TRANSIENT & ~Modifier.ABSTRACT) + SPACE;
    }

    /**
     * Generate a {@link String} of the signature of given {@link Method}.
     * <p>
     * Signature consists of tabulation symbol, {@link Implementor#generateModifiers(Method)}, return type, {@value SPACE},
     * {@link Method#getName()}, {@link Implementor#generateParameters(Method)},
     * {@link Implementor#generateExceptions(Method)}}.
     * @param method method which signature we need to get.
     * @return a string of signature of given method.
     */
    private String generateSignature(Method method) {
        return generateTabulation(1) + generateModifiers(method)
                + method.getReturnType().getCanonicalName() + SPACE
                + method.getName() + generateParameters(method) + generateExceptions(method);
    }

    /**
     * Generate a {@link String} of the body of given {@link Method}.
     * Body consists of {@value OPEN_BRACKET}, {@link System#lineSeparator()}, 2 tabs,
     * {@link Implementor#generateRealisation(Method)}, {@link System#lineSeparator()}, tabulation symbol,
     * {@value CLOSE_BRACKET}, {@link System#lineSeparator()}.
     * @param method method which body we need to get.
     * @return a string of the body of given method.
     */
    private String generateBodyOfMethod(Method method) {
        return OPEN_BRACKET + SEPARATOR
                + generateTabulation(2) + generateRealisation(method) + SEPARATOR
                + generateTabulation(1) + CLOSE_BRACKET + SEPARATOR;
    }

    /**
     * Generate a {@link String} of {@link Implementor#generateSignature(Method) the signature}
     * and {@link Implementor#generateBodyOfMethod(Method) the body} of given {@link Method}.
     * @param method method which string representation we need to get.
     * @return a string representation of given method.
     */
    private String generateMethod(Method method) {
        return generateSignature(method) + generateBodyOfMethod(method);
    }

    /**
     * Generate a {@link String} of {@link Implementor#generateMethod(Method) string representations}
     * of all {@link Method methods} in given {@link Class}.
     * @param clazz class which methods string representations we need to get.
     * @return a string of string representations of all methods in given class.
     */
    private String generateMethods(Class<?> clazz) {
        final Method[] methods = clazz.getMethods();
        final StringBuilder methodsStr = new StringBuilder();
        for (Method method : methods) {
            methodsStr.append(generateMethod(method));
        }
        return methodsStr.toString();

    }


    /**
     * Create {@link Path} for implementation of given {@link Class} and all missing directories to its parent.
     * @param root root path.
     * @param clazz class for which implementation we need a path.
     * @return path of implementation of given class.
     * @throws ImplerException if could not create directories.
     */
    private static Path createPath(Path root, Class<?> clazz) throws ImplerException {
        try {
            final Path path = root.resolve(getPath(clazz, ".java"));
            final Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            return path;
        } catch (IOException e) {
            throw new ImplerException("Cannot create directories for implemented class.");
        }
    }

    /**
     * Create a {@link Path} for java-file of implementation of given {@link Class},
     * or for class-file of compiled implementation of given {@link Class}.
     * @param clazz class for which implementation we need a path.
     * @param suffix {@link String suffix} added to the path of implementation of given class.
     * @return {@link Path} for implementation or compiled implementation of given class.
     */
    private static Path getPath(Class<?> clazz, String suffix) {
        return Path.of(clazz.getPackageName().replace('.', '/')
                + '/' + clazz.getSimpleName() + IMPL + suffix);
    }


    /**
     * Produces code implementing class or interface specified by provided {@code token}.
     * <p>
     * Generated class classes name should be same as classes name of the type token with {@code Impl} suffix
     * added. Generated source code should be placed in the correct subdirectory of the specified
     * {@code root} directory and have correct file name.
     * @param token type token to create implementation for.
     * @param root root directory.
     * @throws ImplerException if given class is not an interface or is private.
     */
    @Override
    public void implement(Class<?> token, Path root) throws ImplerException {
        if (!token.isInterface() || Modifier.isPrivate(token.getModifiers())) {
            throw new ImplerException("Cannot implement this class.");
        }
        final Path path = createPath(root, token);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            bufferedWriter.write((generateHeading(token) + generateMethods(token) + CLOSE_BRACKET)
                    .chars().mapToObj(c -> String.format("\\u%04x", c)).collect(Collectors.joining()));
        } catch (IOException e) {
            System.err.println("Cannot create java class.");
        }
    }

    /**
     * Produces <var>.jar</var> file implementing interface specified by provided <var>token</var>.
     * <p>
     * Generated class classes name should be same as classes name of the type token with <var>Impl</var> suffix
     * added.
     * @param token type token to create implementation for.
     * @param jarFile target <var>.jar</var> file.
     * @throws ImplerException if could not create an OutPutStream or copy to jar-file.
     */
    @Override
    public void implementJar(Class<?> token, Path jarFile) throws ImplerException {
        implement(token, Path.of("."));
        compile(token);
        try (JarOutputStream jarOutputStream = new JarOutputStream(Files.newOutputStream(jarFile))) {
            final String fileName = getPath(token, ".class").toString().replace(File.separatorChar,'/');
            jarOutputStream.putNextEntry(new ZipEntry(fileName));
            Files.copy(Path.of(fileName), jarOutputStream);
        } catch (IOException e) {
            throw new ImplerException("Cannot create an OutPutStream or copy to jar-file: " + e);
        }

    }


    /**
     * Compile an implementation of given {@link Class}.
     * @param clazz class which implementation we need to compile.
     * @throws ImplerException if compilation was not successful.
     */
    private static void compile(final Class<?> clazz) throws ImplerException {
        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new ImplerException("Could not find java compiler, include tools.jar to classpath");
        }
        final String classpath = getClassPath(clazz);
        final String[] args = Stream.of("-cp", classpath, getPath(clazz, ".java").toString()).toArray(String[]::new);
        final int exitCode = compiler.run(null, null, null, args);
        if (exitCode != 0) {
            throw new ImplerException("Cannot compile this class: " + classpath);
        }
    }

    /**
     * Create a {@link String} of {@link Path path} to given {@link Class}.
     * @param clazz class which path we need to get.
     * @return a string of path of given class.
     * @throws ImplerException if URI is not correct and could not get a path.
     */
    private static String getClassPath(Class<?> clazz) throws ImplerException {
        try {
            return Path.of(clazz.getProtectionDomain().getCodeSource().getLocation().toURI()).toString();
        } catch (final URISyntaxException e) {
            throw new ImplerException("Cannot get class path: " + e);
        }
    }
}