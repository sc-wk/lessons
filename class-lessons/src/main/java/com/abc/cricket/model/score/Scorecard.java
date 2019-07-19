/**
 * 
 */
package com.abc.cricket.model.score;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Soham Chakravarti
 *
 */
@Entity
@Table(name="SCORECARD")
@Getter @Setter @ToString
public class Scorecard {
	
	@Id
	@GeneratedValue(generator="SEQ_SCORECARD")
	@SequenceGenerator(name="SEQ_SCORECARD", initialValue=1, allocationSize=1)
	@Column(name="ID")
	private Integer id;

	@Column(name="MATCH_ID")
	private Integer matchId;
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn
	private Innings firstInnings;
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn
	private Innings secondInnings;
	
	@Column(name="LAST_UPDATED")
	@Version
	@LastModifiedDate
	private Date lastUpdated;
}
