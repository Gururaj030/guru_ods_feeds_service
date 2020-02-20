package com.guru.feeds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guru.feeds.model.Configuration;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {
	List<Configuration> findByDataStoreId(Integer dataStoreId);
}
