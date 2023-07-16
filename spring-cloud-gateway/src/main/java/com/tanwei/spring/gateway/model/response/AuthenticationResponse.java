package com.tanwei.spring.gateway.model.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author tanwei
 * @date 2023-07-04 16:09
 **/
@Data
@Builder
public class AuthenticationResponse {

    private String token;
}
