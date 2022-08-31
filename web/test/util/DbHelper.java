package com.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class DbHelper {

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    public DbHelper()
    {
        getConnection();
    }

    public void getConnection()
    {
        try {
            if(connection==null || connection.isClosed()) {
                try {

                    //读取配置文件的信息
                    Properties properties=new Properties();

                    InputStream inputStream= this.getClass().getResourceAsStream("db.properties");

                    properties.load(inputStream);

                    String driver=properties.getProperty("driver");

                    Class.forName(driver);

                    String url = properties.getProperty("url");

                    String uname =  properties.getProperty("uname");

                    String pwd =  properties.getProperty("pwd");

                    connection = DriverManager.getConnection(url, uname, pwd);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //  执行所有的 增 删 改
    public int executeUpdate(String sql)
    {
          getConnection();

        try {
            preparedStatement= connection.prepareStatement(sql);

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //  执行所有的 增 删 改
    public int executeUpdate(String sql,List<Object> paramList)
    {
        getConnection();

        try {
            preparedStatement= connection.prepareStatement(sql);


            if(paramList!=null)  //  paramlist   [1,"aa",18]
            {
                for(int i=0;i<paramList.size();i++)
                {
                    preparedStatement.setObject(i+1,paramList.get(i));
                }
            }


            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //执行查询操作
    // hashMap.put("id",1)
    // hashmap.put("uname","aa")
    public  List<Map<String,Object>> executeQuery(String sql)
    {
        getConnection();
        try {
            //bankinfo
            preparedStatement= connection.prepareStatement(sql);
            resultSet= preparedStatement.executeQuery();

            //查询的是结果集的表头
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

            List<Map<String,Object>> mapList=new ArrayList<Map<String,Object>>();

            while(resultSet.next())
            {
                HashMap<String,Object> hashMap=new HashMap<String,Object>();

                //遍历所有的列
                for(int i=1;i<=resultSetMetaData.getColumnCount();i++)
                {
                         //获取当前列的列名
                         String columnName=resultSetMetaData.getColumnName(i); //id
                         //根据列名找到值
                         Object columnValue= resultSet.getObject(columnName); //1

                         hashMap.put(columnName,columnValue);
                }

                mapList.add(hashMap);

            }

            close();

            return mapList;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public  List<Map<String,Object>> executeQuery(String sql,List paramList)
    {
        getConnection();
        try {
            //bankinfo
            preparedStatement= connection.prepareStatement(sql);

            if(paramList!=null)  //  paramlist   [1,"aa",18]
            {
                 for(int i=0;i<paramList.size();i++)
                 {
                     preparedStatement.setObject(i+1,paramList.get(i));
                 }
            }


            resultSet= preparedStatement.executeQuery();

            //查询的是结果集的表头
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

            List<Map<String,Object>> mapList=new ArrayList<Map<String,Object>>();

            while(resultSet.next())
            {
                HashMap<String,Object> hashMap=new HashMap<String,Object>();

                //遍历所有的列
                for(int i=1;i<=resultSetMetaData.getColumnCount();i++)
                {
                    //获取当前列的列名
                    String columnName=resultSetMetaData.getColumnName(i); //id
                    //根据列名找到值
                    Object columnValue= resultSet.getObject(columnName); //1

                    hashMap.put(columnName,columnValue);
                }

                mapList.add(hashMap);

            }

            close();

            return mapList;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void close()
    {
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
