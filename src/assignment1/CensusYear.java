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
@Table(name="CENSUSYEAR", schema="APP")
public class CensusYear {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="CENSUSYEARID")
    private int censusYearID;
	
	@OneToMany(mappedBy="censusYear")
    private Set<Age> age = new HashSet<Age>();
	
	@OneToMany(mappedBy="censusYear")
    private Set<HouseHold> houseHold = new HashSet<HouseHold>();
	
	@Column(name="CENSUSYEAR")
    private int censusYear;

	public int getCensusYearID() {
		return censusYearID;
	}

	public void setCensusYearID(int censusYearID) {
		this.censusYearID = censusYearID;
	}

	public Set<Age> getAge() {
		return age;
	}

	public void setAge(Set<Age> age) {
		this.age = age;
	}

	public Set<HouseHold> getHouseHold() {
		return houseHold;
	}

	public void setHouseHold(Set<HouseHold> houseHold) {
		this.houseHold = houseHold;
	}

	public int getCensusYear() {
		return censusYear;
	}

	public void setCensusYear(int censusYear) {
		this.censusYear = censusYear;
	}
	
	
	
}
