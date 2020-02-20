package com.guru.feeds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guru.feeds.model.DataStore;

@Repository
public interface DataStoreRepository extends JpaRepository<DataStore, Integer>{

  List<DataStore> findBytenantId(String tenantId);
  
  DataStore findByIdAndTenantId(Integer id, String tenantId);
}
