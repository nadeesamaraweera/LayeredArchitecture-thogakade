package com.example.layeredarchitecture.dao.custom.Impl;

import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<Dinu> extends SuperDAO {
   /* ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T dto) throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    String genarateId() throws SQLException, ClassNotFoundException;

    public  T search(String id) throws SQLException, ClassNotFoundException ;

    */

    ArrayList<Dinu> getAll() throws SQLException, ClassNotFoundException;
    boolean save(Dinu dto) throws SQLException, ClassNotFoundException;

    boolean update(Dinu dto) throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    String genarateId() throws SQLException, ClassNotFoundException;

    public  Dinu search(String id) throws SQLException, ClassNotFoundException ;


}
