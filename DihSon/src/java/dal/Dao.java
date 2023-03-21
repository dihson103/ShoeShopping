/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Cart;
import model.Feedback;
import model.ItemInCart;
import model.Order;
import model.Product;
import model.User;
import ultils.Check;

/**
 *
 * @author admin
 */
public class Dao {

    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    
    public static ArrayList<Order> getAllListOrder(){
        String sql = "Select * from orders_He160021";
        ArrayList<Order> listOrder = new ArrayList<>();
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int userId = rs.getInt(2);
                String fullName = rs.getString(3);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                String address = rs.getString(6);
                String not = rs.getString(7);
                String orderDate = rs.getString(8);
                String status = rs.getInt(9)==0?"Waiting for accept":"Accepted";
                ArrayList<ItemInCart> listIdProduct = getListItemByOrderId(id);
                listOrder.add(new Order(id, userId, fullName, email, phone, 
                        address, not, orderDate, status, listIdProduct));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listOrder;
    }
    
    private static ArrayList<ItemInCart> getListItemByOrderId(int orderId){
        String query = "Select * from orders_detail_He160021 where id = ?";
        ArrayList<ItemInCart> listItems = new ArrayList<>();
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt(2);
                double priceWhenSell = rs.getDouble(3);
                int number = rs.getInt(4);
                Product p = getProductById(productId);
                listItems.add(new ItemInCart(p, number, priceWhenSell));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static void addNewProduct(String name, String image, String des, String quanity, String price, String cate){
        String sql = "insert into product_He160021 (category_id, title, price, number, [image], [description])\n" +
                        "values (?, ?, ?, ?, ?, ?)";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cate);
            ps.setString(2, name);
            ps.setString(3, price);
            ps.setString(4, quanity);
            ps.setString(5, image);
            ps.setString(6, des);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void addOrderToDbIfHasNoUserID(String name, String email, 
            String phone, String address){
        String sql = "insert into orders_He160021 (fullname, email, phone, [address], order_date ,[status])\n" +
                        "values (?, ?, ?, ?, ?, 0)"; // status 0 la chua accept
        Check check = new Check();
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, check.getDateNow());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void addOrderToDbIfHasUserID(User user, String name, String email, 
            String phone, String address){
        String sql = "insert into orders_He160021 ([user_id], fullname, email, phone, [address], order_date ,[status])\n" +
                        "values (?, ?, ?, ?, ?, ?, 0)"; // status 0 la chua accept
        Check check = new Check();
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setString(6, check.getDateNow());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void addOrderToOrderDetail(ArrayList<ItemInCart> listItems, String email){
        String sql = "insert into orders_detail_He160021 (order_id, product_id, price, number)\n" +
                    "values (?, ?, ?, ?)";
        int orderId = getTheNewestOrderIdByEmail(email);
   
        try {
            for (ItemInCart item : listItems) {
                conn = new DbContext().getConnection();
                ps = conn.prepareStatement(sql);
                ps.setInt(1, orderId);
                ps.setInt(2, item.getProduct().getId());
                ps.setDouble(3, item.getPriceWhenSell());
                ps.setInt(4, item.getNumber());
                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static int getTheNewestOrderIdByEmail(String email){
        String sql = "select top 1 (id)\n" +
                    "from orders_He160021\n" +
                    "where email = ?\n" +
                    "order by id desc";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                return id;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public static void main(String[] args) {
        System.out.println(getTheNewestOrderIdByEmail("dinhson1032001@gmail.com"));
    }
   
    public static void updateAccountInfor(User user){
        String sql = "update User_He160021\n"
                + "set fullname = ?, email = ?, phone = ?, [address] = ?\n"
                + "where id = ?";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getAddress());
            ps.setInt(5, user.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addFeedbackToDB(Feedback f) {
        String sql = "insert into Feedback_He160021 (email, [name], [subject], [message], dateSend)\n"
                + "values (?, ?, ?, ?, ?)";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,f.getEmail());
            ps.setString(2, f.getName());
            ps.setString(3, f.getSubject());
            ps.setString(4, f.getMess());
            ps.setString(5, f.getDate());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Feedback getFeedbackById(String id) {
        String query = "Select * from Feedback_He160021 where id = ?";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Feedback(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void removeFeedbackById(String id) {
        String sql = "delete from Feedback_He160021 where id = ?";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Feedback> getListFeedback() {
        ArrayList<Feedback> ls = new ArrayList<>();
        try {
            String query = "Select * from Feedback_He160021";
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                ls.add(f);
            }
            return ls;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void updateProduct(Product p) {
        String sql = "update product_He160021\n"
                + "set title = ?, price = ?, number = ?, [image] = ?, [description] = ?\n"
                + "where id = ?";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getTittle());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getNumber());
            ps.setString(4, p.getImage());
            ps.setString(5, p.getDescription());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void changePassByEmail(String email, String pass) {
        String sql = "update User_He160021 \n"
                + "set password = ?\n"
                + "where email  = ?";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addNewUserToDB(User user) {
        String sql = "insert into User_He160021 (email, role_id, fullname, password, isdeleted) values\n"
                + "(?, 3, ?, ?, 0)";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteProductById(String id) {
        String sql = "delete from Product_He160021 where id = ?";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateRoleUser(String id, String roleId) {
        if (roleId.equals("1")) {
            roleId = "3";
        } else {
            roleId = "1";
        }
        String sql = "update User_He160021 \n"
                + "set role_id = ?\n"
                + "where id  = ?";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, roleId);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteUserById(String id) {
        String sql = "delete from User_He160021 where id = ?";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Order> getListOrder() {// chuwa co sql
        try {
            String query = "Select * from User_He160021";

            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Order> ls = new ArrayList<>();
            while (rs.next()) {
                Order o = new Order();
                ls.add(o);
            }
            return ls;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static ArrayList<User> getListUser() {
        try {
            String query = "Select * from User_He160021";

            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<User> ls = new ArrayList<>();
            while (rs.next()) {
                User u = new User(rs.getInt("id"),
                        rs.getInt("role_id"),
                        rs.getString("email"),
                        rs.getString("fullname"),
                        rs.getString("phone"));
                ls.add(u);
            }
            return ls;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static double getAmountSellInYear() {
        Check check = new Check();
        double total = 0;
        int number = 0;
        double price = 0;
        String sql = "select *\n"
                + "from orders_detail_He160021\n"
                + "where order_id = (select id\n"
                + "from orders_He160021\n"
                + "where and YEAR(order_date) = YEAR(?))";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, check.getDateNow());
            rs = ps.executeQuery();
            while (rs.next()) {
                number = rs.getInt("number");
                price = rs.getDouble("price");
                total += number * price;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return total;
    }

    public static int getNumberProductSellInYear() {
        Check check = new Check();
        String sql = "select count(*)\n"
                + "from orders_detail_He160021\n"
                + "where order_id = (select id\n"
                + "from orders_He160021\n"
                + "where YEAR(order_date) = YEAR(?))";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, check.getDateNow());
            ps.setString(2, check.getDateNow());
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static double getAmountSellInMonth() {
        Check check = new Check();
        double total = 0;
        int number = 0;
        double price = 0;
        String sql = "select *\n"
                + "from orders_detail_He160021\n"
                + "where order_id = (select id\n"
                + "from orders_He160021\n"
                + "where month(order_date) = month(?) and YEAR(order_date) = YEAR(?))";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, check.getDateNow());
            rs = ps.executeQuery();
            while (rs.next()) {
                number = rs.getInt("number");
                price = rs.getDouble("price");
                total += number * price;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return total;
    }

    public static int getNumberProductSellInMonth() {
        Check check = new Check();
        String sql = "select count(*)\n"
                + "from orders_detail_He160021\n"
                + "where order_id = (select id\n"
                + "from orders_He160021\n"
                + "where month(order_date) = month(?) and YEAR(order_date) = YEAR(?))";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, check.getDateNow());
            ps.setString(2, check.getDateNow());
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static double getAmountSellInDay() {
        Check check = new Check();
        double total = 0;
        int number = 0;
        double price = 0;
        String sql = "select *\n"
                + "from orders_detail_He160021\n"
                + "where order_id = (select id\n"
                + "from orders_He160021\n"
                + "where order_date = ?)";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, check.getDateNow());
            rs = ps.executeQuery();
            while (rs.next()) {
                number = rs.getInt("number");
                price = rs.getDouble("price");
                total += number * price;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return total;
    }

    public static int getNumberProductSellInDay() {
        Check check = new Check();
        String sql = "select count(*)\n"
                + "from orders_detail_He160021\n"
                + "where order_id = (select id\n"
                + "from orders_He160021\n"
                + "where order_date = ?)";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, check.getDateNow());
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static int getNumberAccount() {
        String sql = "select count(*) from user_He160021";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public static User isAdmin(String email){
        try {
            String sql = "select * \n"
                    + "from User_He160021\n"
                    + "where role_id = 1 and email = ?";
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static User getAdminByEmail(User user) {
        try {
            String sql = "select * \n"
                    + "from User_He160021\n"
                    + "where role_id = 1 and email = ?";
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static User getUserByEmail(String email) {
        try {
            String sql = "select *\n"
                    + "from User_He160021\n"
                    + "where email = ? ";
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static User getUserByPassAndEmail(String email, String pass) {
        try {
            String sql = "select *\n"
                    + "from User_He160021\n"
                    + "where email = ? and [password] = ? ";
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static ArrayList<Product> getListProductByCategoryId(int id, int index, int size) {
        int start = index * size - (size - 1);
        int end = index * size;
        try {
            String query = "select * from \n"
                    + "(select ROW_NUMBER() over (order by id asc) as r, *\n"
                    + "from (select * from product_He160021\n"
                    + "where category_id = ?) as s) as x\n"
                    + "where r between ? and ?";

            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, start);
            ps.setInt(3, end);
            rs = ps.executeQuery();
            ArrayList<Product> ls = new ArrayList<>();
            while (rs.next()) {
                Product s = new Product(rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getInt(6),
                        0,
                        rs.getString(8),
                        rs.getString(9));
                ls.add(s);
            }
            return ls;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static ArrayList<Product> getListProduct() {
        try {
            String query = "Select * from Product_He160021";

            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Product> ls = new ArrayList<>();
            while (rs.next()) {
                Product s = new Product(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        0,
                        rs.getString(7),
                        rs.getString(8));
                ls.add(s);
            }
            return ls;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Product getProductById(int id) {
        try {
            String query = "Select * from Product_He160021 where id = ?";

            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            ArrayList<Product> ls = new ArrayList<>();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        0,
                        rs.getString(7),
                        rs.getString(8));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static ArrayList<Product> getListNewProduct() {
        try {
            String query = "select top 3 * from product_He160021\n"
                    + "order by id desc";
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Product> ls = new ArrayList<>();
            while (rs.next()) {
                Product s = new Product(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        0,
                        rs.getString(7),
                        rs.getString(8));
                ls.add(s);
            }
            return ls;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static int getIdOfTheLastProduct() {
        String sql = "select top 1(id) from product_He160021\n"
                + "order by id desc";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static int getNumberALLProduct() {
        String sql = "select count(*) from product_He160021";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static int getNumberAllProductByCategoryId(int id) {
        String sql = "select count(*) from\n"
                + "(select * from product_He160021\n"
                + "where category_id = ?) as x";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static ArrayList<Product> getListProductByIndex(int index, int size) {
        ArrayList<Product> list = new ArrayList<>();

        int start = index * size - (size - 1);
        int end = index * size;

        String sql = "select * from \n"
                + "(select ROW_NUMBER() over (order by id asc) as r, * \n"
                + "from product_He160021) as x\n"
                + "where r between ? and ?";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product s = new Product(rs.getInt(2), // vi them cot row nen phai cong them 1
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getInt(6),
                        0,
                        rs.getString(8),
                        rs.getString(9));
                list.add(s);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static int getNumberAllProductBySearch(String search) {
        String sql = "select count(*) from\n"
                + "(select * \n"
                + "from product_He160021\n"
                + "where title like ?) as x";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static ArrayList<Product> getListProductBySearch(String search, int index, int size) {
        ArrayList<Product> list = new ArrayList<>();

        int start = index * size - (size - 1);
        int end = index * size;

        String sql = "select * from \n"
                + "(select ROW_NUMBER() over (order by id asc) as r, *\n"
                + "from (select * \n"
                + "from product_He160021\n"
                + "where title like ?) as s) as x\n"
                + "where r between ? and ?";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setInt(2, start);
            ps.setInt(3, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product s = new Product(rs.getInt(2), // vi them cot row nen phai cong them 1
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getInt(6),
                        0,
                        rs.getString(8),
                        rs.getString(9));
                list.add(s);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getNameBrand(int id) {
        String sql = "select name\n"
                + "from category_He160021\n"
                + "where id = (select category_id\n"
                + "from product_He160021\n"
                + "where id = ?)";
        try {
            conn = new DbContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
