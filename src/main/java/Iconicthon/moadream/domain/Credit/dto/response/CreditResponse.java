package Iconicthon.moadream.domain.Credit.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CreditResponse (
        @JsonProperty("common_liberal")
        Integer commonLiberal,

        @JsonProperty("basic_liberal")
        Integer basicLiberal,

        @JsonProperty("major_common")
        Integer majorCommon,

        @JsonProperty("major_required")
        Integer majorRequired,

        @JsonProperty("bsm")
        Integer bsm,

        @JsonProperty("average_grade")
        Integer averageGrade,

        @JsonProperty("english_count")
        long englishCount
)
{}
