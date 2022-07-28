package com.kb.exam.kapuka.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kb.exam.kapuka.application.rest.search.PlacesSearchController;
import com.kb.exam.kapuka.domain.search.KeywordService;
import com.kb.exam.kapuka.domain.search.PlaceSearchService;
import com.kb.exam.kapuka.domain.search.model.RankKeywordVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class PlacesSearchControllerTest {

    private MockMvc mvc;
    private PlaceSearchService placeSearchService;
    private KeywordService keywordService;

    @BeforeEach
    void setUp() {
        placeSearchService = mock(PlaceSearchService.class);
        mvc = MockMvcBuilders.standaloneSetup(new PlacesSearchController(placeSearchService,keywordService))
                .setMessageConverters(new MappingJackson2HttpMessageConverter(new ObjectMapper()))
                .build();
    }

    @Test
    public void rankkeywords_success() throws Exception {
        doReturn(Arrays.asList(new RankKeywordVO("TEST",1L))).when(keywordService).getRankKeyword(anyInt());
        mvc.perform(get("/v1/keyword/top-ranks").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].getKeyword").value("TEST"))
                .andExpect(jsonPath("$[0].getCount").value(1L));
    }

}
