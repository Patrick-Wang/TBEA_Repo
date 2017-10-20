package com.util.tools;

import java.io.File;
import java.net.URISyntaxException;

public class PathUtil {
    public static String getClassRoot() throws URISyntaxException {
        File f = new File(PathUtil.class.getClassLoader().getResource("").toURI());
        return f.getAbsolutePath() + "/";
    }
}
