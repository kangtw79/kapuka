package com.kb.exam.kapuka.domain.search.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RankKeywordVO {
    private String getKeyword;
    private Long getCount;
}
