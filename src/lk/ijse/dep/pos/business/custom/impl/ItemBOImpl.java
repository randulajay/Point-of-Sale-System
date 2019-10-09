package lk.ijse.dep.pos.business.custom.impl;

import lk.ijse.dep.pos.business.custom.ItemBO;
import lk.ijse.dep.pos.business.exception.AlreadyExistsInOrderException;
import lk.ijse.dep.pos.dao.DAOFactory;
import lk.ijse.dep.pos.dao.DAOTypes;
import lk.ijse.dep.pos.dao.custom.ItemDAO;
import lk.ijse.dep.pos.dao.custom.OrderDetailDAO;
import lk.ijse.dep.pos.dto.ItemDTO;
import lk.ijse.dep.pos.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    private OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER_DETAIL);
    private ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);

    @Override
    public boolean saveItem(ItemDTO item) throws Exception {
        return itemDAO.save(new Item(item.getCode(),
                item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(ItemDTO item) throws Exception {
        return itemDAO.save(new Item(item.getCode(),
                item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
    }

    @Override
    public boolean deleteItem(String itemCode) throws Exception {
        if (orderDetailDAO.existsByItemCode(itemCode)) {
            throw new AlreadyExistsInOrderException("Item already exists in an order, hence unable to delete");
        }
        return itemDAO.delete(itemCode);
    }

    @Override
    public List<ItemDTO> findAllItems() throws Exception {
        List<Item> allItems = itemDAO.findAll();
        List<ItemDTO> dtos = new ArrayList<>();
        for (Item item : allItems) {
            dtos.add(new ItemDTO(item.getCode(),
                    item.getDescription(),
                    item.getQtyOnHand(),
                    item.getUnitPrice()));
        }
        return dtos;
    }

    @Override
    public String getLastItemCode() throws Exception {
        return itemDAO.getLastItemCode();
    }

    @Override
    public ItemDTO findItem(String itemCode) throws Exception {
        Item item = itemDAO.find(itemCode);
        return new ItemDTO(item.getCode(),
                item.getDescription(),
                item.getQtyOnHand(),
                item.getUnitPrice());
    }

    @Override
    public List<String> getAllItemCodes() throws Exception {
        List<Item> allItems = itemDAO.findAll();
        List<String> codes = new ArrayList<>();
        for (Item item : allItems) {
            codes.add(item.getCode());
        }
        return codes;
    }
}
