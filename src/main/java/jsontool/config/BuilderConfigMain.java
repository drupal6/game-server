package jsontool.config;

import java.io.File;
import java.io.IOException;

import jsontool.common.FileUtil;


public class BuilderConfigMain {
	
	private static final String excelName = "test.xlsx";
	private static final String beanPacketPath = "game/data/jsondata/bean";
	private static final String providerPacketPath = "game/data/jsondata/provider";
	private static final String excepPath = "D:/work/workspace/game-server/excel";
	private static final String basePath = "D:/work/workspace/game-server/src/main/java";
	
	
	private static final String jsonPath = "D:/work/workspace/game-server/src/main/java";
	private static final String jsonPacketPath = "jsontool/json/node";
	private static final String configFile = "jsontool/json/Config.java";
	
	public static void main(String[] args) throws IOException {
		String configName = excelName.substring(0, excelName.length() - 5);
		
		StringBuffer classbuffer = BuilderConfigBean.build(excepPath + File.separator + excelName, excelName, beanPacketPath);
		FileUtil.createClass(basePath, beanPacketPath, configName, classbuffer);
		
		StringBuffer providerbuffer = BuilderConfigProvider.creatConfigProvider(basePath, providerPacketPath, beanPacketPath, configName);
		FileUtil.createProvider(basePath, providerPacketPath, configName, providerbuffer);
		
		StringBuffer jsonbuffer = BuilderConfigJson.creatConfigProvider(beanPacketPath, configName);
		FileUtil.createJson(jsonPath, jsonPacketPath, configName, jsonbuffer);
		
		String configString = BuilderConfig.builder(jsonPath, configFile, configName);
		FileUtil.createConfig(jsonPath, configFile, configString);
	}

}
