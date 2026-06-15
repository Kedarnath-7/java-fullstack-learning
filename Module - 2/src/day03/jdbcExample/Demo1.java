package day03.jdbcExample;

import java.sql.*;

public class Demo1 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/northerarc";
        String user = "postgres";
        String password = "12345";
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            System.out.println("Database served connected successfully....");

            // String sql = "Create table if not exists person(id serial primary key, name varchar(30) not null, email varchar(50) unique not null, age real);";

            // String sql = "insert into person(name, email) values('kedarnath', 'kedarnath@gmail.com'),('tony stark', 'ironman@avengers.com'),('peter parker', 'spiderman@avengers.com'),('steve rogers', 'captainamerica@avengers.com');";

            // String sql = "update person set email='kedarnath.nagaradone@gmail.com' where name='kedarnath'";

            //String sql = "delete from person where name='tony stark'";

            //String sql = "select * from person;";

            String sql = "select id, name from person order by name;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            System.out.println("Executing query: " + stmt);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("id") + " " + rs.getString("name"));
            }
        }catch (SQLException e){
            System.out.println("Failed to connect to the database");
            e.getStackTrace();
        }
    }
}
