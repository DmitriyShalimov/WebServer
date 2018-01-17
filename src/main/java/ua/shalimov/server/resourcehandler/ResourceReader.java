package ua.shalimov.server.resourcehandler;

import java.io.*;

public class ResourceReader {
    private String resourcePath;

    public ResourceReader(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public BufferedReader readContent(String url) throws IOException {
        String path =  resourcePath + url;
        return new BufferedReader(new FileReader(path));
    }
}
