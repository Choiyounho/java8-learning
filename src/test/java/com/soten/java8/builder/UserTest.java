package com.soten.java8.builder;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void user() {
        User user = User.builder()
                .id(1)
                .name("최윤호")
                .email("asdf@adsf.com")
                .password("aaaa")
                .rePassword("aaaa")
                .nickname("soten")
                .address("도농")
                .phoneNumber("0000")
                .build();

        System.out.println(user.getName());
    }
}
