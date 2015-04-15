package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import classes.Booking;
import classes.Status;
import exceptions.InvalidGroupSizeException;
import exceptions.InvalidPriceException;

/**
 * Dao para gestionar las reservas con la base de datos.
 */
public class DaoBooking {
	private final static Logger Log = Logger.getLogger(DaoBooking.class.getName());
	private DaoInstructor daoInstructor;
	private DaoActivity daoActivity;
	private DaoClient daoClient;
	private DaoStatus daoStatus;

	
	//Las reservas estan relacionadas con el monitor, la actividad y el cliente.
	public DaoBooking() {
		super();
		daoInstructor = new DaoInstructor();
		daoActivity = new DaoActivity();
		daoClient = new DaoClient();
		daoStatus = new DaoStatus();
	}

	/**
	 * Obtener todas las reservas registradas en la base de datos.
	 * 
	 * @return Map con las reservas.
	 */
	public Map<Integer,Booking> getBookings() {
		Connection conn = createConnection();
		
		Map<Integer,Booking> reservas = new HashMap<Integer, Booking>();
		ResultSet result = null;
		PreparedStatement statement = null;
		// Peticion a la DB
		try {
			statement = conn.prepareStatement("SELECT * FROM Booking");
			result = statement.executeQuery();
			
			while (result.next()) {
				Booking reserva = new Booking();
				reserva.setInnerIdBooking(result.getInt("innerIdBooking"));
				reserva.setGroupSize(result.getInt("groupSize"));
				reserva.setDateActivity(result.getDate("dateActivity"));
				reserva.setDateCreation(result.getDate("dateCreation"));
				reserva.setClient(daoClient.getClient(result.getString("clientId")));
				reserva.setPrice(result.getFloat("price"));
				reserva.setActivity(daoActivity.getActivity(result.getInt("idAct")));
				reserva.setInformation(result.getString("information"));
				reserva.setInstructor(daoInstructor.getInstructor(result.getString("ssNumber")));
				reserva.setIdBooking(result.getInt("idBooking"));
				reserva.setStatus(daoStatus.getStatus(result.getInt("innerIdBooking")));
					
				reservas.put(reserva.getInnerIdBooking(),reserva);
			}
		} catch (SQLException e) {
			Log.severe("Error en el preparedStatement");
			e.printStackTrace(); return null;
			
		} catch (InvalidGroupSizeException e) {
			e.printStackTrace(); return null;
			
		} catch (InvalidPriceException e) {
			e.printStackTrace(); return null;
		} finally {
			closeResult(result);
			closeStatement(statement);
			closeConnection(conn);
		}
		return reservas;
	}




	/**
	 * Obtener una reserva registrada por un usuario
	 * 
	 * @param innerIdBooking El identificador interno de la reserva
	 * 
	 * @return Reserva buscada o null.
	 */

	public Booking getBooking(int innerIdBooking) {
		Connection conn = createConnection();

		PreparedStatement statement = null;
		ResultSet result = null;
		// Peticion a la db
		try {
			statement = conn.prepareStatement("SELECT * FROM Booking WHERE innerIdBooking=?");
			
			statement.setInt(1, innerIdBooking);

			result = statement.executeQuery();
			// Si se encuentra un resultado se crea la respuesta
			if (result.next()) {
				Booking reserva = new Booking();
				
				reserva.setInnerIdBooking(result.getInt("innerIdBooking"));
				reserva.setGroupSize(result.getInt("groupSize"));
				reserva.setDateActivity(result.getDate("dateActivity"));
				reserva.setDateCreation(result.getDate("dateCreation"));
				reserva.setClient(daoClient.getClient(result.getString("clientId")));
				reserva.setPrice(result.getFloat("price"));
				reserva.setActivity(daoActivity.getActivity(result.getInt("idAct")));
				reserva.setInformation(result.getString("information"));
				reserva.setInstructor(daoInstructor.getInstructor(result.getString("ssNumber")));
				reserva.setIdBooking(result.getInt("idBooking"));
				reserva.setStatus(daoStatus.getStatus(result.getInt("innerIdBooking")));
				
					
				return reserva;
			}
		return null; //Sino se devuelve nulo
			
		} catch (SQLException e) {
			Log.severe("Error en el preparedStatement");
			e.printStackTrace(); return null;
			
		} catch (InvalidPriceException e) {
			e.printStackTrace(); return null;
			
		} catch (InvalidGroupSizeException e) {
			e.printStackTrace(); return null;
			
		} finally {
			closeResult(result);
			closeStatement(statement);
			closeConnection(conn);
		}
	}

	/**
	 * Registrar una reserva en la base de datos.
	 * 
	 * @param reserva  Reserva a anadir en la db
	 */

	public void addBooking(Booking reserva) {
		Connection conn = createConnection();
		PreparedStatement statement = null;
		
		// Sentencia SQL para el add
		try {
			statement = conn
					.prepareStatement("INSERT INTO Booking(innerIdBooking, groupSize, dateActivity, dateCreation, clientId, price, idAct, information, ssNumber, idBooking) "
							+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setInt(1, reserva.getInnerIdBooking());
			statement.setInt(2, reserva.getGroupSize());
			statement.setDate(3, reserva.getDateActivity());
			statement.setDate(4, reserva.getDateCreation());
			statement.setString(5, reserva.getClient().getClientId());
			statement.setFloat(6, reserva.getPrice());
			statement.setInt(7, reserva.getActivity().getIdAct());
			statement.setString(8, reserva.getInformation());
			statement.setString(9, reserva.getInstructor().getSsNumber());
			statement.setInt(10, reserva.getIdBooking());
			
			//Nuevo estado vacio
			Status status = new Status();
			status.setIDbooking(reserva.getInnerIdBooking());
			daoStatus.addStatus(status);
			
			
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
	 * Actualizar los datos de una reserva en la base de datos.
	 * 
	 * @param reserva Objeto reserva con los parametros cambiados
	 */
	public void updateBooking(Booking reserva) {
		Connection conn = createConnection();
		PreparedStatement statement = null;
		// Sentencia SQL para el update
		try {
			statement = conn.prepareStatement("UPDATE Booking " + "SET "
					+ "innerIdBooking = ?," 
					+ "groupSize = ?," 
					+ "dateActivity = ?,"
					+ "dateCreation = ?," 
					+ "clientId = ?," 
					+ "price = ?,"
					+ "idAct = ?," 
					+ "information = ?," 
					+ "ssNumber = ?,"
					+ "idBooking = ?" 
					+ " WHERE innerIdBooking = ?");
			
			statement.setInt(1, reserva.getInnerIdBooking());
			statement.setInt(2, reserva.getGroupSize());
			statement.setDate(3, reserva.getDateActivity());
			statement.setDate(4, reserva.getDateCreation());
			statement.setString(5, reserva.getClient().getClientId());
			statement.setFloat(6, reserva.getPrice());
			statement.setInt(7, reserva.getActivity().getIdAct());
			statement.setString(8, reserva.getInformation());
			statement.setString(9, reserva.getInstructor().getSsNumber());
			statement.setInt(10, reserva.getIdBooking());
			statement.setInt(11, reserva.getInnerIdBooking());

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
	 * Eliminar una reserva de la base de datos.
	 * 
	 * @param innerIdBooking ID interna de la reserva
	 */

	public void deleteBooking(int innerIdBooking) {
		Connection conn = createConnection();
		PreparedStatement statement = null;
		// Sentencia SQL para borrar
		try {
			statement = conn.prepareStatement("DELETE FROM Booking WHERE innerIdBooking=?");
			statement.setInt(1, innerIdBooking);
			
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
		// Obtener la conexion con la DB
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
