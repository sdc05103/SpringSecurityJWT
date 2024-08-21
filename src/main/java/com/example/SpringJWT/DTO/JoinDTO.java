package com.example.SpringJWT.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDTO {
    private String nickname;
    private String password;
    private String inviterNickname;
}
