package ar.edu.iua.iw3.Model;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "directions")
public class Direction implements Serializable{

	private static final long serialVersionUID = -2096655693932225923L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_direction;

	@Column(length = 100,nullable = false)
	private String number;

	@Column(length = 100,nullable = false)
	private String neighborhood;
	
	@Column(length = 100,nullable = false)
	private Integer userId;
	
	@Column(length = 100,nullable = false)
	private String street;

	//SETTERS AND GETTERS	
	public long getId_direction() {
		return id_direction;
	}

	public void setId_direction(long id_direction) {
		this.id_direction = id_direction;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
}