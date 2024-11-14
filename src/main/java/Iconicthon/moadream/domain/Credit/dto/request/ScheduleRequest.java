package Iconicthon.moadream.domain.Credit.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ScheduleRequest(
  String name,
  @JsonProperty("user_id")
  Long userId
) {
}
