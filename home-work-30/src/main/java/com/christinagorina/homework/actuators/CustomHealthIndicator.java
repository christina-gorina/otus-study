package com.christinagorina.homework.actuators;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
@RequiredArgsConstructor
public class CustomHealthIndicator implements HealthIndicator {

    private final DataSource dataSource;

    @Override
    public Health health() {
        try(Connection conn = dataSource.getConnection()){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select COUNT(*) AS rowcount from book");
            rs.next();
            int count = rs.getInt("rowcount");
            rs.close();
            if(count == 0){
                return Health.down().status(Status.DOWN)
                        .withDetail("message", "База библиотеки пуста!").build();
            }

        } catch (SQLException exception) {
            return Health.outOfService().withException(exception).build();
        }
        return Health.up().withDetail("message", "Наполнение базы данных корректно!").build();
    }
}
