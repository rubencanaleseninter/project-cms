package com.cms.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@ComponentScan
public class CmsApplication implements CommandLineRunner {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${cms.jdbc.import.route}")
	private String route;

	@Value("${cms.jdbc.import}")
	private String imported;

	private Log log = LogFactory.getLog(getClass());

	public CmsApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);
	}

	@Override
	public void run(String... args) throws NullPointerException {
		if (imported.equalsIgnoreCase("true")) {
			Path path = Paths.get(route);
			try (BufferedReader bufferedReader = Files.newBufferedReader(path, Charset.forName("UTF-8"))) {
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jdbcTemplate.execute(line);
				}
			} catch (IOException ex) {

			}
		}
		log.info("Total permissions: "
				+ jdbcTemplate.queryForObject("SELECT Count(*) FROM `permission`;", Integer.class));
	}

}
