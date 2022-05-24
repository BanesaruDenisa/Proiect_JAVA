package config.DbRepository;

import config.DBConnection;
import courier.Courier;
import order.Client;
import order.Order;
import repository.OrderRepository;
import services.ClientService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                ClientService cls = new ClientService();
                List<Client> cl = cls.getAllClients();
                int id_o = resultSet.getInt("id_order");
                int id_b = resultSet.getInt("id_bill");
                int id_d = resultSet.getInt("id_del_comp");
                String email = resultSet.getString("email");
               // if(email == cls.)
              //  orders.add(new Order(id_o, id_b, id_d,cl));
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

}
