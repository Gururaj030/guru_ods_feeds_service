package com.guru.feeds.dto;

import java.util.List;

import com.guru.feeds.model.DataSet;

public class DataSets {
	
	private List<DataSet> items;
	
	private Integer totalResults;

	public DataSets(List<DataSet> items, Integer totalResults) {
		super();
		this.items = items;
		this.totalResults = totalResults;
	}

	public List<DataSet> getItems() {
		return items;
	}

	public void setItems(List<DataSet> items) {
		this.items = items;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}
}
