package Iconicthon.moadream.domain.Credit.dto.response;

import lombok.Builder;

@Builder
public record EnglishResponse(
        Integer type,
        Integer grade,

        LectureListResponse lectureListResponse
) {
}
