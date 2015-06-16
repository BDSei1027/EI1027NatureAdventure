package classes;

import org.springframework.stereotype.Component;

@Component
public class ClientRegister {
	/*
	 * Esta clase es una clase envoltura para recibir el registro
	 * de un cliente en nuestra pagina web
	 */
	
	private String id;
	private String name;
	private String lastName;
	private String email;
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
		this.email = cl.getEmail();
		this.password = cl.getPassword();
		this.language = cl.getLanguage();
		this.tocs = cl.isTocs();
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( obj == null ) return false;
        if ( this == obj ) return true;
        if ( ! (obj instanceof ClientRegister ) ) return false;
        
        ClientRegister c = (ClientRegister) obj;
        if (!this.id.equals(c.getId())) return false;
        if (!this.name.equals(c.getName())) return false;
        if (!this.lastName.equals(c.getLastName())) return false;
        if (!this.email.equals(c.getEmail())) return false;
        if (!this.language.equals(c.getLanguage())) return false;
        if (this.tocs != c.isTocs()) return false;
        return true;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
