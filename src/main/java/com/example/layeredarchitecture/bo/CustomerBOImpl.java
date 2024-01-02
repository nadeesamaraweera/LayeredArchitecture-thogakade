package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.custom.CustomerDAOImpl;
import com.example.layeredarchitecture.dao.custom.Impl.CustomerDAO;
import com.example.layeredarchitecture.entity.Customer;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO=new CustomerDAOImpl();
    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException{
        //customer business logic example
       // return customerDAO.save(dto);
        return customerDAO.save(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
       // return customerDAO.getAll();
        ArrayList<Customer> customers=customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS=new ArrayList<>();
        for (Customer customer:customers) {
            customerDTOS.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerDTOS;
    }

    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException{
       // return customerDAO.update(dto);
        return customerDAO.update(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException{
        return customerDAO.exist(id);
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException{
        return customerDAO.delete(id);
    }

    public String genarateId() throws SQLException, ClassNotFoundException{
        return customerDAO.genarateId();
    }

    public  CustomerDTO search(String id) throws SQLException, ClassNotFoundException{
       // return customerDAO.search(id);
        Customer customer=customerDAO.search(id);
        CustomerDTO customerDTO=new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
        return customerDTO;
    }


}
