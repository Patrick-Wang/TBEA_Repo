package com.tbea.ic.operation.controller.servlet.account;

import com.util.tools.Pair;
import com.util.tools.WatchDirectory;
import com.util.tools.xml.Loop;
import com.util.tools.xml.XmlWalker;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.util.*;
import java.util.regex.Pattern;

public class OpenResources {

    private List<Pattern> urlPatterns = Collections.synchronizedList(new ArrayList<Pattern>());

    private void watch(String fName) throws Exception {

        File fRes = new File(fName);
        String parentPath = fRes.getParentFile().getAbsolutePath();
        Iterator<Pair<WatchEvent.Kind<Path>, String>> it = new WatchDirectory(Paths.get(parentPath), false);

        while (it.hasNext()) {
            Pair<WatchEvent.Kind<Path>, String> p = it.next();
            File f = new File(p.getSecond());
            if (p.getFirst() != StandardWatchEventKinds.ENTRY_DELETE) {
                if (f.getAbsolutePath().equals(fRes.getAbsolutePath())) {
                    scan(fRes);
                }
            }
        }
    }

    void scan(File file) throws Exception {
        urlPatterns.clear();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        Map<String, DataSource> dsMapNew = new HashMap<String, DataSource>();
        XmlWalker.eachChildren(doc.getDocumentElement(), new Loop() {

            @Override
            public void on(Element e) throws Exception {
                urlPatterns.add(Pattern.compile(e.getAttribute("url")));
            }

        });
    }

    public OpenResources(String path) throws Exception {
        scan(new File(path));
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        watch(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public boolean validate(String url){
        for (Pattern pattern : urlPatterns){
            if (pattern.matcher(url).find()){
                return true;
            }
        }
        return false;
    }
}
