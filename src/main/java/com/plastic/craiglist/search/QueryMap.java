package com.plastic.craiglist.search;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QueryMap {

    public Map<String, String> constructMap(String url) {
        Map<String, String> result = new HashMap<>();

        Document doc;
        try {
            doc = Jsoup.connect(url).get();

            // get all links
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                if(link.attr("href").startsWith("/d")) {
                    result.put(link.text(), link.attr("href"));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
