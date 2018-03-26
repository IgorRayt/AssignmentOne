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
@Table(name="AGEGROUP", schema="APP")
public class AgeGroup {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="AGEGROUPID")
    private int ageGroupID;
	
	@OneToMany(mappedBy="ageGroup")
    private Set<Age> age = new HashSet<Age>();
	
	@Column(name="DESCRIPTION")
    private String description;

	public int getAgeGroupID() {
		return ageGroupID;
	}

	public void setAgeGroupID(int ageGroupID) {
		this.ageGroupID = ageGroupID;
	}

	public Set<Age> getAge() {
		return age;
	}

	public void setAge(Set<Age> age) {
		this.age = age;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
