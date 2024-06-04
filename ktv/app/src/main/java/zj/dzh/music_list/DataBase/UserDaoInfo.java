package zj.dzh.music_list.DataBase;

import android.annotation.SuppressLint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import zj.dzh.music_list.bean.UserInfo;


public class UserDaoInfo {

    // 数据库连接
    private Connection connection;

    public UserDaoInfo(Connection connection) {
        this.connection = connection;
    }

    // 更新用户信息
    public boolean updateUser(UserInfo user) {
        String sql = "UPDATE users SET userId = ?, userPassword = ?, userName = ?, userAge = ?, userPicPath = ?, userSex = ? WHERE id = ?";

        try (@SuppressLint({"NewApi", "LocalSuppress"}) PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getUserPassword());
            ps.setString(3, user.getUserName());
            ps.setShort(4, user.getUserAge());
            ps.setString(5, user.getUserPicPath());
            ps.setString(6, user.getUserSex());
            ps.setString(7, user.getId()); // 设置ID参数

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}