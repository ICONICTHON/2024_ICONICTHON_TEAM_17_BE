package Iconicthon.moadream.domain.Credit.dto.request;

import lombok.Builder;

@Builder
public record CreditRequest(
        Integer type,
        Boolean major
){

}
