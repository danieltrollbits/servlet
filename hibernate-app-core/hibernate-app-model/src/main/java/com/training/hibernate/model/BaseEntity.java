package com.training.hibernate.model;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;

	public int getId(){
		return this.id;
	}

	public void setId(int id){
		this.id = id;
	}
} 