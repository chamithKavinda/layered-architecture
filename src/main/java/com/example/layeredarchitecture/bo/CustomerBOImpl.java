package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO=new CustomerDAOImpl();

    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException{
        return customerDAO.save(dto);
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
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
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException{
        return customerDAO.update(dto);
    }


}
