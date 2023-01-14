package ru.itmo.hps.lab1.payment.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.itmo.hps.lab1.payment.entity.Payment;

import java.sql.ResultSet;
import java.util.List;

public interface PaymentRepository {
    // Маппер, превращающий строку из таблицы БД в объект класса Person
    RowMapper<Payment> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Payment(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("token"),
                resultSet.getDate("create_date"),
                resultSet.getString("description"));
    };

    List<Payment> findAll();

    Payment getById(Long id);

    int save(Payment person);

    int update(Payment person);

    int removeById(Long id);
    int deleteAll();
}