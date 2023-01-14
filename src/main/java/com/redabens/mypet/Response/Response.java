package com.redabens.mypet.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Response {
    private Map data;
    private String message;
    private Integer status;
}
