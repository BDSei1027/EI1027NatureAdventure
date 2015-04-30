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

import classes.Activity;
import exceptions.InvalidGroupSizeException;

/**
 * Dao para gestionar las actividades con la base de datos
 */
public class DaoActivity {
    private final static Logger Log = Logger.getLogger(DaoActivity.class.getName());

    public DaoActivity() {
        super();
    }

    /**
     * Obtener todas las actividades registradas en la base de datos.
     * @return Map con las actividades.
     */
    public Map<Integer,Activity> getActivities() {
        Connection conn = getConnection();
        Map<Integer,Activity> activities = new HashMap<Integer,Activity>();
        PreparedStatement statement = null;
        ResultSet result = null;
        // Petició a la DB
        try {
            statement = conn.prepareStatement("SELECT idact, name, leveldif, schedule, price, place, mingroup, maxgroup, isactive FROM activity");
            result = statement.executeQuery();
            while ( result.next() ) {
                Activity actividad = new Activity();
                actividad.setIdAct( result.getInt("idact") );
                actividad.setName( result.getString("name") );
                actividad.setLevel( result.getInt("leveldif") );
                actividad.setSchedule( result.getString("schedule") );
                actividad.setPrice( result.getFloat("price") );
                actividad.setPlace( result.getString("place") );
                actividad.setMinimumGroup( result.getInt("mingroup") );
                actividad.setMaximumGroup( result.getInt("maxgroup") );
                actividad.setIsActive( result.getBoolean("isactive"));
                actividad.setInstructors(getInstructorActividad(actividad.getIdAct()));
                activities.put(actividad.getIdAct(), actividad);
                
            }            
        } catch (SQLException e) {
            Log.severe("Error al preparedStatement");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
        	e.printStackTrace();
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
        return activities;
    }

    /**
     * Obtener los instructores que puede impartir esa actividad
     * @param idAct Código de la actividad
     * @return Lista con los identificadores de los instructores
     */
    private List<String> getInstructorActividad(int idAct) {
    	Connection conn = getConnection();
        List<String> listaInstructores=new LinkedList<String>();
        PreparedStatement statement = null;
        ResultSet result = null;
        // Petició a la DB
        try {
        	statement = conn.prepareStatement("SELECT ssNumber from instruidas WHERE idAct=?");
        	statement.setInt(1, idAct);
            result = statement.executeQuery();
            while ( result.next() ) {
            	listaInstructores.add(result.getString("ssNumber"));
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
		return listaInstructores;
	}

	/**
     * Obtener una actividad registrada por el identificador de la actividad.
     * @param codigo Entero que es el identificador de la actividad.
     * @return Actividad buscada o null.
     */
    public Activity getActivity(int codigo){
        Connection conn = getConnection();
        Activity actividad = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        // Petició a la DB
        try {
            statement = conn.prepareStatement("SELECT idact, name, leveldif, schedule, price, place, mingroup, maxgroup, isactive FROM activity WHERE idact=?");
            statement.setInt(1, codigo);
            result = statement.executeQuery();
            // Si el trobe cree la activitat
            if ( result != null) {
                while (result.next()) {
                    actividad = new Activity();
                    actividad.setIdAct(result.getInt("idact"));
                    actividad.setName(result.getString("name"));
                    actividad.setLevel(result.getInt("leveldif"));
                    actividad.setSchedule(result.getString("schedule"));
                    actividad.setPrice(result.getFloat("price"));
                    actividad.setPlace(result.getString("place"));
                    actividad.setMinimumGroup(result.getInt("mingroup"));
                    actividad.setMaximumGroup(result.getInt("maxgroup"));
                    actividad.setIsActive(result.getBoolean("isactive"));
                    actividad.setInstructors(getInstructorActividad(actividad.getIdAct()));
                }
                return actividad;
            }
            else return null;
        } catch (Exception e) {
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
    }

    /**
     * Registrar una actividad en la base de datos.
     * @param actividad Actividad a añadir a la base de datos.
     */
    public void addActivity(Activity actividad) {
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
                    "INSERT INTO activity(idact, name, leveldif, schedule, price, place, mingroup, maxgroup, isactive) " +
                            "values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, actividad.getIdAct());
            statement.setString(2, actividad.getName());
            statement.setInt(3, actividad.getLevel());
            statement.setString(4, actividad.getSchedule());
            statement.setDouble(5, actividad.getPrice());
            statement.setString(6, actividad.getPlace());
            statement.setInt(7, actividad.getMinimumGroup());
            statement.setInt(8, actividad.getMaximumGroup());
            statement.setBoolean(9, actividad.isActive());
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
     * Actualizar los datos de una actividad en la base de datos.
     * @param actividad Actividad a actualizar la información.
     */
    public void updateActivity(Activity actividad) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
        }
        catch (ClassNotFoundException e) {
            Log.severe("Driver JDBC no trobat");
            e.printStackTrace();
            return;
        }
        catch (SQLException e) {
            Log.severe("Error creant connexió JDBC");
            e.printStackTrace();
            return;
        }
        PreparedStatement statement = null;
        // Sentencia SQL i petició a la DB
        try {
            statement = conn.prepareStatement(
                    "UPDATE activity " +
                            "SET " +
                            "name = ?," +
                            "leveldif = ?," +
                            "schedule = ?," +
                            "price = ?," +
                            "place = ?," +
                            "mingroup = ?," +
                            "maxgroup = ?, " +
                            "isactive = ? " +
                            "WHERE idact = ?");
            statement.setString(1, actividad.getName());
            statement.setInt(2, actividad.getLevel());
            statement.setString(3, actividad.getSchedule());
            statement.setDouble(4, actividad.getPrice());
            statement.setString(5, actividad.getPlace());
            statement.setInt(6, actividad.getMinimumGroup());
            statement.setInt(7, actividad.getMaximumGroup());
            statement.setBoolean(8, actividad.isActive());
            statement.setInt(9, actividad.getIdAct());
            statement.execute();
        }
        catch (SQLException e) {
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
     * Eliminar una actividad de la base de datos.
     * @param codigo Entero, que es el código de identifiparameterIndexcación de la actividad
     */
    public void deleteActivity(int codigo) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
        }
        catch (ClassNotFoundException e) {
            Log.severe("Driver JDBC no trobat");
            e.printStackTrace();
            return;
        }
        catch (SQLException e) {
            Log.severe("Error creant connexió JDBC");
            e.printStackTrace();
            return;
        }
        PreparedStatement statement = null;
        // Sentencia SQL i petició a la DB
        try {
            statement = conn.prepareStatement("DELETE FROM activity WHERE idact = ?");
            statement.setInt(1, codigo);
            statement.execute();
        }
        catch (SQLException e) {
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
     * Asignar un monitor a una actividad de la base de datos.
     * @param ssNumber idAct, ssNumber es el codigo del monitor y idAct el de la actividad.
     */
    public void assignInstructorToActivity(String ssNumber, int idAct){
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
                    "INSERT INTO instruidas(ssNumber,idact) " +
                            "values(?, ?)");
            statement.setString(1, ssNumber);
            statement.setInt(2, idAct);
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
     * Eliminar a un monitor de una actividad que puede instruir.
     * @param ssNumber Identificador del instructor.
     * @param idAct Identificador de la actividad
     */
    public void deleteInstructorFromActivity(String ssNumber, int idAct) {
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
                    "DELETE FROM instruidas WHERE ssNumber = ? AND idAct = ?");
            statement.setString(1, ssNumber);
            statement.setInt(2, idAct);
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
     * Borrar todos los instructores asignados a una actividad
     * @param idAct Código de la actividad
     */
    public void deleteInstructorsFromActivity(int idAct){
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
                    "DELETE FROM instruidas WHERE idAct = ?");
            statement.setInt(1, idAct);
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
        }
        catch (ClassNotFoundException e) {
            Log.severe("Driver JDBC no trobat");
            e.printStackTrace();
            return null;
        }
        catch (SQLException e) {
            Log.severe("Error creant connexió JDBC");
            e.printStackTrace();
            return null;
        }
		return conn;
	}
    
}
