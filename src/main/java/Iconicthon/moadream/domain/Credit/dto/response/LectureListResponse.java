package Iconicthon.moadream.domain.Credit.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record LectureListResponse(
        @JsonProperty("lectures")
        List<LectureResponse> lectureResponses
){
}
