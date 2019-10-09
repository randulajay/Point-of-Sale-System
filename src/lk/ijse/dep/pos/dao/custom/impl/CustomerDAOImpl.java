package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudUtil;
import lk.ijse.dep.pos.dao.custom.CustomerDAO;
import lk.ijse.dep.pos.entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public String getLastCustomerId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT customerId FROM Customer ORDER BY customerId DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public List<Customer> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");
        List<Customer> customers = new ArrayList<>();
        while (rst.next()) {
            customers.add(new Customer(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)));
        }
        return customers;
    }

    @Override
    public Customer find(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer WHERE customerId=?", s);
        if (rst.next()) {
            return new Customer(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3));
        }
        return null;
    }

    @Override
    public boolean save(Customer entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?)", entity.getCustomerId(), entity.getName(), entity.getAddress());
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return CrudUtil.execute("UPDATE Customer SET name=?, address=? WHERE customerId=?", customer.getName(), customer.getAddress(), customer.getCustomerId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM Customer WHERE customerId=?", s);
    }
}
