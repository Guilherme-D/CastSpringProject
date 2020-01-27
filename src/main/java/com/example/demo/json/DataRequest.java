package com.example.demo.json;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class DataRequest {

    @ApiModelProperty(notes = "Dado em base64 que será salvo em Left ou Right", example = "ZXNxdWVyZGE=")
    @NotNull(message = "Campo data não pode ser nulo")
    @NotEmpty(message = "Campo data não pode ser vazio")
    private String data;

    public DataRequest() {
    }

    public DataRequest(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
