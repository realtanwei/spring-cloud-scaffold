package com.tanwei.spring.gateway.model.request;

import lombok.Data;

/**
 * @author tanwei
 * @date 2023-07-04 16:09
 **/
@Data
public class AuthenticationRequest {

    private String username;
    private String password;
}
