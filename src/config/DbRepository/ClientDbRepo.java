package config.DbRepository;

import config.DBConnection;
import order.Client;
import product.Products;
import repository.ClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDbRepo extends ClientRepository {

    private DBConnection dbconnection;

    public ClientDbRepo(DBConnection db) throws SQLException{
        this.dbconnection = db;
    }

    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "select * from clients";
        try(PreparedStatement preparedStatement = dbconnection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String phno = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                clients.add(new Client(fname, lname, phno, email, address));
            }
        }
            catch (SQLException e) {
                e.printStackTrace();
            }

            return clients;

    }

    public void save(List<Client> client) {
        String query = "insert into clients values (?, ?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = dbconnection.prepareStatement(query)) {

            int cnt=0;
            for(Client cl : client) {

                preparedStatement.setString(1, cl.getFirstName());
                preparedStatement.setString(2, cl.getLastName());
                preparedStatement.setString(3, cl.getPhonenumber());
                preparedStatement.setString(4, cl.getEmail());
                preparedStatement.setString(5, cl.getAddress());
                preparedStatement.addBatch();
                cnt ++;
                if(cnt % 100 == 0 || cnt == client.size()){
                    preparedStatement.executeBatch();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
