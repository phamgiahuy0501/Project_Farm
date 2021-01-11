/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proc_data;

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
public class SqlDataFarm extends SqlData {

    private static final String SQL_SYNTAX_TABLE = "farm";
    private static final String SQL_SYNTAX_ID = "land_id";
    private static final String SQL_SYNTAX_TYPE = "type_plant";
    private static final String SQL_SYNTAX_TIMEFINISH = "date_finish";

    public static JSONObject getSpecificGround(int index) {
        Statement statement = null;
        ResultSet resultSet = null;
        JSONObject ground = new JSONObject();
        
        try {
            String query = "SELECT * FROM " + SQL_SYNTAX_TABLE + " WHERE " + SQL_SYNTAX_ID + "=" + index;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            if (!isEmptySet(resultSet)) {
                ground.put(SQL_SYNTAX_TYPE, resultSet.getInt(SQL_SYNTAX_TYPE));
                ground.put(SQL_SYNTAX_TIMEFINISH, resultSet.getLong(SQL_SYNTAX_TIMEFINISH));
            } else {
                System.out.println("ground empty");
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
        return ground;
    }

    public static List<JSONObject> getAllGround() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<JSONObject> listGround = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + SQL_SYNTAX_TABLE;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if (!isEmptySet(resultSet)) {
                do {
                    JSONObject tempJSONObject = new JSONObject();
                    tempJSONObject.put(SQL_SYNTAX_TYPE, resultSet.getInt(SQL_SYNTAX_TYPE));
                    tempJSONObject.put(SQL_SYNTAX_TIMEFINISH, resultSet.getLong(SQL_SYNTAX_TIMEFINISH));

                    listGround.add(tempJSONObject);
                } while (resultSet.next());
            } else {
                System.out.println("list ground empty");
            }

        } catch (NullPointerException | SQLException e) {
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
        return listGround;
    }

    private static boolean isEmptySet(ResultSet resultSet) throws SQLException {
        return !resultSet.next();
    }

    private static boolean validBound(int index) {
        return !(index < 1 || index > 8);
    }
}

class OverBoundControlException extends Exception {

    private final int bound;

    public OverBoundControlException(int bound) {
        this.bound = bound;
    }

    @Override
    public String toString() {
        return "Exception Out of bound control\tIndex : " + bound;
    }
}
