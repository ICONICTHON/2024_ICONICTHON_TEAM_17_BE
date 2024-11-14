package Iconicthon.moadream.domain.Credit.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CreditResponse (
        @JsonProperty("common_liberal")
        Long commonLiberal,

        @JsonProperty("basic_liberal")
        Long basicLiberal,

        @JsonProperty("major_common")
        Long majorCommon,

        @JsonProperty("major_required")
        Long majorRequired,

        @JsonProperty("bsm")
        Long bsm
)
{}
