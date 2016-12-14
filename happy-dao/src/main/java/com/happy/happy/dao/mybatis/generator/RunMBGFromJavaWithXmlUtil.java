package com.happy.happy.dao.mybatis.generator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class RunMBGFromJavaWithXmlUtil {

	public static void main(String[] args)
			throws SQLException, IOException, InterruptedException, XMLParserException, InvalidConfigurationException {
		
		System.out.println("start generator ...");
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File(getConfigFilePath());
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		System.out.println("end generator ...");
	}

	private static String getConfigFilePath() throws IOException{
		return new File("").getCanonicalPath() + "/src/main/resources/mybatis/generator/generatorConfig.xml";
	}
}
