package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.CustomerBO;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO=new CustomerDAOImpl();

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException{
        return customerDAO.save(entity);
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException{
        return customerDAO.exist(id);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException{
        return customerDAO.delete(id);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateId();
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException{
        return customerDAO.update(entity);
    }


}
