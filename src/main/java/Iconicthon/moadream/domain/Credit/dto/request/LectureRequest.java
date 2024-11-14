package Iconicthon.moadream.domain.Credit.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record LectureRequest (
        @JsonProperty("schedule_id")
        Long scheduleId,

        @JsonProperty("lecture_id")
        Long lectureId
){
}
