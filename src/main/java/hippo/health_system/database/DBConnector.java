package hippo.health_system.database;

import hippo.health_system.bean.DeptBean;
import hippo.health_system.bean.HospitalBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangchaoyuan on 16/12/8.
 */
public class DBConnector {
        public static String url = "jdbc:mysql://localhost:3306/health_system";//characterEncoding=GBK
//        private static String username;//username
//        private static String password;//passwd
        public static Connection con;
        public static Statement stmt;
        public static ResultSet rs;

        public static void testInsertMain(HospitalBean hospitalBean, DeptBean deptBean){
            try {
                String query = "insert into deptBasicMsg values (?,?,?,?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1,hospitalBean.getAreaId());
                preparedStmt.setString(2, hospitalBean.getHospitalId());
                preparedStmt.setString(3,deptBean.getDeptId());
                preparedStmt.setString(4,deptBean.getDeptName());
//                preparedStmt.setString(11,deptBean.getDeptIntro());
                preparedStmt.execute();
            }catch (Exception e){System.out.println("insert error.");}

        }
        public static void testInsertDetail(HospitalBean hospitalBean){
            try{
                String query = "insert into hospitalDetail values (?,?,?,?,?,?,?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1,hospitalBean.getHospitalAddr());
                preparedStmt.setString(2,hospitalBean.getHospitalLevel());
                preparedStmt.setString(3, hospitalBean.getHospitalName());
                preparedStmt.setString(4, hospitalBean.getHospitalQuality());
                preparedStmt.setString(5,hospitalBean.getHospitalTel());
                preparedStmt.setString(6, hospitalBean.getHospitalType());
                preparedStmt.setString(7, hospitalBean.getHospitalId());
                preparedStmt.execute();


            }catch(Exception e) {System.out.println("insert hospitalerror");}


        }

        public static void close(){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        };//close connect

        public static void connect(String username,String password) {
            // 定位驱动
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("加载驱动成功!");
            } catch (ClassNotFoundException e) {
                System.out.println("加载驱动失败!");
                e.printStackTrace();
            }
            // 建立连接
            try {
                con = DriverManager.getConnection(url, username, password);
                stmt = con.createStatement();
                System.out.println("数据库连接成功!");
            } catch(SQLException e) {
                System.out.println("数据库连接失败!");
            }
        }
        public static List<String> getDeptIdByRADeptName(String regionName,String deptName ){

            String sql_select = "select * from deptBasicMsg where deptName like '%"+deptName+"%'"+" and areaName="+"'"+regionName+"'";
            return select(sql_select);

        }//RAD=RegionName&DeptName

        public static List<String> getDeptIdByDeptName(String deptName){

            String sql_select = "select * from deptBasicMsg where deptName like '%"+deptName+"%'";
            return select(sql_select);
        }

        public static List<String> select(String sql) {
            try {
//                connect();
                ResultSet rs = stmt.executeQuery(sql);//创建数据对象
//                System.out.println("1"+"\t"+"2"+"\t"+"3"+"\t"+"4");
                List<String> id =new ArrayList<String>();
                while (rs.next()){
//                    System.out.print(rs.getString(1) + "\t");
//                    System.out.print(rs.getString(2) + "\t");
//                    {System.out.print(rs.getString(3) + "\t");
                    id.add(rs.getNString(3));//3=deptName
//                    System.out.print(rs.getString(4) + "\t");
//                    System.out.println();
                }
                rs.close();
                stmt.close();
//                conn.close();
                return id;
            }catch (Exception e) {
                System.out.println("数据查询失败!");
            }
            return null;
        }


}
