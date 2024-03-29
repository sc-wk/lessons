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
@Table(name="PLAYER")
@Getter @Setter @ToString
public class Player {
	
	@Id
	@GeneratedValue(generator="SEQ_PLAYER")
	@SequenceGenerator(name="SEQ_PLAYER", initialValue=1, allocationSize=1)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="FIRST_NM")
	private String firstName;
	
	@Column(name="LAST_NM")
	private String lastName;
	
}
