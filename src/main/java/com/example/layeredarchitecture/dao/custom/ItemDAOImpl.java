package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.custom.Impl.ItemDAO;
import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.entity.Customer;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {

       // Connection connection = DBConnection.getDbConnection().getConnection();
       // Statement stm = connection.createStatement();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");
        ArrayList<Item> allItem = new ArrayList<>();

        while (rst.next()) {
            Item item =new Item(

                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand")
            );
            allItem.add(item);
        }
        return allItem;


    }
    @Override

    public boolean save(Item dto) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code,description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
        pstm.setString(1, dto.getCode());
        pstm.setString(2, dto.getDescription());
        pstm.setBigDecimal(3, dto.getUnitPrice());
        pstm.setInt(4, dto.getQtyOnHand());
        return pstm.executeUpdate()> 0;

        */
        return SQLUtil.execute("INSERT INTO Item (code,description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());



    }
    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
      /*  Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");

        pstm.setString(1, dto.getDescription());
        pstm.setBigDecimal(2, dto.getUnitPrice());
        pstm.setInt(3, dto.getQtyOnHand());
        pstm.setString(4, dto.getCode());
        return pstm.executeUpdate()> 0;

       */
        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());
    }

    @Override

    public boolean exist(String code) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();

        */
        ResultSet rst =SQLUtil.execute("SELECT code FROM Item WHERE code=?",code);
        return rst.next();

    }
    @Override

    public  boolean delete(String code) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, code);
       return pstm.executeUpdate()>0;

        */
        return SQLUtil.execute("DELETE FROM Item WHERE code=?",code);
    }

    @Override

    public String genarateId() throws SQLException, ClassNotFoundException {
        //Connection connection = DBConnection.getDbConnection().getConnection();
       // ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
         if (rst.next()) {
                String id = rst.getString("code");
                int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
                return String.format("I00-%03d", newItemId);
            } else {
                return "I00-001";
            }
    }

    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, code+"");
        ResultSet rst = pstm.executeQuery();
        rst.next();
        return new ItemDTO(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));

        */
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?",code);
        if(rst.next()){
            return new Item(rst.getString(1),
                    // return new CustomerDTO((rst.getString(1)
                    rst.getString(2),
                    rst.getBigDecimal(3),
                    rst.getInt(4));
        }
        return null;

    }
}


