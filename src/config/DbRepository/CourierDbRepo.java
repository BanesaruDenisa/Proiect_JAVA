package config.DbRepository;

import config.DBConnection;
import courier.Courier;
import order.Client;
import order.Order;
import repository.CourierRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourierDbRepo extends CourierRepository {

    private DBConnection dbconnection;

    public CourierDbRepo(DBConnection db) throws SQLException {
        this.dbconnection = db;
    }


    public List<Courier> getAllCouriers() throws SQLException {
        List<Courier> couriers = new ArrayList<>();
        String query = "select * from courier";
        try (PreparedStatement preparedStatement = dbconnection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_courier");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String phno = resultSet.getString("phone_number");

                couriers.add(new Courier(id, fname, lname, phno));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return couriers;

    }

    public void save(List<Courier> courier) throws SQLException {
        String query = "insert into courier(id_courier, first_name, last_name, phone_number)" + " values (?, ?, ?, ?)";


        try (PreparedStatement preparedStatement = dbconnection.prepareStatement(query)) {

            int cnt = 0;
            for (Courier cr : courier) {
                preparedStatement.setInt(1, cr.getIdCourier());
                preparedStatement.setString(2, cr.getFirstName());
                preparedStatement.setString(3, cr.getLastName());
                preparedStatement.setString(4, cr.getPhonenumber());
                preparedStatement.addBatch();
                cnt++;
                if (cnt % 100 == 0 || cnt == courier.size()) {
                    preparedStatement.executeBatch();
                }
            }
            } catch(SQLException e){
                e.printStackTrace();
            }

    }

    public void deleteC(Courier courier) {

        String query = "Delete from courier where id_courier = ?";
        System.out.println("Enter the courier id to be deleted: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        try (PreparedStatement preparedStatement = dbconnection.prepareStatement(query)) {


                    preparedStatement.setInt(1, id);
                    preparedStatement.execute();
                    System.out.println("Courier deleted! ");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateCr(Courier courier) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the courier id to be updated: ");
        int id = scanner.nextInt();
        System.out.println("Enter the new address: ");
        String phn = scanner.next();
        String query = "Update courier set phone_number=? where id_courier = ?";
        try (PreparedStatement preparedStatement = dbconnection.prepareStatement(query)) {
            preparedStatement.setString(1, phn);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            System.out.println("Courier updated !");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
