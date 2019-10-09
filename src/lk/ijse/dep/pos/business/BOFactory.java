package lk.ijse.dep.pos.business;

import lk.ijse.dep.pos.business.custom.impl.CustomerBOImpl;
import lk.ijse.dep.pos.business.custom.impl.ItemBOImpl;
import lk.ijse.dep.pos.business.custom.impl.OrderBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? (boFactory = new BOFactory()) : boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case ORDER:
                return (T) new OrderBOImpl();
            default:
                return null;
        }
    }

}
