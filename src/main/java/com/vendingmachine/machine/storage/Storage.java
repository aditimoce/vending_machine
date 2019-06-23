package com.vendingmachine.machine.storage;

import java.util.Map;
import java.util.Queue;

import com.vendingmachine.machine.product.Product;

public class Storage {
	private Map<Integer, Queue<Product>> storageMap;

	public Map<Integer, Queue<Product>> getStorageMap() {
		return storageMap;
	}

	public void setStorageMap(Map<Integer, Queue<Product>> storageMap) {
		this.storageMap = storageMap;
	}

}
