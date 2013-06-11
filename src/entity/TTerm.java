package entity;

import java.util.LinkedList;

/**
 * TTerm entity.TTerm
 * 概念的信息
 * 
 * @author MyEclipse Persistence Tools
 */
public class TTerm implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer conceptId;
	private String termLabel;
	private String upLabel;
	private String normLabel;
	private String normLabel2;
	private Integer language;
	private Integer lanPreferred;
	private Integer type;
	private String qualifier;
	private Integer sortOrder;

	// Constructors

	/** default constructor */
	public TTerm() {
	}

	/** minimal constructor */
	public TTerm(Integer id, Integer conceptId, String termLabel,
			String upLabel, Integer lanPreferred, Integer type,
			Integer sortOrder) {
		this.id = id;
		this.conceptId = conceptId;
		this.termLabel = termLabel;
		this.upLabel = upLabel;
		this.lanPreferred = lanPreferred;
		this.type = type;
		this.sortOrder = sortOrder;
	}

	/** full constructor */
	public TTerm(Integer id, Integer conceptId, String termLabel,
			String upLabel, String normLabel, String normLabel2,
			Integer language, Integer lanPreferred, Integer type,
			String qualifier, Integer sortOrder) {
		this.id = id;
		this.conceptId = conceptId;
		this.termLabel = termLabel;
		this.upLabel = upLabel;
		this.normLabel = normLabel;
		this.normLabel2 = normLabel2;
		this.language = language;
		this.lanPreferred = lanPreferred;
		this.type = type;
		this.qualifier = qualifier;
		this.sortOrder = sortOrder;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getConceptId() {
		return this.conceptId;
	}

	public void setConceptId(Integer conceptId) {
		this.conceptId = conceptId;
	}

	public String getTermLabel() {
		return this.termLabel;
	}

	public void setTermLabel(String termLabel) {
		this.termLabel = termLabel;
	}

	public String getUpLabel() {
		return this.upLabel;
	}

	public void setUpLabel(String upLabel) {
		this.upLabel = upLabel;
	}

	public String getNormLabel() {
		return this.normLabel;
	}

	public void setNormLabel(String normLabel) {
		this.normLabel = normLabel;
	}

	public String getNormLabel2() {
		return this.normLabel2;
	}

	public void setNormLabel2(String normLabel2) {
		this.normLabel2 = normLabel2;
	}

	public Integer getLanguage() {
		return this.language;
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}

	public Integer getLanPreferred() {
		return this.lanPreferred;
	}

	public void setLanPreferred(Integer lanPreferred) {
		this.lanPreferred = lanPreferred;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getQualifier() {
		return this.qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}