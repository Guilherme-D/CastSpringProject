package com.example.demo.service;

import com.example.demo.enums.Sides;
import com.example.demo.json.DataRequest;
import com.example.demo.json.DataResponse;
import javassist.NotFoundException;


public interface DataService {

    DataResponse create(Long id, DataRequest request, Sides side);

    String validateDiff(Long id) throws NotFoundException;

}
