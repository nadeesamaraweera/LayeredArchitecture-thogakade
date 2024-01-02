package com.example.layeredarchitecture.dao.custom.Impl;

import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.SQLException;

public interface OrderDAO extends SuperDAO {
     String generateOID() throws SQLException, ClassNotFoundException;

      boolean existOrder(String orderId) throws SQLException, ClassNotFoundException;

      boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;


     }
