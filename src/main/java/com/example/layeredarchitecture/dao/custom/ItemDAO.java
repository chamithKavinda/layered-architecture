package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<ItemDTO> {


  /*  ArrayList<ItemDTO> getallItem() throws SQLException, ClassNotFoundException ;

     boolean getSaveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException;

     boolean deleteItem(String code) throws SQLException, ClassNotFoundException;

     String generateId() throws SQLException, ClassNotFoundException ;

     boolean existItem(String code) throws SQLException, ClassNotFoundException ;

    ItemDTO searchItem(String s) throws SQLException, ClassNotFoundException ;*/


}
