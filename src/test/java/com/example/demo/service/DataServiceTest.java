package com.example.demo.service;

import com.example.demo.json.DataRequest;
import com.example.demo.json.DataResponse;
import com.example.demo.enums.Sides;
import com.example.demo.repository.Repository;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataServiceTest {
    @MockBean
    private Repository repository;

    @Autowired
    private DataService dataService;

    @Test
    public void whenCreateIsOkForLeftSideNotExists(){

        final DataRequest dataRequest = FakeJson.generateRequest();
        final DataResponse dataResponse = FakeJson.generateResponse();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(new DataResponse()));
        when(this.repository.save(any(DataResponse.class))).thenReturn(dataResponse);

        final DataResponse response = this.dataService.create(1L, dataRequest, Sides.LEFT);
        assertNotNull(response);

        verify(this.repository, times(1)).findById(anyLong());
        verify(this.repository, times(1)).save(any(DataResponse.class));
    }

    @Test
    public void whenCreateIsOkForRightSideNotExists(){

        final DataRequest dataRequest = FakeJson.generateRequest();
        final DataResponse dataResponse = FakeJson.generateResponse();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(new DataResponse()));
        when(this.repository.save(any(DataResponse.class))).thenReturn(dataResponse);

        final DataResponse response = this.dataService.create(1L, dataRequest, Sides.RIGHT);
        assertNotNull(response);

        verify(this.repository, times(1)).findById(anyLong());
        verify(this.repository, times(1)).save(any(DataResponse.class));
    }

    @Test
    public void whenCreateIsOkForLeftSideExists(){

        final DataRequest dataRequest = FakeJson.generateRequest();
        final DataResponse dataResponse = FakeJson.generateResponse();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(dataResponse));
        when(this.repository.save(any(DataResponse.class))).thenReturn(dataResponse);

        final DataResponse response = this.dataService.create(1L, dataRequest, Sides.LEFT);
        assertNotNull(response);

        verify(this.repository, times(1)).findById(anyLong());
        verify(this.repository, times(1)).save(any(DataResponse.class));
    }

    @Test
    public void whenCreateIsOkForRightSideExists(){

        final DataRequest dataRequest = FakeJson.generateRequest();
        final DataResponse dataResponse = FakeJson.generateResponse();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(dataResponse));
        when(this.repository.save(any(DataResponse.class))).thenReturn(dataResponse);

        final DataResponse response = this.dataService.create(1L, dataRequest, Sides.RIGHT);
        assertNotNull(response);

        verify(this.repository, times(1)).findById(anyLong());
        verify(this.repository, times(1)).save(any(DataResponse.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateAndNotBase64(){

        final DataRequest dataRequest = FakeJson.generateRequest();
        dataRequest.setData("12d'''");

        final DataResponse response = this.dataService.create(1L, dataRequest, Sides.RIGHT);
        assertNull(response);
    }

    @Test(expected = NotFoundException.class)
    public void whenValidateDiffNotFoundId() throws NotFoundException {

        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

        final String response = this.dataService.validateDiff(1L);
        assertNull(response);

        verify(this.repository, times(1)).findById(anyLong());
    }

    @Test
    public void whenValidateDiffHasLeftAndRightEquals() throws NotFoundException {

        final DataResponse dataResponse = FakeJson.generateResponse();
        dataResponse.setLeftData(dataResponse.getRightData());

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(dataResponse));

        final String response = this.dataService.validateDiff(1L);
        assertNotNull(response);

        verify(this.repository, times(1)).findById(anyLong());
    }

    @Test
    public void whenValidateDiffHasDifferentLeftAndRightSize() throws NotFoundException {

        final DataResponse dataResponse = FakeJson.generateResponse();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(dataResponse));

        final String response = this.dataService.validateDiff(1L);
        assertNotNull(response);

        verify(this.repository, times(1)).findById(anyLong());
    }
    @Test
    public void whenValidateDiffHasDifferentLeftAndRightButSameSize() throws NotFoundException {

        final DataResponse dataResponse = FakeJson.generateResponse();
        dataResponse.setRightData("ZXNxdWVyZGK=");

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(dataResponse));

        final String response = this.dataService.validateDiff(1L);
        assertNotNull(response);

        verify(this.repository, times(1)).findById(anyLong());
    }
}
