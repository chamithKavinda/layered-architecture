package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;

import java.sql.SQLException;

public interface PlaceOrderDAO extends SuperDAO {

     boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException ;

}
