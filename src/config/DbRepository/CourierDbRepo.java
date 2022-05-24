package config.DbRepository;

import config.DBConnection;
import courier.Courier;
import order.Client;
import order.Order;
import repository.CourierRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void deleteC(int id) {
        String query = "Delete from courier where id_courier = ?";
        try (PreparedStatement preparedStatement = dbconnection.prepareStatement(query)) {

           preparedStatement.setInt(1, id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
