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
@Table(name="GEOGRAPHICAREA", schema="APP")
public class GeographicArea {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="GEOGRAPHICAREAID")
    private int geographicAreaID;
	
	@OneToMany(mappedBy="geographicArea")
    private Set<Age> age = new HashSet<Age>();
	
	@OneToMany(mappedBy="geographicArea")
    private Set<HouseHold> houseHold = new HashSet<HouseHold>();
	
	@Column(name="CODE")
    private int code;
	
	@Column(name="LEVEL")
    private int level;
	
	@Column(name="NAME")
    private String name;
	
	@Column(name="ALTERNATIVECODE")
    private int alternativeCode;

	public int getGeographicAreaID() {
		return geographicAreaID;
	}

	public void setGeographicAreaID(int geographicAreaID) {
		this.geographicAreaID = geographicAreaID;
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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAlternativeCode() {
		return alternativeCode;
	}

	public void setAlternativeCode(int alternativeCode) {
		this.alternativeCode = alternativeCode;
	}
	
	
}
