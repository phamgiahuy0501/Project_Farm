/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proc_data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kazen
 */
public class SqlData {
    
    static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    protected static Connection connection;

    public static final String SQL_FARM_SYNTAX_TABLE = "farm";
    public static final String SQL_FARM_SYNTAX_ID = "land_id";
    public static final String SQL_FARM_SYNTAX_TYPE = "type_plant";
    public static final String SQL_FARM_SYNTAX_TIMEFINISH = "date_finish";

    public static final String SQL_ITEM_SYNTAX_TABLE = "item";
    public static final String SQL_ITEM_SYNTAX_ID = "id";
    public static final String SQL_ITEM_SYNTAX_BRAND = "brand_id";
    public static final String SQL_ITEM_SYNTAX_TYPE = "type";
    public static final String SQL_ITEM_SYNTAX_AMOUNT = "amount";
    
    public static void initConnection(String host, String user, String pass) {
        try {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(host, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
