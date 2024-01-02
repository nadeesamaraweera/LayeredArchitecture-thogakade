package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.custom.Impl.ItemDAO;
import com.example.layeredarchitecture.dao.custom.ItemDAOImpl;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO=new ItemDAOImpl();
    @Override

    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException{
        return itemDAO.getAll();
    }

    public boolean save(Item dto) throws SQLException, ClassNotFoundException{
        return itemDAO.save(dto);
    }

    public boolean update(Item item) throws SQLException, ClassNotFoundException{
        return itemDAO.update(item);
    }

    public boolean exist(String code) throws SQLException, ClassNotFoundException{
        return itemDAO.exist(code);
    }

    public  boolean delete(String code) throws SQLException, ClassNotFoundException{
        return itemDAO.delete(code);
    }

    public String genarateId() throws SQLException, ClassNotFoundException{
        return  itemDAO.genarateId();
    }

    public Item search(String code) throws SQLException, ClassNotFoundException{
        return itemDAO.search(code);
    }

}
