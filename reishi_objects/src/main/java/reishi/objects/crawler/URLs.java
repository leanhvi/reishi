/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reishi.objects.crawler;

import java.util.ArrayList;
import java.util.List;
import reishi.objects.crawler.URLObject.URLState;

/**
 *
 * @author manhc
 */
public class URLs {
    private static List<URLObject> listURLs = new ArrayList<URLObject>();

    private static List<URLObject> listURLApproded = new ArrayList<URLObject>();

    public static void addURL(String url) {

        URLObject urlObject = new URLObject();
        urlObject.setUrl(url);
        urlObject.setState(URLState.GREEN);
        boolean status = false;
        for (URLObject urlObject2 : listURLs) {
                if(urlObject2.getUrl().equalsIgnoreCase(urlObject.getUrl())){
                        status = true;
                        break;
                }
        }
        if (!status) {
                listURLs.add(urlObject);			
        }
    }

    public static boolean approved(String url) {
        boolean status = false;
        for (URLObject urlObject2 : listURLApproded) {
                if(urlObject2.getUrl().equalsIgnoreCase(url)){
                        status = true;
                        break;
                }
        }
        return status;
    }

    public static List<URLObject> getListURLs() {
        return listURLs;
    }

    public static void setListURLs(List<URLObject> listURLs) {
        URLs.listURLs = listURLs;
    }

    public static List<URLObject> getListURLApproded() {
        return listURLApproded;
    }

    public static void setListURLApproded(List<URLObject> listURLApproded) {
        URLs.listURLApproded = listURLApproded;
    }
}
