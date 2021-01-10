/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proc_data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kazen
 */
public class SqlDataFarm extends SqlData {

    static ResultSet resultSet;

    public static ResultSet getSpecificGround(int index) {
        try {
            if (resultSet == null) throw new NullPointerException();
            if (!validBound(index)) throw new OverBoundControlException(index);
            
            resultSet.first();
            
            for (int i = 0; i < index - 1; i++) {
                resultSet.next();
            }
            
            return resultSet;
            
        } catch (OverBoundControlException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ResultSet getAllGround() {
        try {
            if (resultSet == null) throw new NullPointerException();
            resultSet.first();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    
    public static void loadAllGround() {
        try {
            resultSet = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM farm");
            if (isEmptySet()) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static boolean isEmptySet() throws SQLException {
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

