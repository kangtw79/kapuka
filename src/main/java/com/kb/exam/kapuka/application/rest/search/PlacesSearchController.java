package com.kb.exam.kapuka.application.rest.search;

import com.kb.exam.kapuka.domain.search.KeywordService;
import com.kb.exam.kapuka.domain.search.PlaceSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/place")
@RequiredArgsConstructor
public class PlacesSearchController {

    private final PlaceSearchService placesService;
    private final KeywordService keywordService;

    @GetMapping("/search")
    public List<String> search(@RequestParam("keyword") String keyword) {
        keywordService.save(keyword);
        return placesService.search(keyword);
    }
}
