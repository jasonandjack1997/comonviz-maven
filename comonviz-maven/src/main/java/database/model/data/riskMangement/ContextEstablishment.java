package database.model.data.riskMangement;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecordSet;
import database.model.data.relatedEntity.ExternalParameter;
import database.model.data.relatedEntity.InternalParameter;
import database.model.data.relatedEntity.RiskCriterion;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ContextEstablishment extends RiskManagementProcess {
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<ExternalParameter> externalParameters = new BasicRecordSet<ExternalParameter>();
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<InternalParameter> internalParameters = new BasicRecordSet<InternalParameter>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<RiskCriterion> riskCriteria = new BasicRecordSet<RiskCriterion>();
	
	@Enumerated(EnumType.ORDINAL)
	private Applicability applicability;
	
	private enum Applicability{
		Applicable,
		NOT_Applicable
	}

	public ContextEstablishment(String name) {
		super(name);
	}

}
