package config.DbRepository;

import config.DBConnection;
import order.Client;
import order.Order;
import product.Products;
import repository.ProductsRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDbRepo extends ProductsRepository {
    private DBConnection dbconnection;

    public ProductDbRepo(DBConnection db) throws SQLException{
        this.dbconnection = db;
    }

    public List<Products> getAllProducts() throws SQLException {
        List<Products> products = new ArrayList<>();
        String query = "select * from products";
        try(PreparedStatement preparedStatement = dbconnection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

               // int id_p = resultSet.getInt("id_product");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int quant = resultSet.getInt("quantity");
                products.add(new Products(name, price, quant));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public void save(List<Products> product ) throws SQLException {
        String query = "insert into products values (null, ?, ?, ?)";
       // String query = "insert into products (name, price, quantity) value" + "('" + product.getName() + "', '"
        //                                                                           + product.getPrice() + "', '" + product.getQuantity() + "');";
        try(PreparedStatement preparedStatement = dbconnection.prepareStatement(query)) {

            int cnt=0;
            for(Products pr : product) {
                preparedStatement.setString(1, pr.getName());
                preparedStatement.setDouble(2, pr.getPrice());
                preparedStatement.setInt(3, pr.getQuantity());
                preparedStatement.addBatch();
                cnt++;
                if (cnt % 100 == 0 || cnt == product.size()) {
                    preparedStatement.executeBatch();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
