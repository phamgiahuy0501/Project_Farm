/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proc_data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kazen
 */
public class SqlDataMoney extends SqlData {

    static ResultSet resultSet;
    private static final int BRAND_ID_MONEY = 0;
    private static final int TYPE_MONEY = 0;

    private static final String SQL_SYNTAX_AMOUNT = "amount";
    private static final String SQL_SYNTAX_TABLE = "item";

    public static void loadMoney() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT " + SQL_SYNTAX_AMOUNT + " FROM " + SQL_SYNTAX_TABLE + " WHERE brand_id=? AND type=?");
            statement.setInt(1, BRAND_ID_MONEY);
            statement.setInt(2, TYPE_MONEY);
            resultSet = statement.executeQuery();
            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getMoney() {
        loadMoney();
        try {
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public static void updateMoney(int amount) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE " + SQL_SYNTAX_TABLE + " SET " + SQL_SYNTAX_AMOUNT + "=?" + " WHERE brand_id=? AND type=?");
            statement.setInt(1, amount);
            statement.setInt(2, 0);
            statement.setInt(3, 0);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
