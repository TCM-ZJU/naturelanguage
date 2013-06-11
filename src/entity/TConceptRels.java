package entity;

import java.util.Set;

/**
 * TConceptRels entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TConceptRels implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer childId;
	private Integer parentId;
	private Integer sortOrder;
	private Integer type;
	private Integer preferred;
	private Set<TTerm> childTerms;
	private Set<TTerm> parentTerms;
	
	// Constructors

	/** default constructor */
	public TConceptRels() {
	}

	/** full constructor */
	public TConceptRels(Integer id, Integer childId, Integer parentId,
			Integer sortOrder, Integer type, Integer preferred) {
		this.id = id;
		this.childId = childId;
		this.parentId = parentId;
		this.sortOrder = sortOrder;
		this.type = type;
		this.preferred = preferred;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChildId() {
		return this.childId;
	}

	public void setChildId(Integer childId) {
		this.childId = childId;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPreferred() {
		return this.preferred;
	}

	public void setPreferred(Integer preferred) {
		this.preferred = preferred;
	}

	public Set<TTerm> getChildTerms() {
		return childTerms;
	}

	public void setChildTerms(Set<TTerm> childTerms) {
		this.childTerms = childTerms;
	}

	public Set<TTerm> getParentTerms() {
		return parentTerms;
	}

	public void setParentTerms(Set<TTerm> parentTerms) {
		this.parentTerms = parentTerms;
	}

	
}