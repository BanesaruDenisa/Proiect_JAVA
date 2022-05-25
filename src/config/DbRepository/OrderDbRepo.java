package config.DbRepository;

import config.DBConnection;
import courier.Courier;
import order.Client;
import order.Order;
import product.Products;
import repository.OrderRepository;
import services.ClientService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDbRepo extends OrderRepository {

    private DBConnection dbconnection;

    public OrderDbRepo(DBConnection db) throws SQLException {
        this.dbconnection = db;
    }

    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "select * from orders";
        try(PreparedStatement preparedStatement = dbconnection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                List<Client> cl = new ArrayList<>();
                int id_o = resultSet.getInt("id_order");
                int id_b = resultSet.getInt("id_bill");
                int id_d = resultSet.getInt("id_del_comp");
                String email = resultSet.getString("email");

                orders.add(new Order(id_o, id_b, id_d, email));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }


    public void save(List<Order> order) {
        String query = "insert into orders values (null, ?, ?, ?)";

        try(PreparedStatement preparedStatement = dbconnection.prepareStatement(query)) {

            int cnt=0;
            for(Order or : order) {
                preparedStatement.setInt(1, or.getIdBill());
                preparedStatement.setInt(2, or.getIdDelComp());
                preparedStatement.setString(3, or.getClient().getEmail());
                preparedStatement.addBatch();
                cnt++;
                if (cnt % 100 == 0 || cnt == order.size()) {
                    preparedStatement.executeBatch();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteO(Order order) {

        String query = "Delete from orders where id_order = ?";
        System.out.println("Enter the order id to be deleted: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        try (PreparedStatement preparedStatement = dbconnection.prepareStatement(query)) {


            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            System.out.println("Order deleted! ");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void update(Order order) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the order id to be updated: ");
        int id = scanner.nextInt();
        System.out.println("Enter the new email: ");
        String email = scanner.next();
        String query = "Update orders set email=? where id_order = ?";
        try (PreparedStatement preparedStatement = dbconnection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            System.out.println("Order updated !");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
