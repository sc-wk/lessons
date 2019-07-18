/**
 * 
 */
package com.abc.cricket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Soham.Chakravarti
 *
 */
@Entity
@Table(name="STADIUM")
@Getter @Setter @ToString
public class Stadium {

	@Id
	@SequenceGenerator(name="SEQ_STADIUM")
	@Column(name="ID")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="city")
	private String city;
	
	@Column(name="country")
	private String country;
	
}
