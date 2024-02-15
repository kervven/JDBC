package com.kerven.coursejdbc;

import db.DB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class CoursejdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoursejdbcApplication.class, args);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                "INSERT INTO seller"
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)");

            st.setString(1, "Kerven Kildhery");
            st.setString(2, "kervensilvva@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("08/12/2000").getTime()));
            st.setDouble(4, 3000.0);
            st.setInt(5, 1);

            int rowsAffected = st.executeUpdate();

            System.out.println("Feito! Linhas afetadas: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
