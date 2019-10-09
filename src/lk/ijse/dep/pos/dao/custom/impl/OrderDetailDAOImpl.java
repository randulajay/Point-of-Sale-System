package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudUtil;
import lk.ijse.dep.pos.dao.custom.OrderDetailDAO;
import lk.ijse.dep.pos.entity.OrderDetail;
import lk.ijse.dep.pos.entity.OrderDetailPK;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public List<OrderDetail> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM OrderDetail");
        List<OrderDetail> orderDetails = new ArrayList<>();
        while (rst.next()) {
            orderDetails.add(new OrderDetail(rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)));
        }
        return orderDetails;
    }

    @Override
    public OrderDetail find(OrderDetailPK orderDetailPK) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM OrderDetail WHERE orderId=? AND itemCode=?", orderDetailPK.getOrderId(), orderDetailPK.getItemCode());
        if (rst.next()) {
            return new OrderDetail(rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4));
        }
        return null;
    }

    @Override
    public boolean save(OrderDetail orderDetail) throws Exception {
        return CrudUtil.execute("INSERT INTO OrderDetail VALUES (?,?,?,?)", orderDetail.getOrderDetailPK().getOrderId(), orderDetail.getOrderDetailPK().getItemCode()
                , orderDetail.getQty(), orderDetail.getUnitPrice());
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws Exception {
        return CrudUtil.execute("UPDATE OrderDetail SET qty=?, unitPrice=?  WHERE orderId=? AND itemCode=?", orderDetail.getQty(), orderDetail.getUnitPrice(), orderDetail.getOrderDetailPK().getOrderId(), orderDetail.getOrderDetailPK().getItemCode());
    }

    @Override
    public boolean delete(OrderDetailPK orderDetailPK) throws Exception {
        return CrudUtil.execute("DELETE FROM OrderDetail WHERE orderId=? AND itemCode=?", orderDetailPK.getOrderId(), orderDetailPK.getItemCode());
    }

    @Override
    public boolean existsByItemCode(String itemCode) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM OrderDetail WHERE itemCode=?", itemCode);
        return rst.next();
    }
}
