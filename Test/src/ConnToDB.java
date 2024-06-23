import java.sql.*;
import java.util.HashMap;

public class ConnToDB {
    // Begin Class

    private Connection connect = null;
    private Statement statement = null;
    // private PreparedStatement prep = null;
    private ResultSet res = null;
    HashMap<String,String> hashmap = new HashMap<String,String>();

    ConnToDB(String choice) {

        if (choice.equalsIgnoreCase("for Login")) {
            this.config();
            this.selectToLogin();
        }
        else{
            this.config();
        }

//		if (choice.equalsIgnoreCase("for SignUp")){
//			this.config("person_DB","Reese","Dashwood");
//			this.insertData();
//		}

    }

    private void config () {
        // Begin config()

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://roundhouse.proxy.rlwy.net:40039/railway";
            String username = "root";
            String password = "YNBQVsoIkrZYZvtQGGRpNzPXCiJwjvJc";

            connect = DriverManager.getConnection(url, username, password);
            statement = connect.createStatement();

            String dbName = connect.getCatalog();
            System.out.println("Database name: " + dbName);
            // Log message
            System.out.println("Connected to the database");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // End config()
    }

    private void selectToLogin () {
        // Begin select()

        try {
            String sqll = "select email,password from users";

            res = statement.executeQuery(sqll);
            while (res.next()) {

                String email = res.getString("email");
                String passwd = res.getString("password");

                hashmap.put(email,passwd);

            }

            connect.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        // End select()
    }

    void insertData (String fname,String lname,String email,String passwd) {
        // Begin insertData()

        try {
            String sqll = "insert into users (first_name,last_name,email,password) values ('"+ fname +"','"+ lname +"','"+ email +"','"+ passwd +"')";
            connect.prepareStatement(sqll).executeUpdate();

            connect.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        // End insertData()
    }

    void updateData () {
        // Begin updateData()

        try {
            String sqll = "";
            connect.prepareStatement(sqll).executeUpdate();

            connect.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        // End updateData()
    }

    void deleteData () {
        // Begin deleteData()

        try {
            String sqll = "";
            connect.prepareStatement(sqll).executeUpdate();

            connect.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        // End deleteData()
    }

// End Class
}
