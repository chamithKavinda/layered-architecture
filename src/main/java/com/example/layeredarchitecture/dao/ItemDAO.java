package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO {

    ArrayList<ItemDTO> getallItem() throws SQLException, ClassNotFoundException ;

     boolean getSaveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    void updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

     boolean deleteItem(String code) throws SQLException, ClassNotFoundException;

     String generateId() throws SQLException, ClassNotFoundException ;

     boolean existItem(String code) throws SQLException, ClassNotFoundException ;

    ItemDTO searchItem(String s) throws SQLException, ClassNotFoundException ;


}
