package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.custom.CustomerDAOImpl;
import com.example.layeredarchitecture.dao.custom.Impl.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.Impl.ItemDAO;
import com.example.layeredarchitecture.dao.custom.Impl.OrderDAO;
import com.example.layeredarchitecture.dao.custom.Impl.OrderDetailDAO;
import com.example.layeredarchitecture.dao.custom.ItemDAOImpl;
import com.example.layeredarchitecture.dao.custom.OrderDAOImpl;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAOImpl;
import com.example.layeredarchitecture.entity.Customer;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO{
    CustomerDAO customerDAO = new CustomerDAOImpl();
    ItemDAO itemDAO = new ItemDAOImpl();
    OrderDAO orderDAO = new OrderDAOImpl();

    OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();




    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;
       /* try {
          connection = DBConnection.getDbConnection().getConnection();

            if order id already exist

            check order id already exist or not
            boolean b1 = orderDAO.existOrder(orderId);
            if(b1){
               return false;
             }*/

           connection.setAutoCommit(false);

           boolean b2 = orderDAO.saveOrder(new OrderDTO(orderId,orderDate,customerId,null,null));
            if (!b2){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
            System.out.println("orderSaved");
                    for (OrderDetailDTO detail : orderDetails) {
                    boolean b3 = orderDetailDAO.saveOrderDetails(detail);
                    if(!b3){
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
                       System.out.println("orderDetailsSaved");
                //Search & Update Item
                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                boolean b = itemDAO.update(new Item(item.getCode(), item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));

                if (!b){
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
                        System.out.println("Items Updated");
            }

            connection.commit();
            connection.setAutoCommit(true);
            System.out.println("Transaction completed");
            return true;

      /* } catch (SQLException throwables) {

            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;*/
    }


    @Override
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        //return itemDAO.search(code);
        Item item = itemDAO.search(code);
        return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(), item.getQtyOnHand());

    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public String generateOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateOID();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException{
       // ArrayList<Customer> all = customerDAO.getAll();
       // return null;
        ArrayList<Customer> customers=customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS=new ArrayList<>();
        for (Customer customer:customers) {
            customerDTOS.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerDTOS;

    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
       // return itemDAO.getAll();
        ArrayList<Item> items=itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS=new ArrayList<>();
        for (Item item:items) {
            itemDTOS.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return itemDTOS;
    }



    public ItemDTO findItem(String code) {
        /*try {
            return itemDAO.search(code);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;*/
        try {
            Item item = itemDAO.search(code);
            return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(), item.getQtyOnHand());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
