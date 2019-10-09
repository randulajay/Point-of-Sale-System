package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudUtil;
import lk.ijse.dep.pos.dao.custom.OrderDAO;
import lk.ijse.dep.pos.entity.Order;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public int getLastOrderId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT id FROM `Order` ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getInt(1);
        } else {
            return 0;
        }
    }

    @Override
    public boolean existsByCustomerId(String customerId) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM `Order` WHERE customerId=?", customerId);
        return rst.next();
    }

    @Override
    public List<Order> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM `Order`");
        List<Order> orders = new ArrayList<>();
        while (rst.next()) {
            orders.add(new Order(rst.getInt(1),
                    rst.getDate(2),
                    rst.getString(3)));
        }
        return orders;
    }

    @Override
    public Order find(Integer orderId) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM `Order` WHERE id=?", orderId);
        if (rst.next()) {
            return new Order(rst.getInt(1),
                    rst.getDate(2),
                    rst.getString(3));
        }
        return null;
    }

    @Override
    public boolean save(Order order) throws Exception {
        return CrudUtil.execute("INSERT INTO `Order` VALUES (?,?,?)", order.getId(), order.getDate(), order.getCustomerId());
    }

    @Override
    public boolean update(Order order) throws Exception {
        return CrudUtil.execute("UPDATE `Order` SET name=?, address=? WHERE id=?", order.getDate(), order.getCustomerId(), order.getId());
    }

    @Override
    public boolean delete(Integer orderId) throws Exception {
        return CrudUtil.execute("DELETE FROM `Order` WHERE id=?", orderId);
    }
}
