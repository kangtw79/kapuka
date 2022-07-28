package com.kb.exam.kapuka.infrastructure.repository.search;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "keyword_histories", indexes = {@Index(columnList = "createdAt DESC"),
                                          @Index(columnList = "keyword")})
public class KeywordHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String keyword;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column
    private String createdBy;
}
