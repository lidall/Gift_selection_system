package com.example.RUA.myapplication;

/**
 * Created by harry on 2018/2/24.
 */

import android.widget.Switch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author harry
 */
public class DatabaseFunction {

    public static int dataInsert(String FriendName, String Birthday, String GiftWish, String Description) {

        Connection conn = SystemInitial.getConn();
        int i = 0;
        String sql = "insert into xuwei (FriendName,BirthDay,GiftWish,Description) values (?,?,?,?)";
        PreparedStatement pstmt;
        try {

            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, FriendName);
            pstmt.setString(2, Birthday);
            pstmt.setString(3, GiftWish);
            pstmt.setString(4, Description);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }


    public static Integer dataBaseGetAll() {
        Connection conn = SystemInitial.getConn();
        String sql = "select * from UserInfo";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



        public static String getinfor(int command) {
            String sql="";
            String res="";
            Connection conn = SystemInitial.getConn();

            sql = "select Birthday,GiftWish,Description from xuwei where FriendName='Hongyi Zhang'";

            PreparedStatement pstmt;
            try {
                pstmt = (PreparedStatement)conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                switch (command) {
                    case 1: {
                        res = rs.getString("Birthday");
                        break;
                    }
                    case 2: {
                        res = rs.getString("GiftWish");
                        break;
                    }

                    case 3: {
                        res = rs.getString("Description");
                        break;
                    }
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return res;

        }
}

