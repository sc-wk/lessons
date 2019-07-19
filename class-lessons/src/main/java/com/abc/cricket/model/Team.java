/**
 * 
 */
package com.abc.cricket.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue(generator="SEQ_TEAM")
	@SequenceGenerator(name="SEQ_TEAM", initialValue=1, allocationSize=1)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	@JoinColumn(name="TEAM_ID")
	private List<Player> players;
	
	public void addPlayer(Player p) {
		//p.setTeamId(id);
		
		Optional.ofNullable(players)
			.orElseGet(()->{
				setPlayers(new ArrayList<>());
				return getPlayers();
			})
			.add(p);
	}
	
	public Player removePlayer(int playerId) {
		Optional<Player> player = findPlayer(playerId);
		
		if(player.isPresent()) {
			players.remove(player.get());
			return player.get();
		}
		
		return null;
	}
	
	public Player getPlayer(int playerId) throws ApplicationException {
		return findPlayer(playerId)
				.orElseThrow(()->new ApplicationException("Player with id: "+playerId+" not found in Team: "+this));
	}
	
	public Optional<Player> findPlayer(int playerId) {
		if(players==null)
			return Optional.empty();
		
		return getPlayers().stream()
				.filter(p->p.getId()==playerId)
				.findFirst();
	}
}
