/**
 * 
 */
package com.abc.cricket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue(generator="SEQ_STADIUM")
	@SequenceGenerator(name="SEQ_STADIUM", initialValue=1, allocationSize=1)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="city")
	private String city;
	
	@Column(name="country")
	private String country;
	
}
