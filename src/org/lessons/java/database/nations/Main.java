package org.lessons.java.database.nations;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:8889/db_nations";
        String user = "root";
        String password = "root";

        // Creazione connessione
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            // esplicito la query e la assegno ad una variabile query
            String query = "SELECT c.country_id,c.name as Country_Name, r.name as Region_Name, c2.name as Continent_Name FROM countries c  JOIN regions r ON r.region_id = c.region_id  JOIN continents c2 ON c2.continent_id =r.continent_id  ORDER BY c.name ASC;";
            // preparo uno statement sql
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                // eseguo la query
                preparedStatement.execute();
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()){
                        String countryId = resultSet.getString("country_id");
                        String countryName = resultSet.getString("Country_Name");
                        String regionName = resultSet.getString("Region_Name");
                        String continentName = resultSet.getString("Continent_Name");
                        System.out.println(countryId + " " + countryName + " " + regionName + " " + continentName);
                    }
                } catch (SQLException e){
                    System.out.println("Unable to execute query");
                }
            } catch (SQLException e){
                System.out.println("Unable to create statement");
            }
        } catch (SQLException e){
            System.out.println("Unable to connect to database");
            e.printStackTrace();
        }
    }
}
