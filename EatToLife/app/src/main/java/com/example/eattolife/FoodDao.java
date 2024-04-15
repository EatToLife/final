package com.example.eattolife;

import com.example.eattolife.FoodInfo;
import com.example.eattolife.sql.DbOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户数据库操作类
 * 实现用户对饮食的管理CRUD操作
 */
public class FoodDao extends DbOpenHelper {
    /**
     * 查询所有的饮食信息
     */
    public List<FoodInfo> getAllFoodList() {
        List<FoodInfo> list = new ArrayList<>();
        try{
            getConnection(); //获取连接信息
            String sql = "select * from foodinfo";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while (rs.next()) { //查询数据不用if，而用while
                FoodInfo item = new FoodInfo();
                item.setFoodID(rs.getInt("foodID"));
                item.setFoodName(rs.getString("foodName"));
                item.setFoodPrice(rs.getInt("foodPrice"));
                item.setFoodCalorie(rs.getFloat("foodCalorie"));
                //item.setFoodPic(rs.getString("foodPic"));
                list.add(item);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return list;
    }

    /**
     * 按饮食日期和饮食餐别查询用户信息
     * @param foodDate 饮食日期
     * @param foodMeal 饮食类别
     * @return com.example.eattolife.FoodRecord 实例
     */
    public FoodRecord getFoodRecordByfoodDateAndfoodMeal(String foodDate, String foodMeal) {
        FoodRecord item = null;
        try{
            getConnection(); //获取连接信息
            String sql = "select * from foodrecord where foodDate=? and foodMeal=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, foodDate);
            pStmt.setString(2,foodMeal);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                item = new FoodRecord();
                item.setFoodDate(foodDate);
                item.setFoodMeal(foodMeal);
                item.setFoodCalorie(rs.getFloat("foodCalorie"));
                //item.setFoodPic(rs.getString("foodPic"));
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return item;
    }

    /**
     * 添加饮食信息 C
     * @param item 要添加的饮食信息
     * @return int 影响的行数
     */
    public int addFoodInfo(FoodInfo item) {
        int iRow = 0;
        try{
            getConnection(); //获取连接信息
            String sql = "insert into foodinfo(foodName, foodPrice, foodCalorie) values(?,?,?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, item.getFoodName());
            pStmt.setInt(2,item.getFoodPrice());
            pStmt.setFloat(3, item.getFoodCalorie());
            // pStmt.setString(4,item.getFoodPic());
            iRow = pStmt.executeUpdate();
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }

    /**
     * 修改饮食信息 C
     * @param item 要编辑的饮食信息
     * @return int 影响的行数
     */
    public int editFoodRecord(FoodInfo item) {
        int iRow = 0;
        try{
            getConnection(); //获取连接信息
            String sql = "update foodinfo set foodCalorie=?, foodPrice=? where foodID=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setFloat(1, item.getFoodCalorie());
            pStmt.setInt(2,item.getFoodPrice());
            pStmt.setInt(3, item.getFoodID());
            //pStmt.setString(4,item.getFoodPic());
            iRow = pStmt.executeUpdate();
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }

    /**
     * 删除饮食信息 C
     * @param foodID 要删除的饮食编号
     * @return int 影响的行数
     */
    public int delFoodRecord(int foodID)  {
        int iRow = 0;
        try{
            getConnection(); //获取连接信息
            String sql = "delete from foodinfo where foodID=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, String.valueOf(foodID));
            iRow = pStmt.executeUpdate();
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {

            closeAll();
        }
        return iRow;
    }
}
