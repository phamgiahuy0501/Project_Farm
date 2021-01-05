/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proc_data;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author kazen
 */
public class SqlDataFarm extends SqlData {

    static ResultSet resultSet;

    public static void loadAllGround() {
        try {
            resultSet = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM farm");
            resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void display() {
        try {
            if (resultSet.next()) {
                resultSet.next();
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getLong(2));
            }
            
            if (resultSet.first()) {
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getLong(2));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
