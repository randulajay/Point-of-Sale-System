package lk.ijse.dep.pos.dao;

import lk.ijse.dep.pos.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CrudUtil {

    public static <T> T execute(String sql, Object... params) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstm.setObject((i + 1), params[i]);
        }
        if (sql.startsWith("SELECT")) {
            return (T) pstm.executeQuery();
        }
        return (T) ((Boolean) (pstm.executeUpdate() > 0));
    }
    /*
    public ResultSet executeQuery(String sql, Object... params) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstm.setObject((i + 1), params[i]);
        }
        return pstm.executeQuery();
    }

    public boolean executeUpdate(String sql, Object... params)throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstm.setObject((i + 1), params[i]);
        }
        return pstm.executeUpdate() > 0;
    }
*/
}
