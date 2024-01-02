package com.example.layeredarchitecture.dao.custom.Impl;

import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;

public interface OrderDetailDAO extends SuperDAO {
    public boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException ;
}
