package com.guru.feeds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guru.feeds.model.DataSet;

@Repository
public interface DataSetRepository extends JpaRepository<DataSet, Integer>{

}
