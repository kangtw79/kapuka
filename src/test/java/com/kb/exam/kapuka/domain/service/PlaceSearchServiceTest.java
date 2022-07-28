package com.kb.exam.kapuka.domain.service;

import com.kb.exam.kapuka.domain.search.model.RankKeywordVO;
import com.kb.exam.kapuka.domain.search.model.SearchHistoryVO;

import java.util.List;

public interface PlaceSearchServiceTest {

    List<String> search(String username, String keyword);

    List<RankKeywordVO> getRankKeyword();

    List<SearchHistoryVO> getSearchHistory(String username);
}
