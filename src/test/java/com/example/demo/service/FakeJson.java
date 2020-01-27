package com.example.demo.service;

import com.example.demo.json.DataRequest;
import com.example.demo.json.DataResponse;

public class FakeJson {

    public static DataRequest generateRequest(){
        return new DataRequest("ZXNxdWVyZGE=");
    }

    public static DataResponse generateResponse(){
        return new DataResponse(1L,
                "ZXNxdWVyZGE=",
                "ZGlyZWl0YQ==");
    }
}
