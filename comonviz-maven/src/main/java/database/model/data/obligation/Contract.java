package database.model.data.obligation;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecordSet;
import database.model.data.relatedEntity.Client;
import database.model.data.relatedEntity.Contractor;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Contract extends MandatoryObligation	{

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Contractor> contractors =  new BasicRecordSet<Contractor>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Client> clients =  new BasicRecordSet<Client>();
	
	private Date signingDate;
	
	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	public Contract(String name){
		super(name);
	}

	public Set<Contractor> getContractors() {
		return contractors;
	}

	public void setContractors(Set<Contractor> contractors) {
		this.contractors = contractors;
	}

}
