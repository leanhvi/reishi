/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.crawler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import reishi.config.ReishiConfig;
import reishi.dataobjects.CrawlerDataObj;
import reishi.http.HttpGet;
import reishi.mysql.CrawlerData;
import reishi.objects.crawler.URLObject;
import reishi.objects.crawler.URLObject.URLState;
import reishi.objects.crawler.URLs;
import reshi.wordsegmenter.WordSegmenter;

/**
 *
 * @author manhc
 */
public class Crawler {
    private CrawlerData crawlerData = new CrawlerData();
    public void crawlerUrl(URLObject url)  {
        url.setState(URLState.YELLOW);
        Document doc;		
        try {
            //URLs.getListURLApproded().add(url);
            String crawledDoc = HttpGet.sentGet(url.getUrl());           
            //doc = Jsoup.connect(url.getUrl()).get();
            doc = Jsoup.parse(crawledDoc);
            URLObject ua = new URLObject();
            ua.setUrl(url.getUrl());
            ua.setState(URLState.GREEN);
            URLs.getListURLApproded().add(ua);
            // get all links
            Elements links = doc.select("a[href]");
            String href = "";
            for (Element link : links) {
                boolean flagStart = false;
                href = link.attr("href");
                if(!href.startsWith("/")) {
                    href = "/" + href;
                }
                URLObject u = new URLObject();
                u.setState(URLState.GREEN);
                if(!href.contains(ReishiConfig.crawler.start)) {
                    u.setUrl(ReishiConfig.crawler.start + href);
                }
                else {
                    u.setUrl(href);
                }
                
                String[] startWithStrings = ReishiConfig.crawler.startWith.split(",");
                for(int i = 0; i < startWithStrings.length; ++i) {
                    if(href.startsWith(startWithStrings[i])) {
                        flagStart = flagStart || true;
                        break;
                    }
                }
                String[] notContainStrings = ReishiConfig.crawler.notContain.split(",");
                for(int i = 0; i < notContainStrings.length; ++i) {
                    if(notContainStrings[i] != null && notContainStrings[i].length() > 0 && href.contains(notContainStrings[i])) {
                        flagStart = flagStart && false;
                        break;
                    }
                }
                if(flagStart && !URLs.approved(ReishiConfig.crawler.start + href)) {
                    URLs.addURL(ReishiConfig.crawler.start + href);
                }			
            }
            
            if(url.getUrl().startsWith(ReishiConfig.crawler.startWithForGetContent)) {
                Elements articles = doc.select(ReishiConfig.crawler.selectContent);
                if(!articles.isEmpty()) {
                    String content = articles.get(0).text();
                    String segmented = WordSegmenter.segmenting(content);
                    //WriteFile.writeToFile("tinhte.txt", segmented);
                    CrawlerDataObj obj = null;
                    obj = new CrawlerDataObj();
                    obj.contents = content;
                    obj.raw = articles.get(0).html();
                    obj.source = "tinhte";
                    obj.url = url.getUrl();
                    obj.wordSegmented = segmented;
                    try {
                        crawlerData.crawlerDataInser(obj);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }            			
            URLs.getListURLs().remove(url);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            url.setState(URLState.GREEN);
        }			
    }
}
