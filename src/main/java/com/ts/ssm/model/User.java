package com.ts.ssm.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by ts on 2016/12/23.
 */
@Getter
@Setter
public class User {

    private Long id;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String userPwd;
    private String pwdSalt;
    private Date createTime;
    private Date modifyTime;
    private Short isDelete;


}
