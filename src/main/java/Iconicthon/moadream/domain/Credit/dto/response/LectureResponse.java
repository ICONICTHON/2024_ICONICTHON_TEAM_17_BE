package Iconicthon.moadream.domain.Credit.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record LectureResponse(
        @JsonProperty("name")
        String name,
        @JsonProperty("credit")
        Integer credit

) {

}
