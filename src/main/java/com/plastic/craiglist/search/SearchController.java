package com.plastic.craiglist.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private CriagRequestHelper helper;

    @RequestMapping(name="/search", method=RequestMethod.GET)
    @ResponseBody
    public List<Item> getItems(@RequestParam("key") String keyWord) {
        List<Item> result;
        result = helper.queryCraig(keyWord);
        return result;
    }
}
