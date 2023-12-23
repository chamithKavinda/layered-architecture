package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.PlaceOrderBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.custom.PlaceOrderDAO;
import com.example.layeredarchitecture.dao.custom.impl.ItemDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.PlaceOrderDAOImpl;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.dto.OrderDTO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import com.example.layeredarchitecture.entity.Customer;
import com.example.layeredarchitecture.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    OrderDAOImpl orderDAO = (OrderDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    //OrderDAO orderDAO=new OrderDAOImpl();

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    //CustomerDAO customerDAO = new CustomerDAOImpl();

    ItemDAO itemDAO = new ItemDAOImpl();
    PlaceOrderDAO placeOrderDAO = new PlaceOrderDAOImpl();


    @Override
    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        //*Transaction*//*
        Connection connection= null;
       /* try {
            Connection connection= DBConnection.getDbConnection().getConnection();
            boolean isOrderExists = orderDAO.getOrderDetail(orderId);

            if (isOrderExists) {
                return false;
            }*/



            connection.setAutoCommit(false);

            boolean isOrderSaved =orderDAO.saveOrder(new OrderDTO(orderId, orderDate, customerId, null, null));

            if (!isOrderSaved) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailDTO detail : orderDetails) {
                boolean isOrderDetailSaved = placeOrderDAO.saveOrderDetails(detail);

                if (!isOrderDetailSaved) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

//                //Search & Update Item
                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                boolean isUpdated = itemDAO.update(new Item(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));

                if (!isUpdated) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

       /* } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
       // return false;
    }


    public  ItemDTO findItem(String code) {
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

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer=customerDAO.search(id);
        CustomerDTO customerDTO=new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
        return customerDTO;
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
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
        return orderDAO.generateNextOrderId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers=customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS=new ArrayList<>();
        for (Customer customer:customers) {
            customerDTOS.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerDTOS;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items=itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS=new ArrayList<>();
        for (Item item:items) {
            itemDTOS.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return itemDTOS;
    }

}
