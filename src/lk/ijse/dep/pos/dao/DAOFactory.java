package lk.ijse.dep.pos.dao;

import lk.ijse.dep.pos.dao.custom.impl.*;

public class DAOFactory {

    /*
     * Let's move these constants to an enum
    public static final int CUSTOMER_DAO = 0;
    public static final int ITEM_DAO = 1;
    public static final int ORDER_DAO = 2;
    public static final int ORDER_DETAIL_DAO = 3;
     */

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoType) {
        switch (daoType) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case ORDER:
                return (T) new OrderDAOImpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }

    /*
    public CustomerDAO getCustomerDAO(){
        return new CustomerDAOImpl();
    }

    public ItemDAO getItemDAO(){
        return new ItemDAOImpl();
    }

    public OrderDAO getOrderDAO(){
        return new OrderDAOImpl();
    }

    public OrderDetailDAO getOrderDetailDAO(){
        return new OrderDetailDAOImpl();
    }
     */

}
