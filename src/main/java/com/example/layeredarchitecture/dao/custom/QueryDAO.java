package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.CustomerDTO;

public interface QueryDAO {
    void customerOrderDetails(CustomerDTO customerDTO);
}
