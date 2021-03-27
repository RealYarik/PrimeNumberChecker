package com.chalyk.yaroslav.checker.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

public final class FileReader {

    private static final String JAR_SCHEME = "jar";

    private FileReader() {
        throw new IllegalStateException("Utility class");
    }

    public static Stream<String> read(String fileName) {
        fileName = Optional.ofNullable(fileName)
                .orElseThrow(() -> new IllegalArgumentException("File name cannot be null"));
        URL fileUrl = FileReader.class.getClassLoader().getResource(fileName);

        try {
            if (fileUrl == null) {
                throw new FileNotFoundException("Missing file \"" + fileName + "\"");
            }
            return Files.lines(getPathFromUrlByJARScheme(fileUrl));
        } catch (IOException | URISyntaxException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static Path getPathFromUrlByJARScheme(URL fileUrl) throws URISyntaxException, IOException {
        URI uri = fileUrl.toURI();

        if (JAR_SCHEME.equals(uri.getScheme())) {
            for (FileSystemProvider provider : FileSystemProvider.installedProviders()) {
                if (provider.getScheme().equalsIgnoreCase(JAR_SCHEME)) {
                    try {
                        provider.getFileSystem(uri);
                    } catch (FileSystemNotFoundException e) {
                        provider.newFileSystem(uri, Collections.emptyMap());
                    }
                }
            }
        }
        return Paths.get(uri);
    }
}
