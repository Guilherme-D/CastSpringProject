package com.example.demo.repository;

import com.example.demo.json.DataResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<DataResponse,Long> {

}
