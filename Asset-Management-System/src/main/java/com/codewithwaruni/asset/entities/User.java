package com.codewithwaruni.asset.entities;

import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Entity
@Table(name= "users")
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int id;
	@Column(nullable =false, length=100)
	private String name;
	private String email;
	private String password;
	private String confirm_password;
    
	
}
