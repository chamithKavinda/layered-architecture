package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.dto.CustomDTO;


public interface QueryDAO extends SuperDAO {
    void customerOrderDetails(CustomDTO customDTO);
}
