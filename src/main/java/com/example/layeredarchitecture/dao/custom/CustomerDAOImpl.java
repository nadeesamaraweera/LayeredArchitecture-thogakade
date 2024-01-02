package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.custom.Impl.CustomerDAO;
import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.entity.Customer;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override

    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
       // Connection connection = DBConnection.getDbConnection().getConnection();
      //  Statement stm = connection.createStatement();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> allCustomer = new ArrayList<>();


        while (rst.next()) {
           // CustomerDTO customerDTO = new CustomerDTO(
            Customer entity = new Customer(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address")
                    );
          //  allCustomer.add(customerDTO);
            allCustomer.add(entity);

        }
        return allCustomer;

    }
@Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        return pstm.executeUpdate()> 0;

        */
    return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",entity.getId(),entity.getName(),entity.getAddress());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getId());
        pstm.executeUpdate();

        */
       return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",entity.getName(),entity.getAddress(),entity.getId());

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);*/
        ResultSet rst =SQLUtil.execute("SELECT id FROM Customer WHERE id=?",id);
        return rst.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, id);

        */
      // return pstm.executeUpdate()>0;
        return SQLUtil.execute("DELETE FROM Customer WHERE id=?",id);
    }

@Override
    public String genarateId() throws SQLException, ClassNotFoundException {
        //Connection connection = DBConnection.getDbConnection().getConnection();
        //ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
    ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
    if (rst.next()) {
        String id = rst.getString("id");
        int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
        return String.format("C00-%03d", newCustomerId);
    } else {
        return "C00-001";
    }




    }
    @Override
    public  Customer search(String id) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setString(1, id + "");
        ResultSet rst = pstm.executeQuery();
        rst.next();


        return new CustomerDTO(id + "",rst.getString("name"),rst.getString("address"));

        */
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?",id);
        if(rst.next()){
            return new Customer(rst.getString(1),
           // return new CustomerDTO((rst.getString(1)
                    rst.getString(2),
                    rst.getString(3));
        }
        return null;
    }
}
