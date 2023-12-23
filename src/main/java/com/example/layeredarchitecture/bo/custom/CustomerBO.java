package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.bo.SuperBO;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
     boolean save(Customer entity) throws SQLException, ClassNotFoundException;

     ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException;

     boolean exist(String id) throws SQLException, ClassNotFoundException;

     boolean delete(String id) throws SQLException, ClassNotFoundException;

     String generateId() throws SQLException, ClassNotFoundException ;

     boolean update(Customer entity) throws SQLException, ClassNotFoundException;

    }
