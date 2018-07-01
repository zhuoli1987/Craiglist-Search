package com.plastic.craiglist.search;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Component
public class CriagRequestHelper {
    private static final String SITE_URL = "https://toronto.craigslist.ca";
    public static Map<String, String> searchKeyWorkMap = null;

    @PostConstruct
    private void init() {
        QueryMap m = new QueryMap();
        searchKeyWorkMap = m.constructMap(SITE_URL);
    }

    public List<Item> queryCraig(String keyWord) {
        List<Item> result = new ArrayList<>();

        String additionUrl = searchKeyWorkMap.get(keyWord);

        try {
            URL feedUrl = new URL(SITE_URL + additionUrl + "?format=rss");

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            result = RssToList(feed);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }

        return result;
    }

    private List<Item> RssToList(SyndFeed feed) {
        List<Item> result = new ArrayList<>();

        List<SyndEntry> list = feed.getEntries();
        for(SyndEntry entry : list) {
            String title = entry.getTitle();
            Date date = entry.getPublishedDate();

            Item item = new Item(title, date);
            result.add(item);
        }

        return result;
    }
}
