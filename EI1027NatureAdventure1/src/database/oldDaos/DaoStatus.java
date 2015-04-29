package database.oldDaos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import classes.Status;

/**
 * Dao para gestionar los estados de las reservas.
 */
public class DaoStatus {
	private final static Logger Log = Logger.getLogger(DaoStatus.class.getName());
 
	
	public DaoStatus() {
		super();
	}

	/**
	 * Obtener todos los estados de la db
	 * 
	 * @return Map con los estados
	 */
	public Map<Integer, Status> getStats() {
		Connection conn = createConnection();
		
		Map<Integer, Status> stats = new HashMap<Integer, Status>();
		ResultSet result = null;
		PreparedStatement statement = null;
		// Peticion a la DB
		try {
			statement = conn.prepareStatement("SELECT * FROM Status");
			result = statement.executeQuery();
			
			while (result.next()) {
				Status status = new Status();
				
				status.setIDbooking(result.getInt("inneridbooking"));
				status.setDateRevision(result.getDate("dateRevision"));
				status.setStatus(result.getString("status"));
				status.setSsNumber(result.getString("ssNumber"));
				
					
				stats.put(status.getIDbooking(),status);
			}
		} catch (SQLException e) {
			Log.severe("Error en el preparedStatement");
			e.printStackTrace(); return null;
			
		} finally {
			closeResult(result);
			closeStatement(statement);
			closeConnection(conn);
		}
		return stats;
	}




	/**
	 * Obtener un estado de la db
	 * 
	 * @param IDstatus La id del status que coincide con la id de la reserva
	 * 
	 * @return Estado buscado o null.
	 */

	public Status getStatus(int IDstatus) {
		Connection conn = createConnection();

		PreparedStatement statement = null;
		ResultSet result = null;
		// Peticion a la db
		try {
			statement = conn.prepareStatement("SELECT * FROM Status WHERE inneridbooking=?");
			
			statement.setInt(1, IDstatus);

			result = statement.executeQuery();
			// Si se encuentra un resultado se crea la respuesta
			if (result.next()) {
				Status status = new Status();;
				
				status.setIDbooking(result.getInt("inneridbooking"));
				status.setDateRevision(result.getDate("dateRevision"));
				status.setStatus(result.getString("status"));
				status.setSsNumber(result.getString("ssNumber"));
					
				return status;
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
	 * Registrar un nuevo estado en la db
	 * 
	 * @param status Instancia de Status con los datos a anadir
	 */

	public void addStatus(Status status) {
		Connection conn = createConnection();
		PreparedStatement statement = null;
		
		// Sentencia SQL para el add
		try {
			statement = conn
					.prepareStatement("INSERT INTO Status(innerIdBooking, daterevision, status, ssNumber) "
							+ "values(?, ?, ?, ?)");
			statement.setInt(1, status.getIDbooking());
			statement.setDate(2, status.getDateRevision());
			statement.setString(3, status.getStatus());
			statement.setString(4, status.getSsNumber());
			
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
	 * Actualizar los datos de un estado
	 * 
	 * @param status Instancia de Status con los datos actualizados
	 */
	public void updateStatus(Status status) {
		Connection conn = createConnection();
		PreparedStatement statement = null;
		// Sentencia SQL para el update
		try {
			statement = conn.prepareStatement("UPDATE Status " + "SET "
					+ "innerIdBooking = ?," 
					+ "dateRevision = ?," 
					+ "status = ?,"
					+ "ssNumber = ?" 
					+ " WHERE innerIdBooking = ?");
			
			statement.setInt(1, status.getIDbooking());
			statement.setDate(2, status.getDateRevision());
			statement.setString(3, status.getStatus());
			statement.setString(4, status.getSsNumber());
			statement.setInt(5, status.getIDbooking());

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
	 * Eliminar un estado de la db
	 * 
	 * @param IDstatus La ID del estado
	 */

	public void deleteStatus(int IDstatus) {
		Connection conn = createConnection();
		PreparedStatement statement = null;
		// Sentencia SQL para borrar
		try {
			statement = conn.prepareStatement("DELETE FROM Status WHERE inneridbooking=?");
			statement.setInt(1, IDstatus);
			
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
