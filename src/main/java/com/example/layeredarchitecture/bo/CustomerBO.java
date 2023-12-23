package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {
     boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException;

     ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;

     boolean exist(String id) throws SQLException, ClassNotFoundException;

     boolean delete(String id) throws SQLException, ClassNotFoundException;

     String generateId() throws SQLException, ClassNotFoundException ;

     boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    }
