package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.ItemBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.custom.impl.ItemDAOImpl;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;


public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);


    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items=itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS=new ArrayList<>();
        for (Item item:items) {
            itemDTOS.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return itemDTOS;
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return itemDAO.save(entity);
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return itemDAO.update(entity);
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateId();
    }

    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException{
        return itemDAO.exist(code);
    }
}
