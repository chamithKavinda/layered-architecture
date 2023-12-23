package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.bo.SuperBO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {

     ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

     boolean save(Item entity) throws SQLException, ClassNotFoundException;

     boolean update(Item entity) throws SQLException, ClassNotFoundException;

     boolean delete(String code) throws SQLException, ClassNotFoundException;

     String generateId() throws SQLException, ClassNotFoundException;

     boolean exist(String code) throws SQLException, ClassNotFoundException;


     }
