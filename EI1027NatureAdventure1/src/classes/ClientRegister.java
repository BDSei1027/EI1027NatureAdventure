package classes;

public class ClientRegister {
	/*
	 * Esta clase es una clase envoltura para recibir el registro
	 * de un cliente en nuestra pagina web
	 */
	
	private String id;
	private String name;
	private String lastName;
	private String password;
	private String language;
	private boolean tocs;
	
	public ClientRegister() {
		super();
	}

	public ClientRegister(ClientRegister cl) {
		super();
		this.id = cl.getId();
		this.name = cl.getName();
		this.lastName = cl.getLastName();
		this.password = cl.getPassword();
		this.language = cl.getLanguage();
		this.tocs = cl.isTocs();
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isTocs() {
		return tocs;
	}

	public void setTocs(boolean tocs) {
		this.tocs = tocs;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
}
