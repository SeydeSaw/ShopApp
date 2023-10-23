package com.example.shopapp.repository.mysql;

import com.example.shopapp.domain.entity.Product;
import com.example.shopapp.repository.ProductRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.shopapp.domain.database.MySqlConnector.getConnection;

public class MySqlProductRepository implements ProductRepository {

    @Override
    public List<Product> getAll() {
        try (Connection connection = getConnection()) { // получаем соединение с базой данных
            String query = "select * from product;";
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            List<Product> result = new ArrayList<>();

            while (resultSet.next()) {
                // собираем наш объект:
                long id = resultSet.getLong(1);//передаём либо номер колонки либо название колонки "id"
                String name = resultSet.getString(2);// "name"
                double price = resultSet.getDouble(3);// "price"
                result.add(new Product(id,name,price));
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getById(long id) {
        try (Connection connection = getConnection()) {
            String query = String.format("select * from product where id = %d;", id); //String.format так как нам надо вставить переменную в шаблон запроса (id)
            ResultSet resultSet = connection.createStatement().executeQuery(query); // executeQuery - когда хотим получить данные из БД

            resultSet.next(); // цикл while не нужен, т.к. возвращаем 1 строку
            // собираем наш объект:
            // id прописывать не надо, т.к. он уже прилетел в наш метод
            String name = resultSet.getString(2);
            double price = resultSet.getDouble(3);
            return new Product(id, name, price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(String name, double price) {
        try (Connection connection = getConnection()) {
            String query = String.format(Locale.US, "INSERT INTO `product` (`name`, `price`) " + // Locale.US - для того чтобы дробное число точно было передано в БД(не смотря на точку или запятую)
                    "VALUES ('%s', '%.2f');", name, price); //%s ,т.к. строка / %.2f дробное значение и 2 знака после запятой
            // ResultSet resultSet нам не нужен, т.к. мы  теперь делаем изменения в БД
            // поэтому сразу обращаемся к нашему объекту connection
            connection.createStatement().execute(query); // execute- когда хотим изменить данные в БД
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = getConnection()) {
            String query = String.format("DELETE FROM `product` WHERE id = '%d';", id);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}