package com.xml.frame.report.component.manager.loader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent.Kind;
import java.util.Iterator;

import com.util.tools.Pair;
import com.util.tools.WatchDirectory;

public class WatchConfigLoader extends AbstractConfigLoader {

	public WatchConfigLoader(String resPath) {
		super(resPath);
		// TODO Auto-generated constructor stub
	}

	private void watch(Path dir) throws IOException {
		Iterator<Pair<Kind<Path>, String>> it = new WatchDirectory(dir, true);
		while (it.hasNext()) {
			Pair<Kind<Path>, String> p = it.next();
			File f = new File(p.getSecond());
			if (p.getFirst() == StandardWatchEventKinds.ENTRY_DELETE) {
				if (f.isDirectory()) {
					notifyOnDeleteFolder(f.getAbsolutePath());
				} else {
					notifyOnDeleteFile(f.getAbsolutePath());
				}
			} else {
				scan(new File(resPath));
			}
		}
	}
	
	public void load() {
		Path dir = Paths.get(resPath);
		scan(dir.toFile());
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						watch(dir);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public void pause(){
		
	}
}
