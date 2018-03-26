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



@Entity
@Table(name="AGE", schema="APP")
public class Age {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="AGEID")
    private int ageID;
	
	@Column(name="COMBINED")
    private String combined;

    @Column(name="MALE")
    private String male;

    @Column(name="FEMALE")
    private int female;
    
    
    @ManyToOne
	@JoinColumn(name="AGEGROUP", nullable=false)
    private AgeGroup ageGroup;
    
    @ManyToOne
	@JoinColumn(name="CENSUSYEAR", nullable=false)
    private CensusYear censusYear;
    
    @ManyToOne
	@JoinColumn(name="GEOGRAPHICAREA", nullable=false)
    private GeographicArea geographicArea;

	public int getAgeID() {
		return ageID;
	}

	public void setAgeID(int ageID) {
		this.ageID = ageID;
	}

	public String getCombined() {
		return combined;
	}

	public void setCombined(String combined) {
		this.combined = combined;
	}

	public String getMale() {
		return male;
	}

	public void setMale(String male) {
		this.male = male;
	}

	public int getFemale() {
		return female;
	}

	public void setFemale(int female) {
		this.female = female;
	}

	public AgeGroup getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(AgeGroup ageGroup) {
		this.ageGroup = ageGroup;
	}

	public CensusYear getCensusYear() {
		return censusYear;
	}

	public void setCensusYear(CensusYear censusYear) {
		this.censusYear = censusYear;
	}

	public GeographicArea getGeographicArea() {
		return geographicArea;
	}

	public void setGeographicArea(GeographicArea geographicArea) {
		this.geographicArea = geographicArea;
	}

	
    
    
    
    
	
}
