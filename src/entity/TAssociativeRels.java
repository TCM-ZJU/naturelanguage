package entity;

import java.util.Set;

/**
 * TAssociativeRels entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TAssociativeRels implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer leftConcId;
	private Integer rightConcId;
	private Integer type;
	private Integer pairId;
	private TTerm leftTerm;
	private TTerm rightTerm;
//	private Set<TTerm> leftTerms;
//	private Set<TTerm> rightTerms;
//	private Set<TCl25> types;
	
	// Constructors

	/** default constructor */
	public TAssociativeRels() {
	}

	/** minimal constructor */
	public TAssociativeRels(Integer id, Integer leftConcId, Integer rightConcId) {
		this.id = id;
		this.leftConcId = leftConcId;
		this.rightConcId = rightConcId;
	}

	/** full constructor */
	public TAssociativeRels(Integer id, Integer leftConcId,
			Integer rightConcId, Integer type, Integer pairId) {
		this.id = id;
		this.leftConcId = leftConcId;
		this.rightConcId = rightConcId;
		this.type = type;
		this.pairId = pairId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLeftConcId() {
		return this.leftConcId;
	}

	public void setLeftConcId(Integer leftConcId) {
		this.leftConcId = leftConcId;
	}

	public Integer getRightConcId() {
		return this.rightConcId;
	}

	public void setRightConcId(Integer rightConcId) {
		this.rightConcId = rightConcId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPairId() {
		return this.pairId;
	}

	public void setPairId(Integer pairId) {
		this.pairId = pairId;
	}

	public TTerm getLeftTerm() {
		return leftTerm;
	}

	public void setLeftTerm(TTerm leftTerm) {
		this.leftTerm = leftTerm;
	}

	public TTerm getRightTerm() {
		return rightTerm;
	}

	public void setRightTerm(TTerm rightTerm) {
		this.rightTerm = rightTerm;
	}

//	public Set<TTerm> getLeftTerms() {
//		return leftTerms;
//	}
//
//	public void setLeftTerms(Set<TTerm> leftTerms) {
//		this.leftTerms = leftTerms;
//	}
//
//	public Set<TTerm> getRightTerms() {
//		return rightTerms;
//	}
//
//	public void setRightTerms(Set<TTerm> rightTerms) {
//		this.rightTerms = rightTerms;
//	}

//	public Set<TCl25> getTypes() {
//		return types;
//	}
//
//	public void setTypes(Set<TCl25> types) {
//		this.types = types;
//	}

}