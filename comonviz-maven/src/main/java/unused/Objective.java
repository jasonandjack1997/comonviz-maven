package unused;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
//public class ProcessObjective extends DataModel{
public class Objective{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@ManyToOne
	//Town town;
	Activity activity;

//	public Town getTown() {
//		return town;
//	}
//
//	public void setTown(Town town) {
//		this.town = town;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity processActivity) {
		this.activity = processActivity;
	}



}
