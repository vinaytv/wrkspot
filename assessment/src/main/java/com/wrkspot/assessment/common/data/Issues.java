package com.wrkspot.assessment.common.data;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Issues {

    private String type;
    private String detail;
    private String in;
    private String param;


    public Issues(String type, String detail, String in, String param) {
        this.detail = detail;
        this.in = in;
        this.param = param;
        this.type = type;
    }

}
