package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PlaceOrderDAO extends SuperDAO {

     boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException ;

}
