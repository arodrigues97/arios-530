package org.arios.game.system.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.arios.game.system.SystemLogger;
import org.arios.game.system.SystemManager;
import org.arios.game.system.mysql.impl.GroundSpawnSQLHandler;
import org.arios.game.system.mysql.impl.NPCDropSQLHandler;
import org.arios.game.system.mysql.impl.NPCSpawnSQLHandler;
import org.arios.game.system.mysql.impl.ShopSQLHandler;

/**
 * Manages the sql connections.
 * @author Vexia
 */
public final class SQLManager {

	/**
	 * The database URL.
	 */
	public static final String DATABASE_URL = "167.114.128.71:3306/arios_global";

	/**
	 * The username of the user.
	 */
	private static final String USERNAME = "arios_user";

	/**
	 * The password of the user.
	 */
	private static final String PASSWORD = "jdkarios4332";

	/**
	 * IF the sql manager is initialized.
	 */
	private static boolean initialized;

	/**
	 * Constructs a new {@code SQLManager} {@code Object}
	 */
	public SQLManager() {
		/**
		 * empty.
		 */
	}

	/**
	 * Initializes the sql manager.
	 */
	public static void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			initialized = false;
			return;
		}
		initialized = true;
		SystemManager.getSystemConfig().reload();
	}

	/**
	 * Parses data from the database for the server post plugin loading.
	 */
	public static void postPlugin() {
		try {
			new ShopSQLHandler().parse();
			new NPCDropSQLHandler().parse();
			new NPCSpawnSQLHandler().parse();
			new GroundSpawnSQLHandler().parse();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets a connection from the pool.
	 * @return The connection.
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://" + DATABASE_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			SystemLogger.error(SQLManager.class, "Error: Mysql error message=" + e.getMessage() + ".");
		}
		return null;
	}

	/**
	 * Releases the connection so it's available for usage.
	 * @param connection The connection.
	 */
	public static void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the initialized.
	 * @return the initialized
	 */
	public static boolean isInitialized() {
		return initialized;
	}

	/**
	 * Sets the bainitialized.
	 * @param initialized the initialized to set.
	 */
	public static void setInitialized(boolean initialized) {
		SQLManager.initialized = initialized;
	}

}
