package foodstock.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import foodstock.bo.Inventory;
import foodstock.bo.Product;

public class DatabaseManager {

	private static DatabaseManager instance;
	private static final String url = "jdbc:mysql://localhost/home_stock";
	private static final String user = "root";
	private static final String password = "toor";

	private Connection connection;

	public static final DatabaseManager getInstance() {
		if (instance == null) {
			instance = new DatabaseManager(url, user, password);
		}
		return instance;
	}

	private DatabaseManager(String url, String user, String password) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Product readProduct(String skn) {

		Product product = null;
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("SELECT skn, name, description FROM products WHERE skn = ?");
			statement.setString(1, skn);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
//				String skn = result.getString(0);
				String name = result.getString(2);
				String description = result.getString(3);

				product = new Product(skn, name, description);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;
	}

	public boolean createProduct(Product product) {

		boolean result = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products VALUE(?, ?, ? )");
			preparedStatement.setString(1, product.getSkn());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.executeUpdate();
			result = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Inventory> readInventory(String skn, Date limitExpirationDate) {

		List<Inventory> inventoryList = new ArrayList<>();

		try {
			// parse expiration date
			java.sql.Date sqlDate = new java.sql.Date(limitExpirationDate.getTime());

			PreparedStatement preparedStatement = connection.prepareStatement("SELECT skn, quantity, purchase_date, expiration_date FROM inventory WHERE skn = ? AND expiration_date >= NOW() AND expiration_date <= ? ");
			preparedStatement.setString(1, skn);
			preparedStatement.setDate(2, sqlDate);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
//				String skn = result.getString(0);
				int quantity = result.getInt(2);
				Date purchaseDate = result.getDate(3);
				Date expirationDate = result.getDate(4);
				Inventory inventory = new Inventory(skn, quantity, purchaseDate, expirationDate);
				inventoryList.add(inventory);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return inventoryList;
	}

	public boolean createInventory(Inventory inventory) {

		boolean result = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO inventory (skn, quantity, purchase_date, expiration_date) VALUES(?, ?, ?, ?)");
			preparedStatement.setString(1, inventory.getSkn());
			preparedStatement.setInt(2, inventory.getQuantity());
			preparedStatement.setDate(3, new java.sql.Date(inventory.getPurchaseDate().getTime()));
			preparedStatement.setDate(4, new java.sql.Date(inventory.getExpirationDate().getTime()));
			preparedStatement.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
