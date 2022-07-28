package com.kb.exam.kapuka.domain.search;

import com.kb.exam.kapuka.domain.search.model.RankKeywordVO;

import java.util.List;

public interface KeywordService {

    List<RankKeywordVO> getRankKeyword(int limit);

    void save(String keyword);
}
