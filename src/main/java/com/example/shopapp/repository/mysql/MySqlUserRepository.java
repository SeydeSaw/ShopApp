package com.example.shopapp.repository.mysql;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.domain.entity.Product;
import com.example.shopapp.domain.entity.User;
import com.example.shopapp.repository.UserRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.shopapp.domain.database.MySqlConnector.getConnection;

public class MySqlUserRepository implements UserRepository {
    @Override
    public List<User> getAll() {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM user AS u LEFT JOIN user_product AS up ON u.id = up.user_id LEFT JOIN product AS p ON up.product_id = p.id;";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            Map<String, User> map = new HashMap<>();

            while (resultSet.next()) {
                String username = resultSet.getString(2);
                long productId = resultSet.getLong(6);
                String productName = resultSet.getString(7);
                double productPrice = resultSet.getDouble(8);
                Product product = new Product(productId, productName, productPrice);

                if (map.containsKey(username)) {
                    map.get(username).getCart().addProduct(product);
                } else {
                    long userId = resultSet.getLong(1);
                    Cart cart = new Cart();

                    if (productName != null) {
                        cart.addProduct(product);
                    }

                    User user = new User(userId, username, cart);
                    map.put(username, user);
                }
            }

            return map.values().stream().toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getById(long id) {
        try (Connection connection = getConnection()) {
            String query = String.format("SELECT * FROM user as u left join user_product as up on u.id = up.user_id left join product as p on up.product_id = p.id where u.id = %d;", id);
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            User user = null;

            while (resultSet.next()) {
                long productId = resultSet.getLong(6);
                String productName = resultSet.getString(7);
                double productPrice = resultSet.getDouble(8);
                Product product = new Product(productId, productName, productPrice);

                if (user == null) {
                    String username = resultSet.getString(2);
                    user = new User(id, username, new Cart());
                }

                if (productName != null) {
                    user.getCart().addProduct(product);
                }
            }

            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(String name) {
        try (Connection connection = getConnection()) {
            String query = String.format("INSERT INTO `user` (`name`) VALUES ('%s');", name);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = getConnection()) {
            String query = String.format("DELETE FROM `user` WHERE (`id` = '%d');", id);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addToCartById(long userId, long productId) {
        try (Connection connection = getConnection()) {
            String query = String.format("INSERT INTO `user_product` (`user_id`, `product_id`) VALUES ('%d', '%d');", userId, productId);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFromCart(long userId, long productId) {
        try (Connection connection = getConnection()) {
            String query = String.format("DELETE FROM `user_product` WHERE (`user_id` = '%d' and `product_id` = '%d');", userId, productId);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearCart(long userId) {
        try (Connection connection = getConnection()) {
            String query = String.format("DELETE FROM `user_product` WHERE (`user_id` = '%d');", userId);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}