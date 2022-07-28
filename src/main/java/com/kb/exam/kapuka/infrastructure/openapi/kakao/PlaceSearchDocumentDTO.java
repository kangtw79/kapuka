package com.kb.exam.kapuka.infrastructure.openapi.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceSearchDocumentDTO {
    @JsonProperty("place_name")
    private String name;

    @JsonProperty("road_address_name")
    private String roadAddressName;

    @JsonProperty("address_name")
    private String addressName;
}