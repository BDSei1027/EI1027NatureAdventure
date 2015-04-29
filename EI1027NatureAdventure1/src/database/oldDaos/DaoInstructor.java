package database.oldDaos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import classes.Instructor;

/**
 * Dao para gestionar los instructores con la base de datos.
 */
public class DaoInstructor {
	private final static Logger Log = Logger.getLogger(DaoInstructor.class
			.getName());

	public DaoInstructor() {

	}

	/**
	 * Obtener todas los monitores registrados en la base de datos.
	 * 
	 * @return Map con los monitores.
	 */
	public Map<String,Instructor> getInstructors() {
		Connection conn = getConnection();

		Map<String,Instructor> monitores = new HashMap<String,Instructor>();
		PreparedStatement statement = null;
		ResultSet result = null;
		// Petició a la DB
		try {
			statement = conn
					.prepareStatement("SELECT ssNumber, idNumber, name, lastname, email, telephone, isActive FROM Instructor");
			result = statement.executeQuery();
			while (result.next()) {
				Instructor monitor = new Instructor();
				monitor.setSsNumber(result.getString("ssNumber"));
				monitor.setIdNumber(result.getString("idNumber"));
				monitor.setName(result.getString("name"));
				monitor.setLastName(result.getString("lastname"));
				monitor.setEmail(result.getString("email"));
				monitor.setTelephone(result.getString("telephone"));
				monitor.setActive(result.getBoolean("isActive"));
				monitor.setActivities(getActivitiesInstructor(monitor.getSsNumber()));
				monitores.put(monitor.getSsNumber(),monitor);
			}
		} catch (SQLException e) {
			Log.severe("Error al preparedStatement");
			e.printStackTrace();
			return null;
		} finally {
			// Tancar el result
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					Log.warning("Error tancant ResultSet");
					e.printStackTrace();
				}
			}
			// Tancar el statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					Log.warning("Error tancant PreparedStatement");
					e.printStackTrace();
				}
			}
			// Tancar la conexió de la DB
			try {
				conn.close();
			} catch (SQLException e) {
				Log.warning("Error tancant connexió JDBC");
				e.printStackTrace();
			}
		}
		return monitores;
	}

	/**
	 * Obtener las actividades que puede realizar un instructor
	 * @param ssNumber
	 * @return Lista con los identificadores de la actividad que puede realizar
	 */
	private List<Integer> getActivitiesInstructor(String ssNumber) {
		Connection conn = getConnection();
        List<Integer> listaActividades = new LinkedList<Integer>();
        PreparedStatement statement = null;
        ResultSet result = null;
        // Petició a la DB
        try {
        	statement = conn.prepareStatement("SELECT idAct from instruidas WHERE ssNumber= ?");
        	statement.setString(1, ssNumber);
            result = statement.executeQuery();
            while ( result.next() ) {
            	listaActividades.add(result.getInt("idAct"));
            }
        } catch (SQLException e) {
            Log.severe("Error al preparedStatement");
            e.printStackTrace();
            return null;
        } finally {
            // Tancar el result
            if (result != null) {
                try {
                    result.close();
                }
                catch (SQLException e) {
                    Log.warning("Error tancant ResultSet");
                    e.printStackTrace();
                }
            }
            // Tancar el statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    Log.warning("Error tancant PreparedStatement");
                    e.printStackTrace();
                }
            }
            // Tancar la conexió de la DB
            try {
                conn.close();
            } catch (SQLException e) {
                Log.warning("Error tancant connexió JDBC");
                e.printStackTrace();
            }
        }
		return listaActividades;
	}

	/**
	 * Obtener un monitor registrado por el ssNumber.
	 * 
	 * @param codigo
	 *            Entero que es el ssNumber del monitor.
	 * @return Monitor buscado o null.
	 */

	public Instructor getInstructor(String codigo) {
		Connection conn = getConnection();

		Instructor monitor = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		// Petició a la DB
		try {
			statement = conn
					.prepareStatement("SELECT ssNumber, idNumber, name, lastname, email, telephone, isActive FROM Instructor WHERE ssNumber=?");
			statement.setString(1, codigo);
			result = statement.executeQuery();
			// Si el trobe cree el instructor
			if (result != null) {
				while (result.next()) {
					monitor = new Instructor();
					monitor.setSsNumber(result.getString("ssNumber"));
					monitor.setIdNumber(result.getString("idNumber"));
					monitor.setName(result.getString("name"));
					monitor.setLastName(result.getString("lastname"));
					monitor.setEmail(result.getString("email"));
					monitor.setTelephone(result.getString("telephone"));
					monitor.setActive(result.getBoolean("isActive"));
					monitor.setActivities(getActivitiesInstructor(monitor.getSsNumber()));
				}
				return monitor;
			} else
				return null;
		} catch (SQLException e) {
			Log.severe("Error al preparedStatement");
			e.printStackTrace();
			return null;
		} finally {
			// Tancar el result
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					Log.warning("Error tancant ResultSet");
					e.printStackTrace();
				}
			}
			// Tancar el statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					Log.warning("Error tancant PreparedStatement");
					e.printStackTrace();
				}
			}
			// Tancar la conexió de la DB
			try {
				conn.close();
			} catch (SQLException e) {
				Log.warning("Error tancant connexió JDBC");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Registrar un monitor en la base de datos.
	 * 
	 * @param monitor
	 *            a añadir a la base de datos.
	 */

	public void addInstructor(Instructor monitor) {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
		} catch (ClassNotFoundException e) {
			Log.severe("Driver JDBC no trobat");
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			Log.severe("Error creant connexió JDBC");
			e.printStackTrace();
			return;
		}
		PreparedStatement statement = null;
		// Sentencia SQL i petició a la DB
		try {
			statement = conn
					.prepareStatement("INSERT INTO Instructor(ssNumber,idNumber,name,lastname,email,telephone, isActive) "
							+ "values(?, ?, ?, ?, ?, ?,?)");
			statement.setString(1, monitor.getSsNumber());
			statement.setString(2, monitor.getIdNumber());
			statement.setString(3, monitor.getName());
			statement.setString(4, monitor.getLastName());
			statement.setString(5, monitor.getEmail());
			statement.setString(6, monitor.getTelephone());
			statement.setBoolean(7, monitor.isActive());
			statement.execute();
		} catch (SQLException e) {
			Log.severe("Error executant PreparedStatement");
			e.printStackTrace();
		} finally { // Tancar tots els streams i la conexió
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					Log.warning("Error tancant PreparedStatement");
					e.printStackTrace();
				}
			}
			try {
				conn.close();
			} catch (SQLException e) {
				Log.warning("Error tancant connexió JDBC");
				e.printStackTrace();
			}
		}

	}

	/**
	 * Actualizar los datos de un monitor en la base de datos.
	 * 
	 * @param instructor
	 *            Monitor a actualizar la información.
	 */
	public void updateInstructor(Instructor monitor) {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
		} catch (ClassNotFoundException e) {
			Log.severe("Driver JDBC no trobat");
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			Log.severe("Error creant connexió JDBC");
			e.printStackTrace();
			return;
		}
		PreparedStatement statement = null;
		// Sentencia SQL i petició a la DB
		try {
			statement = conn.prepareStatement("UPDATE instructor " + "SET "
					+ "idNumber = ?," + "name = ?," + "lastname = ?,"
					+ "email = ?," + "telephone = ?, " + " isActive = ? " + "WHERE ssNumber = ?");
			statement.setString(1, monitor.getIdNumber());
			statement.setString(2, monitor.getName());
			statement.setString(3, monitor.getLastName());
			statement.setString(4, monitor.getEmail());
			statement.setString(5, monitor.getTelephone());
			statement.setString(7, monitor.getSsNumber());
			statement.setBoolean(6,monitor.isActive());
			statement.execute();
		} catch (SQLException e) {
			Log.severe("Error executant PreparedStatement");
			e.printStackTrace();
		}
		// Tancar tots els streams i la conexió
		finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					Log.warning("Error tancant PreparedStatement");
					e.printStackTrace();
				}
			}
			try {
				conn.close();
			} catch (SQLException e) {
				Log.warning("Error tancant connexió JDBC");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Eliminar un monitor de la base de datos.
	 * 
	 * @param codigo
	 *            Entero, que es el código de identificación del monitor
	 */

	public void deleteInstructor(String codigo) {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
		} catch (ClassNotFoundException e) {
			Log.severe("Driver JDBC no trobat");
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			Log.severe("Error creant connexió JDBC");
			e.printStackTrace();
			return;
		}
		PreparedStatement statement = null;
		// Sentencia SQL i petició a la DB
		try {
			statement = conn.prepareStatement("DELETE FROM Instructor WHERE ssNumber=?");
			statement.setString(1, codigo);
			statement.execute();
			
		} catch (SQLException e) {
			Log.severe("Error executant PreparedStatement");
			e.printStackTrace();
		}
		// Tancar els streams i la connexió
		finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					Log.warning("Error tancant PreparedStatement");
					e.printStackTrace();
				}
			}
			try {
				conn.close();
			} catch (SQLException e) {
				Log.warning("Error tancant connexió JDBC");
				e.printStackTrace();
			}
		}
	}
	
    /**
     * Borrar todas las instancias de un instructor
     * @param ssNumber Identificador de instructor
     */
    public void deleteInstructorFromActivities(String ssNumber){
    	Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
        } catch (ClassNotFoundException e) {
            Log.severe("Driver JDBC no trobat");
            e.printStackTrace();
            return;
        } catch (SQLException e) {
            Log.severe("Error creant connexió JDBC");
            e.printStackTrace();
            return;
        }
        PreparedStatement statement = null;
        // Sentencia SQL i petició a la DB
        try {
            statement = conn.prepareStatement(
                    "DELETE FROM instruidas WHERE ssNumber = ?");
            statement.setString(1, ssNumber);
            statement.execute();
        } catch (SQLException e) {
            Log.severe("Error executant PreparedStatement");
            e.printStackTrace();
        } finally { // Tancar tots els streams i la conexió
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    Log.warning("Error tancant PreparedStatement");
                    e.printStackTrace();
                }
            }
            try {
                conn.close();
            } catch (SQLException e) {
                Log.warning("Error tancant connexió JDBC");
                e.printStackTrace();
            }
        }	
    }
	
	private Connection getConnection() {
		Connection conn = null;
		// Obtener la conexión con la DB
		try {
			conn = ConnectionManager.getConnection();
		} catch (ClassNotFoundException e) {
			Log.severe("Driver JDBC no trobat");
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			Log.severe("Error creant connexió JDBC");
			e.printStackTrace();
			return null;
		}
		return conn;
	}

}
