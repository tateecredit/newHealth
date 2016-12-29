package com.ts.ssm.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ts on 2016/12/27.
 */
@Getter
@Setter
public class Doc {

    private String docId;
    private String docName;
    private String docPosition;
    private String sex;
    private HospAndDept hospAndDept;
}
