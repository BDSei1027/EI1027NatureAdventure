package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import classes.Client;

/**
 * Dao para gestionar los clientes de la base de datos.
 */
public class DaoClient {
	private final static Logger Log = Logger.getLogger(DaoClient.class.getName());

	
	public DaoClient() {
		super();
	}

	/**
	 * Obtener todos los clientes registrados en la db.
	 * 
	 * @return Map con los clientes
	 */
	public Map<String,Client> getClients() {
		Connection conn = createConnection();
		
		Map<String, Client> clientes = new HashMap<String, Client>();
		ResultSet result = null;
		PreparedStatement statement = null;
		// Peticion a la DB
		try {
			statement = conn.prepareStatement("SELECT * FROM Client");
			result = statement.executeQuery();
			
			while (result.next()) {
				Client client = new Client();
				
				client.setClientId(result.getString("clientId"));
				client.setClientName(result.getString("clientName"));
				client.setClientLastName(result.getString("clientLastName"));
				client.setClientEmail(result.getString("clientEmail"));
				
					
				clientes.put(client.getClientId(),client);
			}
		} catch (SQLException e) {
			Log.severe("Error en el preparedStatement");
			e.printStackTrace(); return null;
			
		} finally {
			closeResult(result);
			closeStatement(statement);
			closeConnection(conn);
		}
		return clientes;
	}




	/**
	 * Obtener un cliente de la db
	 * 
	 * @param clientID ID del cliente
	 * 
	 * @return Cliente buscado o null.
	 */

	public Client getClient(String clientId) {
		Connection conn = createConnection();

		PreparedStatement statement = null;
		ResultSet result = null;
		// Peticion a la db
		try {
			statement = conn.prepareStatement("SELECT * FROM Client WHERE clientId=?");
			
			statement.setString(1, clientId);

			result = statement.executeQuery();
			// Si se encuentra un resultado se crea la respuesta
			if (result.next()) {
				Client client = new Client();;
				
				client.setClientId(result.getString("clientId"));
				client.setClientName(result.getString("clientName"));
				client.setClientLastName(result.getString("clientLastName"));
				client.setClientEmail(result.getString("clientEmail"));
					
				return client;
			}
		return null; //Sino se devuelve nulo
			
		} catch (SQLException e) {
			Log.severe("Error en el preparedStatement");
			e.printStackTrace(); return null;
			
		} finally {
			closeResult(result);
			closeStatement(statement);
			closeConnection(conn);
		}
	}

	/**
	 * Registrar un nuevo cliente en la db
	 * 
	 * @param cliente Instancia de Cliente con los datos a anadir
	 */

	public void addClient(Client client) {
		Connection conn = createConnection();
		PreparedStatement statement = null;
		
		// Sentencia SQL para el add
		try {
			statement = conn
					.prepareStatement("INSERT INTO Client(clientId, clientName, clientLastName, clientEmail) "
							+ "values(?, ?, ?, ?)");
			statement.setString(1, client.getClientId());
			statement.setString(2, client.getClientName());
			statement.setString(3, client.getClientLastName());
			statement.setString(4, client.getClientEmail());
			
			statement.execute();
		} catch (SQLException e) {
			Log.severe("Error al ejecutar el statement");
			e.printStackTrace();
			
		} finally { // Cerrando los streams y conexion
			closeStatement(statement);
			closeConnection(conn);
		}

	}

	/**
	 * Actualizar los datos de un cliente
	 * 
	 * @param client Instancia de cliente con los datos actualizados
	 */
	public void updateClient(Client client) {
		Connection conn = createConnection();
		PreparedStatement statement = null;
		// Sentencia SQL para el update
		try {
			statement = conn.prepareStatement("UPDATE Client " + "SET "
					+ "clientId = ?," 
					+ "clientName = ?," 
					+ "clientLastName = ?,"
					+ "clientEmail = ?" 
					+ " WHERE clientId = ?");
			
			statement.setString(1, client.getClientId());
			statement.setString(2, client.getClientName());
			statement.setString(3, client.getClientLastName());
			statement.setString(4, client.getClientEmail());
			statement.setString(5, client.getClientId());

			statement.execute();
		} catch (SQLException e) {
			Log.severe("Error al ejecutar el statement");
			e.printStackTrace();
		}
		// Cerrar todos los streams y la conexion
		finally {
			closeStatement(statement);
			closeConnection(conn);
		}
	}

	/**
	 * Eliminar un cliente de la db
	 * 
	 * @param clientId La ID del cliente
	 */

	public void deleteClient(String clientId) {
		Connection conn = createConnection();
		PreparedStatement statement = null;
		// Sentencia SQL para borrar
		try {
			statement = conn.prepareStatement("DELETE FROM Client WHERE clientId=?");
			statement.setString(1, clientId);
			
			statement.execute();
			
		} catch (SQLException e) {
			Log.severe("Error al ejecutar el statement");
			e.printStackTrace();
		} finally {
			closeStatement(statement);
			closeConnection(conn);
		}
	}
	
	/**
	 * Metodo que crea la conexion con la db
	 * 
	 * @return la conexion creada
	 */
	private Connection createConnection() {
		Connection conn = null;
		// Obtener la conexión con la DB
		try {
			conn = ConnectionManager.getConnection();
		} catch (ClassNotFoundException e) {
			Log.severe("Driver JDBC no encontrado");
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			Log.severe("Error creando la conexion con la DB");
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	/**
	 * Metodo que cierra una conexion dada
	 * 
	 */
	private void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			Log.warning("Error cerrando la conexion");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que cierra un statement creado
	 * 
	 */
	
	private void closeStatement(PreparedStatement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				Log.warning("Error cerrando el statement");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metodo que cierra un set de resultado creado
	 * 
	 */
	private void closeResult(ResultSet result) {
		if (result != null) {
			try {
				result.close();
			} catch (SQLException e) {
				Log.warning("Error cerrando el result set");
				e.printStackTrace();
			}
		}
	}
}
