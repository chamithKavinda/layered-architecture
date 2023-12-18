package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;

public interface OrderDAO {

    String generateNextOrderId() throws SQLException, ClassNotFoundException ;
    boolean getOrderDetail(String orderId) throws SQLException, ClassNotFoundException ;
    boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException ;
}
