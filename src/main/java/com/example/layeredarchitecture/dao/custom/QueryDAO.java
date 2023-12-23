package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.dto.CustomerDTO;

public interface QueryDAO extends SuperDAO {
    void customerOrderDetails(CustomerDTO customerDTO);
}
