package com.example.demo.json;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
public class DataResponse {
    @Id
    private Long id;

    @Lob
    private String leftData;

    @Lob
    private String rightData;

    public DataResponse() {
    }

    public DataResponse(Long id, String leftData, String rightData) {
        this.id = id;
        this.leftData = leftData;
        this.rightData = rightData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeftData() {
        return leftData;
    }

    public void setLeftData(String leftData) {
        this.leftData = leftData;
    }

    public String getRightData() {
        return rightData;
    }

    public void setRightData(String rightData) {
        this.rightData = rightData;
    }
}
