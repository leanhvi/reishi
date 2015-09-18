/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.dataobjects;

/**
 *
 * @author manhc
 */
public class CrawlerDataObj {
    public String source;
    public String contents;
    public String url;
    public String wordSegmented;
    public String raw;
    public int id;
    
    public CrawlerDataObj() {
        id = 0;
        source = "";
        contents = "";
        url = "";
        wordSegmented = "";
        raw = "";        
    }
}
