/**
 * 
 */
package com.abc.cricket.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.abc.cricket.ApplicationException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Soham.Chakravarti
 *
 */
@Entity
@Table(name="TEAM")
@Getter @Setter @ToString
public class Team {

	@Id
	@SequenceGenerator(name="SEQ_TEAM")
	@Column(name="ID")
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="TEAM_ID")
	private List<Player> players;
	
	
	public Player getPlayer(int playerId) throws ApplicationException {
		return getPlayers().stream()
				.filter(p->p.getId()==playerId)
				.findFirst()
				.orElseThrow(()->new ApplicationException("Player with id: "+playerId+" not found in Team: "+this));
	}
}
