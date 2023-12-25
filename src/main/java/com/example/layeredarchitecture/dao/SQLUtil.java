package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T execute(String sql, Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        for (int i=0;i< args.length; i++){
            pstm.setObject(i+1,args[i]);
        }
        if (sql.startsWith("SELECT")){
            return (T)pstm.executeQuery();
        }else {
            return (T)(Boolean)(pstm.executeUpdate()>0);
        }
    }
}
