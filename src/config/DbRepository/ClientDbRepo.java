package config.DbRepository;

import config.DBConnection;
import courier.Courier;
import order.Client;
import order.Order;
import product.Products;
import repository.ClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void deleteCl(Client client) {

        String query = "Delete from clients where email = ?";
        System.out.println("Enter the client email to be deleted: ");
        Scanner sc = new Scanner(System.in);
        String email = sc.next();
        try (PreparedStatement preparedStatement = dbconnection.prepareStatement(query)) {


            preparedStatement.setString(1, email);
            preparedStatement.execute();
            System.out.println("Client deleted! ");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateCl(Client client) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the client email to be updated: ");
        String email = scanner.next();
        System.out.println("Enter the new address: ");
        String address = scanner.next();
        String query = "Update clients set address=? where email = ?";
        try (PreparedStatement preparedStatement = dbconnection.prepareStatement(query)) {
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, email);
            preparedStatement.execute();
            System.out.println("Client updated !");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
