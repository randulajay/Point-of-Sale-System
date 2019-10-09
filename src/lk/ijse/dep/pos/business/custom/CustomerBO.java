package lk.ijse.dep.pos.business.custom;

import lk.ijse.dep.pos.business.SuperBO;
import lk.ijse.dep.pos.dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {

    boolean saveCustomer(CustomerDTO customer) throws Exception;

    boolean updateCustomer(CustomerDTO customer) throws Exception;

    boolean deleteCustomer(String customerId) throws Exception;

    List<CustomerDTO> findAllCustomers() throws Exception;

    String getLastCustomerId() throws Exception;

    CustomerDTO findCustomer(String customerId) throws Exception;

    List<String> getAllCustomerIDs() throws Exception;

}
