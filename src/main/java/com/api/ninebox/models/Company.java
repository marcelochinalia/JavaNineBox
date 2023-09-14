package com.api.ninebox.models;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="Company")
public class Company implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Integer id;

	@Column(nullable = false, length = 70)
	private String name;

	@Column(nullable = false)
	private Boolean activated;

	public Company(){

	}

	public Company(Integer id){
		this.id = id;
		this.activated = true;
	}

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) { this.id = id; }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActivated() {
		return activated;
	}
	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	@Override
	public String toString() {
		return "Company{" +
				"id=" + id +
				", name='" + name + '\'' +
				", activated=" + activated +
				'}';
	}
}
