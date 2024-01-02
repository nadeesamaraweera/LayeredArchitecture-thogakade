package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO {
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException;

    public boolean save(Item dto) throws SQLException, ClassNotFoundException;

    public boolean update(Item dto) throws SQLException, ClassNotFoundException;

    public boolean exist(String code) throws SQLException, ClassNotFoundException;

    public  boolean delete(String code) throws SQLException, ClassNotFoundException;

    public String genarateId() throws SQLException, ClassNotFoundException;
}
