package lk.ijse.layeredarchitecture.dao.custom;

import lk.ijse.layeredarchitecture.dao.SuperDAO;
import lk.ijse.layeredarchitecture.dto.CustomerDTO;

public interface QuaryDAO extends SuperDAO {

     void CustomerOrderDetails(CustomerDTO customerDTO);


}
