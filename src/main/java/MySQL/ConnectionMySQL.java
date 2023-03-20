package MySQL;

import java.sql.*;


public class ConnectionMySQL {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/midtermPoyect";
        String username = "ironhacker";
        String password = "Ir0nh4ck3r!";
        Connection connection = null;
        String bases = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            // INSERT
            String queryInsert = "INSERT INTO Employee VALUES (1, 'Ruth', 'Telleri')";
            if (statement.executeUpdate(queryInsert) > 0) {
                System.out.println("Se ha insertado nuevo elemento");
            }

            // UPDATE
            String queryUpdate = "UPDATE Employee SET salary = 20000, WHERE id = 25";
            if (statement.executeUpdate(queryUpdate) > 0) {
                System.out.println("Se ha actualizado nuevo elemento");
            }

            // DELETE
            String queryDelete = "DELETE FROM Employee WHERE id = 25";
            if (statement.executeUpdate(queryDelete) > 0) {
                System.out.println("Se ha borrado elemento");
            }

            // FIND
            ResultSet resultSet = statement.executeQuery("select * from student");
            boolean hasNext = resultSet.next();  // Si es true hay resultado, si es false no hay resultado
            while (resultSet.next()) {
                bases += resultSet.getString(1) + "\n";
            }

            // PREPARED STATEMENT
            String query = "SELECT * FROM Employee WHERE Salary > ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, 25000);  // En el parametro 1 pones salario de 25000
            ResultSet resultSet1 = preparedStatement.executeQuery();

            String queryUpdate2 = "UPDATE Employee SET salary = ?, WHERE id = ?";
            PreparedStatement preparedUpdateStatement = connection.prepareStatement(queryUpdate2);
            preparedUpdateStatement.setDouble(1, 25500);
            preparedUpdateStatement.setInt(2, 25);
            preparedUpdateStatement.executeUpdate(queryUpdate);


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            assert connection != null;
            connection.close();
        }
    }
}
