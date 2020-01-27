package com.example.demo.controller;

import com.example.demo.enums.Sides;
import com.example.demo.json.DataRequest;
import com.example.demo.json.DataResponse;
import com.example.demo.service.DataService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/v1/diff/{id}")
public class DiffController {

    @Autowired
    private DataService dataService;

    @PutMapping(path = "/left")
    @ApiOperation(value = "Cria ou insere com dado em Left")
    public ResponseEntity<DataResponse> createLeft(
            @ApiParam(value = "Id do documento que será criado ou atualizado", example = "1")
            @PathVariable("id") Long id,
            @ApiParam(value = "Json da requisição que contem o dado a ser salvo")
            @Valid @RequestBody DataRequest request) {

        return ResponseEntity.ok()
                .body(this.dataService.create(id, request, Sides.LEFT));
    }

    @PutMapping(path = "/right")
    @ApiOperation(value = "Cria ou insere com dado em Right")
    public ResponseEntity<DataResponse> createRight(
            @ApiParam(value = "Id do documento que será criado ou atualizado", example = "1")
            @PathVariable Long id,
            @ApiParam(value = "Json da requisição que contem o dado a ser salvo")
            @Valid @RequestBody DataRequest request){

        return ResponseEntity.ok()
                .body(this.dataService.create(id, request, Sides.RIGHT));

    }

    @GetMapping
    @ApiOperation(value = "Valida diferença entre Left e Right")
    public ResponseEntity<String> validateDiff(
            @ApiParam(value = "Id do documento a ser procurado", example = "1")
            @PathVariable Long id) throws NotFoundException {

        return ResponseEntity.ok()
                .body(this.dataService.validateDiff(id));

    }
}