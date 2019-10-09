package lk.ijse.dep.pos.dao;

import lk.ijse.dep.pos.entity.SuperEntity;

import java.util.List;

public interface CrudDAO<T extends SuperEntity, ID> extends SuperDAO {

    List<T> findAll() throws Exception;

    T find(ID id) throws Exception;

    boolean save(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    boolean delete(ID id) throws Exception;

}
