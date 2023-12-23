package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.dto.OrderDTO;

import java.sql.*;

public interface OrderDAO extends SuperDAO {
    String generateNextOrderId() throws SQLException, ClassNotFoundException ;
    boolean getOrderDetail(String orderId) throws SQLException, ClassNotFoundException ;
    boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException ;
}
