package com.kb.exam.kapuka.application.rest.search;

import com.kb.exam.kapuka.domain.search.KeywordService;
import com.kb.exam.kapuka.domain.search.model.RankKeywordVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/keyword")
@RequiredArgsConstructor
public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping("/top-ranks")
    public List<RankKeywordVO> getRankKeyword(@RequestParam(required = false,defaultValue = "10") int limit) {
        return keywordService.getRankKeyword(limit);
    }
}
