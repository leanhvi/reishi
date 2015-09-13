/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author manhc
 */
public final class ReishiConfig {
    public static String mySqlConnection;
    public static String wordSegmentModelsDir;
    public static CrawlerConfig crawler;
    
    final static Logger logger = Logger.getLogger(ReishiConfig.class);
    
    public void wordSegmentConfig() {
        Properties prop = new Properties();
        String proFileName = "config.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(proFileName);
        
        if(inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException ex) {
                logger.error(ex, ex);
                //Logger.getLogger(RabbitmqConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                throw new FileNotFoundException("property file '" + proFileName + "' not found in the classpath");
            } catch (FileNotFoundException ex) {
                logger.error(ex, ex);
                //Logger.getLogger(RabbitmqConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        ReishiConfig.wordSegmentModelsDir = prop.getProperty("wordsegment.models.dir", "models\\jvnsegmenter");
    }
    
    public void crawlerConfig() {
        Properties prop = new Properties();
        String proFileName = "config.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(proFileName);
        
        if(inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException ex) {
                logger.error(ex, ex);
                //Logger.getLogger(RabbitmqConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                throw new FileNotFoundException("property file '" + proFileName + "' not found in the classpath");
            } catch (FileNotFoundException ex) {
                logger.error(ex, ex);
                //Logger.getLogger(RabbitmqConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        ReishiConfig.crawler = new CrawlerConfig();
        ReishiConfig.crawler.url = prop.getProperty("crawler.url", "https://www.tinhte.vn");
        ReishiConfig.crawler.start = prop.getProperty("crawler.start", "https://www.tinhte.vn/");
        ReishiConfig.crawler.startWith = prop.getProperty("crawler.startWith", "threads,/?wpage");
        ReishiConfig.crawler.notContain = prop.getProperty("crawler.notContains", "page-,#post-");
        ReishiConfig.crawler.startWithForGetContent = prop.getProperty("crawler.startWithForGetContent", "https://www.tinhte.vn/threads/");
        ReishiConfig.crawler.selectContent = prop.getProperty("crawler.selectContent", "div.messageContent");
    }
    
    public void mySqlConfig() {
        Properties prop = new Properties();
        String proFileName = "config.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(proFileName);
        
        if(inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException ex) {
                logger.error(ex, ex);
                //Logger.getLogger(RabbitmqConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                throw new FileNotFoundException("property file '" + proFileName + "' not found in the classpath");
            } catch (FileNotFoundException ex) {
                logger.error(ex, ex);
                //Logger.getLogger(RabbitmqConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        ReishiConfig.mySqlConnection = prop.getProperty("mysql.connection", "jdbc:mysql://localhost:3306/crawler?useUnicode=true&characterEncoding=UTF-8&user=foobar&password=foobar");
    }
    
    public ReishiConfig() {        
        mySqlConfig();
        wordSegmentConfig();
        crawlerConfig();
    }
}
