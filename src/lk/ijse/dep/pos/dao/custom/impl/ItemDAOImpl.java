package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudUtil;
import lk.ijse.dep.pos.dao.custom.ItemDAO;
import lk.ijse.dep.pos.entity.Item;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public String getLastItemCode() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        } else {
            return null;
        }
    }

    @Override
    public List<Item> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item");
        List<Item> items = new ArrayList<>();
        while (rst.next()) {
            items.add(new Item(rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4)));
        }
        return items;
    }

    @Override
    public Item find(String itemCode) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item WHERE code=?", itemCode);
        if (rst.next()) {
            return new Item(rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4));
        }
        return null;
    }

    @Override
    public boolean save(Item item) throws Exception {
        return CrudUtil.execute("INSERT INTO Item VALUES (?,?,?,?)", item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
    }

    @Override
    public boolean update(Item item) throws Exception {
        return CrudUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?", item.getDescription(), item.getUnitPrice(), item.getQtyOnHand(), item.getCode());
    }

    @Override
    public boolean delete(String itemCode) throws Exception {
        return CrudUtil.execute("DELETE FROM Item WHERE code=?", itemCode);
    }
}
