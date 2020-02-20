package com.guru.feeds.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guru.feeds.dto.DataStoreDto;
import com.guru.feeds.model.DataStore;
import com.guru.feeds.repository.DataStoreRepository;

import io.swagger.annotations.Api;
import javassist.NotFoundException;

@RestController
@RequestMapping("/data-stores")
@Api(value = "/data-sets", tags = "data-stores", description = "Operations on Data Store")
public class DataStoreController{
	
	@Autowired
	private DataStoreRepository dataStoreRepository;
	
	@GetMapping
	public ResponseEntity<Object> getDataStores(@RequestParam String tenantId) {
		List<DataStore> dataStores = dataStoreRepository.findBytenantId(tenantId);
		List<DataStoreDto> dataStoreDtos = new ArrayList<DataStoreDto>();
		dataStores.forEach(dataStore -> { dataStoreDtos.add(convertToDto(dataStore)); });
		ResponseEntity<Object> response = new ResponseEntity<>(dataStoreDtos, HttpStatus.OK);;
		return response;
	}
	
	@PostMapping
	public ResponseEntity<Object> createDataStore(@Valid @RequestBody DataStore dataStore) {
		dataStoreRepository.save(dataStore);
		ResponseEntity<Object> response = new ResponseEntity<>(convertToDto(dataStore), HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getDataStore(@PathVariable Integer id, @RequestParam String tenantId) throws NotFoundException {
		DataStore dataStore = dataStoreRepository.findByIdAndTenantId(id, tenantId);
		if (dataStore != null) {
			return new ResponseEntity<>(convertToDto(dataStore), HttpStatus.OK);
		} else {
			throw new NotFoundException("Couldn't find Datastore");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDataStore(@PathVariable Integer id) throws NotFoundException {
		Optional<DataStore> dataStore = dataStoreRepository.findById(id);
		if (dataStore.isPresent()) {
			dataStoreRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			throw new NotFoundException("Couldn't find Datastore");
		}
	}
	
	private DataStoreDto convertToDto(DataStore dataStore) {
		ModelMapper mapper = new ModelMapper();
		DataStoreDto dataStoreDto = mapper.map(dataStore, DataStoreDto.class);
		return dataStoreDto;
	}
}
