package com.springboard.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name= "user")
@EqualsAndHashCode(of="uid")
public class User {
	@Id
	private String uid;
	private String email;
	private String password;
	private String user_name;
}
