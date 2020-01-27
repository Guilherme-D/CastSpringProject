package com.example.demo.service.impl;

import com.example.demo.enums.Sides;
import com.example.demo.json.DataRequest;
import com.example.demo.json.DataResponse;
import com.example.demo.repository.Repository;
import com.example.demo.service.DataService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private Repository repository;

    @Override
    public DataResponse create(Long id, DataRequest request, Sides side) {
        if(id <= 0){
            throw new IllegalArgumentException("Id informado deve ser maior que 0");
        }

        if(!this.isBase64(request.getData())){
            throw new IllegalArgumentException("Dado informado não está criptografado com base64");
        }

        DataResponse response = this.repository.findById(id).orElseGet(DataResponse::new);

        response.setId(id);

        if (side.equals(Sides.LEFT)) {
            response.setLeftData(request.getData());
        } else{
            response.setRightData(request.getData());
        }

        return this.repository.save(response);
    }

    @Override
    public String validateDiff(Long id) throws NotFoundException {
        String result = "";

        DataResponse dataResponse = this.repository.findById(id).orElseThrow(() -> new NotFoundException("Não foi encontrado dados para o id informado."));

        byte[] leftData = Base64.getDecoder().decode(dataResponse.getLeftData().toLowerCase());
        byte[] rightData = Base64.getDecoder().decode(dataResponse.getRightData().toLowerCase());

        if(Arrays.equals(leftData,rightData)){
            result = "Documentos "+id+" idênticos.";

        }else if(leftData.length != rightData.length){
            result = "Documentos "+id+" com tamanhos diferentes.";

        }else{
            for (int i = 0; i < leftData.length; i++) {
                int position = leftData[i] ^ rightData[i];
                if (position != 0){
                    result = "Os dados se diferem na posição "+i;
                }
            }
        }

        return result;
    }

    private Boolean isBase64(String base64){

        try{
            Base64.getDecoder().decode(base64);
            return Boolean.TRUE;
        }catch (IllegalArgumentException e){
            return Boolean.FALSE;
        }
    }
}
