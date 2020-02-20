package com.guru.feeds.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guru.feeds.dto.ConfigurationDto;
import com.guru.feeds.dto.DataStoreDto;
import com.guru.feeds.model.Configuration;
import com.guru.feeds.model.DataStore;
import com.guru.feeds.repository.ConfigurationRepository;
import com.guru.feeds.repository.DataStoreRepository;

import io.swagger.annotations.Api;
import javassist.NotFoundException;

@RestController
@RequestMapping("/data-stores/{dataStoreId}/configurations")
@Api(value = "/configurations", tags = "configurations", description = "Operations on Configuration")
public class ConfigurationController{
	
	@Autowired
	private DataStoreRepository dataStoreRepository;
	
	@Autowired
	private ConfigurationRepository configurationRepository;
	
	@GetMapping
	@RequestMapping(value = "/{text:[a-z]+{number{[[\\]+")
	public ResponseEntity<Object> getConfigurations(@PathVariable Integer dataStoreId) throws NotFoundException {
		Optional<DataStore> dataStore = dataStoreRepository.findById(dataStoreId);
		if (dataStore.isPresent()) {
			List<Configuration> configurations = configurationRepository.findByDataStoreId(dataStoreId);
			List<ConfigurationDto> configurationDtos = new ArrayList<ConfigurationDto>();
			configurations.forEach(configuration -> {
				ConfigurationDto configurationDto = convertToDto(configuration);
				configurationDto.setDataStoreId(configuration.getDataStore().getId());
				configurationDtos.add(configurationDto);
			});
			return new ResponseEntity<>(configurationDtos, HttpStatus.OK);
		} else {
			throw new NotFoundException("Couldn't find Datastore");
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> postConfiguration(
			@PathVariable Integer dataStoreId, @Valid @RequestBody Configuration configuration) throws NotFoundException {
		Optional<DataStore> dataStore = dataStoreRepository.findById(dataStoreId);
		if (dataStore.isPresent()) {
			configuration.setDataStore(dataStore.get());
			configurationRepository.save(configuration);
			ConfigurationDto configurationDto = convertToDto(configuration);
			configurationDto.setDataStoreId(configuration.getDataStore().getId());
			return new ResponseEntity<>(configurationDto, HttpStatus.CREATED);
		} else {
			throw new NotFoundException("Couldn't find Datastore");
		}
	}
	
	private ConfigurationDto convertToDto(Configuration configuration) {
		ModelMapper mapper = new ModelMapper();
		ConfigurationDto configurationDto = mapper.map(configuration, ConfigurationDto.class);
		return configurationDto;
	}
}
