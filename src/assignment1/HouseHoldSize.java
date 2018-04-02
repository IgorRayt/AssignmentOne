package assignment1;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
@Table(name="HOUSEHOLDSIZE", schema="APP")
public class HouseHoldSize {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
	
	@OneToMany(mappedBy="houseHoldSize")
    private Set<HouseHold> houseHold = new HashSet<HouseHold>();
	
	@Column(name="DESCRIPTION")
    private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<HouseHold> getHouseHold() {
		return houseHold;
	}

	public void setHouseHold(Set<HouseHold> houseHold) {
		this.houseHold = houseHold;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
