package lk.ijse.gdse.firstsemesterprojectfromlayered.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import lk.ijse.gdse.firstsemesterprojectfromlayered.db.DbConnection;

public class SQLUtil {
    public static <T> T execute(String sql, Object... args) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pstm.setObject(i + 1, args[i]);
        }
        if (sql.startsWith("SELECT")) {
            return (T) pstm.executeQuery();
        } else {
            return (T) (Boolean) (0 < pstm.executeUpdate());
        }
    }
}
