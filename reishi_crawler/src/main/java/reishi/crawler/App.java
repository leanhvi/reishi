/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.crawler;

import org.apache.log4j.Logger;
import reishi.config.ReishiConfig;
import reishi.objects.crawler.URLObject;
import reishi.objects.crawler.URLObject.URLState;
import reishi.objects.crawler.URLs;

/**
 *
 * @author manhc
 */
public class App {
    public static Logger logger = Logger.getLogger(App.class);
    
    public static void main( String[] args ){     
        ReishiConfig config = new ReishiConfig();
    	int sum = 0;
        URLs.addURL(config.crawler.url);
        URLObject u = new URLObject();
        u.setUrl(config.crawler.url);
        u.setState(URLState.GREEN);
        URLs.getListURLApproded().add(u);
        boolean status = false;
        while(!URLs.getListURLs().isEmpty()) {
            Crawler crawler = new Crawler();
            if(status) {
                System.out.println(URLs.getListURLs().get(0).getUrl());     
                logger.info(URLs.getListURLs().get(0).getUrl());
                crawler.crawlerUrl(URLs.getListURLs().get(0));
            }
            else {
                int count = URLs.getListURLs().size() - 1;
                System.out.println(URLs.getListURLs().get(count).getUrl());
                logger.info(URLs.getListURLs().get(count).getUrl());                
                crawler.crawlerUrl(URLs.getListURLs().get(count));
            }
            status = !status;
            ++sum;
        }
    }
}
