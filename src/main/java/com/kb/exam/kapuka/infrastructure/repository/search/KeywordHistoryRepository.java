package com.kb.exam.kapuka.infrastructure.repository.search;

import com.kb.exam.kapuka.domain.search.model.RankKeyword;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface KeywordHistoryRepository extends JpaRepository<KeywordHistory,Long> {

    @Query("SELECT ush.keyword AS keyword, COUNT(ush) AS count FROM KeywordHistory ush GROUP BY ush.keyword ORDER BY COUNT(ush.keyword) DESC")
    List<RankKeyword> findKeywordCountByGroupByKeyword(Pageable pageable);

}
