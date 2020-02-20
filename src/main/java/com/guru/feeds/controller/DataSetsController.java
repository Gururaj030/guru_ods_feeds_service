package com.guru.feeds.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.guru.feeds.dto.DataSetRequestDto;
import com.guru.feeds.dto.DataSets;
import com.guru.feeds.model.DataSet;
import com.guru.feeds.repository.DataSetRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import javassist.NotFoundException;

@RestController
@RequestMapping("/data-sets")
@Api(value = "/data-sets", tags = "data-sets", description = "Operations on DataSets")
public class DataSetsController{
	
	@Autowired
	private DataSetRepository dataSetRepository;
	
	@ApiOperation(value = "Get All DataSets", response = DataSets.class)
	@GetMapping
	public ResponseEntity<Object> getDataSets() {
		List<DataSet> dataSetList = dataSetRepository.findAll();
		DataSets dataSets = new DataSets(dataSetList, dataSetList.size());
		ResponseEntity<Object> response = new ResponseEntity<>(dataSets, HttpStatus.OK);;
		return response;
	}
	
	@ApiOperation(value = "Create DataSet", response = DataSet.class)
	@PostMapping
	public ResponseEntity<Object> createDataSet(@Valid @RequestBody DataSetRequestDto dataSetRequest) {
		DataSet dataSet = convertToEntity(dataSetRequest);
		dataSetRepository.save(dataSet);
		ResponseEntity<Object> response = new ResponseEntity<>(dataSet, HttpStatus.CREATED);
		return response;
	}
	
	@ApiOperation(value = "Get DataSet by ID", response = DataSet.class)
	@GetMapping("/{id}")
	public ResponseEntity<Object> getDataSet(@PathVariable Integer id) throws NotFoundException {
		Optional<DataSet> dataSet = dataSetRepository.findById(id);
		if (dataSetRepository.findById(id).isPresent()) {
			return new ResponseEntity<>(dataSet.get(), HttpStatus.OK);
		} else {
			throw new NotFoundException("Couldn't find DataSet");
		}
	}
	
	@ApiOperation(value = "Update DataSet by ID")
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateDataSet(@PathVariable Integer id, @Valid @RequestBody DataSet dataSet) throws NotFoundException {
		if (dataSetRepository.findById(id).isPresent()) {
			dataSet.setId(id);
			dataSetRepository.save(dataSet);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			throw new NotFoundException("Couldn't find DataSet");
		}
	}
	
	@ApiOperation(value = "Delete DataSet by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDataSet(@PathVariable Integer id) throws NotFoundException {
		if (dataSetRepository.findById(id).isPresent()) {
			dataSetRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			throw new NotFoundException("Couldn't find DataSet");
		}
	}
	
	public DataSet convertToEntity(DataSetRequestDto dataSetRequestDto) {
		ModelMapper mapper = new ModelMapper();
		DataSet dataSet = mapper.map(dataSetRequestDto, DataSet.class);
		return dataSet;
	}
}
