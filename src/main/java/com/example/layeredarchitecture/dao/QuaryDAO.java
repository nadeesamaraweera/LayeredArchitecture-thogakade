package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;

public interface QuaryDAO extends SuperDAO {

     void CustomerOrderDetails(CustomerDTO customerDTO);
   // void customerOrderDetails(CustomDto customDTO);

}
