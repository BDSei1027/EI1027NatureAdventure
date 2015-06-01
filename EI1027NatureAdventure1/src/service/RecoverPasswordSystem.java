package service;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;

public class RecoverPasswordSystem {
	/*
	 * Clase para implementar la recuperacion de contraseña de un usuario
	 */
	private class Data {
		private String user;
		private long timestamp;
		private String challenge;
		
		
		public Data(String user) {
			super();
			this.user = user;
			this.timestamp = new Date().getTime();
			generateChallenge();
		}

		public long getTimestamp() {
			return timestamp;
		}

		public String getChallenge() {
			return challenge;
		}
		
		private void generateChallenge() {
			Random rand = new Random(timestamp);
			Integer auth = new Integer(user.hashCode() + rand.nextInt());
			this.challenge = encr.encryptPassword(auth.toString());
		}
	}
	
	private Map<String, Data> db;
	private final long aDay = 86400000;
	private final static PasswordEncryptor encr = new BasicPasswordEncryptor();
	
	public RecoverPasswordSystem() {
		super();
		this.db = new ConcurrentHashMap<String, RecoverPasswordSystem.Data>();
	}
	
	/**
	 * Añadir una petición de recuperación de contraseña
	 * @param user Usuario que lo ha pedido
	 * @return Devuelve el reto
	 */
	public String add(String user) {
		Data row = new Data(user);
		db.put(user, row);
		return row.getChallenge();
	}
	
	/**
	 * Comprobar el reto obtinido con el usuario para resetear la contraseña
	 * @param user Usuario que pide el reset
	 * @param auth Reto a resolver por el usuario
	 * @return true si supera la comprobacion del reto, false si no
	 */
	public boolean check(String user, String auth) {
		// Si el usuario no esta en la tabla devolver falso
		if (!db.containsKey(user)) return false;
		
		Data row = db.get(user);
		Date now = new Date();
		
		// Si la fecha del add es menor a un dia y el reto es el mismo la validacion es ok return true
		if (now.getTime() < (row.getTimestamp() + aDay)) {
			if (auth.compareTo(row.getChallenge()) == 0) {
				// Encontrado y es valido quita la entrada y devuelve true
				db.remove(user);
				return true;
			}
		} else if (now.getTime() > (row.getTimestamp() + aDay)) db.remove(user);
		// La fecha de un dia ya ha pasado y elimina la entrada
		return false;
	}
}
