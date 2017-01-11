package game.data.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class ConfigResource extends Properties{

	private static final long serialVersionUID = 2577898294845656423L;
	
	private static ConfigResource instance = new ConfigResource();
	
	public static ConfigResource getInst() {
		return instance;
	}
	
	public void load(File file) throws IOException {
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(new FileInputStream(file));
			this.load(isr);
		} finally {
			try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loadResource(String name) throws IOException {
		URL configFileUrl = ClassLoader.getSystemResource(name);
		if(configFileUrl == null) {
			throw new IOException("sytem resource not found +" + name);
		}
		try {
			this.load(new File(configFileUrl.toURI()));
		} catch (URISyntaxException e) {
			throw new IOException(e.getMessage());
		}
	}
	
	public void loadFile(String name) throws IOException, URISyntaxException {
		this.load(new File(name));
	}
	
	@Override
	public String getProperty(String key) {
		String ret = super.getProperty(key);
		return ret == null ? null : ret.trim();
	}
	
	public String getString(String key) {
		return getProperty(key);
	}
	
	public int getInt(String key) {
		String ret = getProperty(key);
		return ret == null ? 0 : Integer.parseInt(ret);
	}
	
	public long getLong(String key) {
		String ret = getProperty(key);
		return ret == null ? 0l : Long.parseLong(ret);
	}
	
	public double getDouble(String key) {
		String ret = getProperty(key);
		return ret == null ? 0d : Double.parseDouble(ret);
	}
	
	public static void main(String[] args) throws IOException {
		ConfigResource.getInst().loadResource("config.properties");
		System.out.println(ConfigResource.getInst().getString("test"));
	}
}
