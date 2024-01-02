package lk.ijse.layeredarchitecture.dao.custom;

import lk.ijse.layeredarchitecture.dao.CrudDAO;
import lk.ijse.layeredarchitecture.dao.SuperDAO;
import lk.ijse.layeredarchitecture.dto.OrderDTO;
import lk.ijse.layeredarchitecture.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {

     String generateID() throws SQLException, ClassNotFoundException;
}
