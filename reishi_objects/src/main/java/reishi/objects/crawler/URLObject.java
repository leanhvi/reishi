/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.objects.crawler;

import java.util.Comparator;

/**
 *
 * @author manhc
 */
public class URLObject implements Comparator<URLObject> {
    private String url = "";
	
    private URLState state;

    public enum URLState {
            GREEN,	//chưa duyệt
            YELLOW, //đang duyệt
            RED		//đã duyệt
    }

    public String getUrl() {
            return url;
    }

    public void setUrl(String url) {
            this.url = url;
    }

    public URLState getState() {
            return state;
    }

    public void setState(URLState state) {
            this.state = state;
    }


    @Override
    public int compare(URLObject o1, URLObject o2) {		
            if(o1.url.equalsIgnoreCase(o2.url)) {
                    return 0;
            }
            else {
                    return 1;
            }

    }
}
