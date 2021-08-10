package com.lyh.dao;

import com.lyh.bean.User;
import com.lyh.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    //增删改查

    //登录 select * from t_user
    public User login(String username,String password){
        User user = null;
        //1.创建链接
        Connection connection = DBHelper.getConnection();
        //2.创建sql语句
        String sql = "select * from t_user where username=? and password=?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //3.使用链接对象获取预编译对象
            ps = connection.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            //4.执行预编译对象
            rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setCreate_time(rs.getString("create_time"));
                user.setImg(rs.getString("img"));
                user.setIs_del(rs.getInt("is_del"));
                user.setModify_time(rs.getString("modify_time"));
                user.setPassword(rs.getString("password"));
                user.setReal_name(rs.getString("real_name"));
                user.setType(rs.getInt("type"));
                user.setUsername(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    //查询
    public List<User> selectAll(){
        //dao层如何和数据库做对接 ，我们用的知识点叫做jdbc
        //要链接数据库就需要到dbhelper.getconnection()
        //1、步骤1、创建链接对象
        List<User> users=new ArrayList<>();
        Connection connection = DBHelper.getConnection();
        //2、创建sql语句
        String sql="select * from t_user";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            //3、使用链接对象获取预编译对象
             ps= connection.prepareStatement(sql);
            System.out.println("ps = " + ps);
            //4、执行预编译对象，得出结果集
             rs= ps.executeQuery();
             //5、遍历结果集,一一的取对象
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setCreate_time(rs.getString("create_time"));
                user.setImg(rs.getString("img"));
                user.setIs_del(rs.getInt("is_del"));
                user.setModify_time(rs.getString("modify_time"));
                user.setPassword(rs.getString("password"));
                user.setReal_name(rs.getString("real_name"));
                user.setType(rs.getInt("type"));
                user.setUsername(rs.getString("username"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       return  users;
    }


    //新增
    public int addUser(User user){
    //  第一步：创建链接对象
        Connection connection = DBHelper.getConnection();
    //    第二步：SQL语句
        String sql = "insert into t_user values (null,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = null;
        int i =0;
        try {
            //  第三步 预编译SQL
            ps = connection.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getReal_name());
            ps.setString(4,user.getImg());
            ps.setInt(5,user.getType());
            ps.setInt(6,user.getIs_del());
            ps.setString(7,user.getCreate_time());
            ps.setString(8,user.getModify_time());
            //第四部 执行 预编译对象
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }


    //删除
    public int deleteUser(int id){
        //1、步骤1、创建链接对象
        Connection connection = DBHelper.getConnection();
        //2、sql语句因为添加的数据是变量 ，所以要用?代替
        String sql="delete from  t_user where id=?";

        PreparedStatement ps=null;
        int i=0;
        try {
            //3、预编译
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            i=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  i;
    }

    //修改
    public int updateUser(User user){
        //1、创建链接对象
        Connection connection = DBHelper.getConnection();
        //2、sql语句因为添加的数据是变量 ，所以要用?代替
        String sql = "update t_user set username=?,password=?,real_name=?,img=?,type=?,is_del=?,create_time=?,modify_time=? where id=?";
        PreparedStatement ps = null;
        int i =0;
        try {
            //3、预编译
            ps = connection.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getReal_name());
            ps.setString(4,user.getImg());
            ps.setInt(5,user.getType());
            ps.setInt(6,user.getIs_del());
            ps.setString(7,user.getCreate_time());
            ps.setString(8,user.getModify_time());
            ps.setInt(9,user.getId());
           //4、执行预编译
            i=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
    public static void main(String[] args) {
        //全查
        UserDao dao=new UserDao();
  /*      List<User> users=dao.selectAll();
        for (User user : users) {
            System.out.println("user = " + user);
        }*/
  //新增
  /*User user = new User();
  user.setUsername("kaijiayongshi");
  user.setType(1);
  user.setReal_name("铠甲勇士");
  user.setPassword("123");
  user.setModify_time("2021-08-09");
  user.setIs_del(1);
  user.setImg("XXXX");
  user.setCreate_time("2021-08-09");
  int i = dao.addUser(user);
        System.out.println("i = " + i);*/

  //删除
      /*  int i= dao.deleteUser(61);
        System.out.println("i = " + i);*/

      //修改
      /*  User user = new User();
        user.setId(8);
        user.setUsername("迪迦");
        user.setType(1);
        user.setReal_name("迪迦奥特曼");
        user.setPassword("123456");
        user.setModify_time("2021-12-07 11:33:54");
        user.setIs_del(1);
        user.setImg("xxxx");
        user.setCreate_time("2020-07-19");
        int i=dao.updateUser(user);
        System.out.println("i = " + i);*/

      //登录测试
        User abc = dao.login("abc", "123456");
        System.out.println("abc = " + abc);
    }
}
