package com.example.eattolife.sql;

import com.example.eattolife.user.Userinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户数据库操作类
 * 实现用户的CRUD操作
 */

public class UserDao extends DbOpenHelper {
    //查询所有用户信息 R
    public List<Userinfo> getAllUserList(){
        List<Userinfo> list = new ArrayList<>();
        try{
            getConnection();//取得连接信息
            String sql = "select * from userinfo";
            pStmt = conn.prepareStatement(sql);
            rs= pStmt.executeQuery();
            while(rs.next()){
                Userinfo item = new Userinfo();
                item.setUserID(rs.getInt("userID"));
                item.setUserName(rs.getString("userName"));
                item.setCell(rs.getString("cell"));
                item.setCell(rs.getString("password"));
                item.setAge(rs.getInt("age"));
                item.setSex(rs.getString("sex"));
                item.setHeight(rs.getDouble("height"));
                item.setWeight(rs.getDouble("weight"));
                item.setFoodLike(rs.getString("foodLike"));
                item.setSportLike(rs.getString("sportLike"));

                list.add(item);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return list;
    }
    /**
     * 按手机号和密码查询用户信息 R
     * @param cell 手机号
     * @param password 密码
     * @return Userinfo 实例
     */
    public Userinfo getUserByCellAndPassword(String cell, String password){
        Userinfo item = null;
        try{
            getConnection();//取得连接信息
            String sql = "select * from userinfo where cell=? and password=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, cell);
            pStmt.setString(2, password);
            rs= pStmt.executeQuery();
            if(rs.next()){
                item = new Userinfo();
                item.setUserID(rs.getInt("userID"));
                item.setUserName(rs.getString("userName"));
                item.setCell(cell);
                item.setCell(password);
                item.setAge(rs.getInt("age"));
                item.setSex(rs.getString("sex"));
                item.setHeight(rs.getDouble("height"));
                item.setWeight(rs.getDouble("weight"));
                item.setFoodLike(rs.getString("foodLike"));
                item.setSportLike(rs.getString("sportLike"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return item;
    }
    /**
     * 添加用户信息 C
     * @param item 要添加的用户信息
     * @return int 影响的行数
     */
    public int addUser(Userinfo item){
        int iRow = 0;
        try{
            getConnection();//取得连接信息
            String sql ="insert into userinfo(userName, cell, password, age, sex, height, weight, foodLike, sportLike) value(?,?,?,?,?,?,?,?,?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,item.getUserName());
            pStmt.setString(2,item.getCell());
            pStmt.setString(3,item.getPassword());
            pStmt.setInt(4,item.getAge());
            pStmt.setString(5,item.getSex());
            pStmt.setDouble(6,item.getHeight());
            pStmt.setDouble(7,item.getWeight());
            pStmt.setString(8,item.getFoodLike());
            pStmt.setString(9,item.getSportLike());
            iRow = pStmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }
    /**
     * 修改用户密码 U
     * @param item 要修改的用户信息
     * @return int 影响的行数
     */
    public int editUserPassword(Userinfo item){
        int iRow = 0;
        try{
            getConnection();//取得连接信息
            String sql ="update userinfo set password=? where id=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,item.getPassword());
            iRow = pStmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }
    /**
     * 根据id删除用户信息
     * @param uerID 要删除用户的id
     * @return int 影响的行数
     */
    public int delUser(int uerID){
        int iRow = 0;
        try{
            getConnection();//取得连接信息
            String sql ="delete from userinfo where userID=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1,uerID);
            iRow = pStmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }
}