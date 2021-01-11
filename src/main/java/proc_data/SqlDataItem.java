/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proc_data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author kazen
 */
public class SqlDataItem extends SqlData {

    private static final int BRAND_ID_MONEY = -1;
    private static final int TYPE_MONEY = 0;

    private static final String SQL_SYNTAX_TABLE = "item";
    private static final String SQL_SYNTAX_ID = "id";
    private static final String SQL_SYNTAX_BRAND = "brand_id";
    private static final String SQL_SYNTAX_TYPE = "type";
    private static final String SQL_SYNTAX_AMOUNT = "amount";

    public static List<JSONObject> getAllItem(int brand, int type) {
        Statement statement = null;
        ResultSet resultSet = null;
        List<JSONObject> listItem = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM " + SQL_SYNTAX_TABLE + " WHERE " + SQL_SYNTAX_BRAND + "=" + brand + " AND " + SQL_SYNTAX_TYPE + "=" + type;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            if (!isEmptySet(resultSet)) {
                do {                    
                    JSONObject tempJSONObject = new JSONObject();
                    tempJSONObject.put(SQL_SYNTAX_BRAND, resultSet.getInt(SQL_SYNTAX_BRAND));
                    tempJSONObject.put(SQL_SYNTAX_TYPE, resultSet.getInt(SQL_SYNTAX_TYPE));
                    tempJSONObject.put(SQL_SYNTAX_AMOUNT, resultSet.getInt(SQL_SYNTAX_AMOUNT));
                    
                    listItem.add(tempJSONObject);
                } while (resultSet.next());
            } else {
                System.out.println("list item empty");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return listItem;
    }
    
    public static int getMoney() {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT " + SQL_SYNTAX_AMOUNT + " FROM " + SQL_SYNTAX_TABLE + " WHERE " + SQL_SYNTAX_BRAND + "=" + BRAND_ID_MONEY + " AND " + SQL_SYNTAX_TYPE + "=" + TYPE_MONEY;
            statement = connection.createStatement();

            resultSet = statement.executeQuery(query);
            if (!isEmptySet(resultSet)) {
                return resultSet.getInt(SQL_SYNTAX_AMOUNT);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static void updateMoney(int amount) {
        Statement statement = null;
        try {
            String query = "UPDATE " + SQL_SYNTAX_TABLE + " SET " + SQL_SYNTAX_AMOUNT + "=" + amount + " WHERE " + SQL_SYNTAX_BRAND + "=" + BRAND_ID_MONEY + " AND " + SQL_SYNTAX_TYPE + "=" + TYPE_MONEY;
            statement = connection.createStatement();
            if (statement.executeUpdate(query) == 1) {
                System.out.println("updated money");
            } else {
                System.out.println("fail update money");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getAmountItem(int brand, int type) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT " + SQL_SYNTAX_AMOUNT + " FROM " + SQL_SYNTAX_TABLE + " WHERE " + SQL_SYNTAX_BRAND + "=" + brand + " AND " + SQL_SYNTAX_TYPE + "=" + type;
            statement = connection.createStatement();

            resultSet = statement.executeQuery(query);
            if (!isEmptySet(resultSet)) {
                return resultSet.getInt(SQL_SYNTAX_AMOUNT);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static void updateAmountItem(int brand, int type, int amount) {
        Statement statement = null;
        try {
            String query = "UPDATE " + SQL_SYNTAX_TABLE + " SET " + SQL_SYNTAX_AMOUNT + "=" + amount + " WHERE " + SQL_SYNTAX_BRAND + "=" + brand + " AND " + SQL_SYNTAX_TYPE + "=" + type;
            statement = connection.createStatement();

            if (statement.executeUpdate(query) == 1) {
                System.out.println("updated item amount");
            } else {
                query = "INSERT INTO " + SQL_SYNTAX_TABLE + " VALUES (" + String.valueOf(calID(brand, type)) + "," + String.valueOf(brand) + "," + String.valueOf(type) + "," + String.valueOf(amount) + ")";
                if (statement.executeUpdate(query) == 1) {
                    System.out.println("inserted item");
                } else {
                    System.out.println("cant insert item");
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static int calID(int brand, int type) {
        return brand * 10 + type;
    }

    private static boolean isEmptySet(ResultSet resultSet) throws SQLException {
        return !resultSet.next();
    }

}
