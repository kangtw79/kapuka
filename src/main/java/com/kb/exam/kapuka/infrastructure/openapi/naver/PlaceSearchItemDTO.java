package com.kb.exam.kapuka.infrastructure.openapi.naver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceSearchItemDTO {
    private String address;
    private String roadAddress;
    private String title;
}