package entity;

/**
 * TCl25 entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TCl25 implements java.io.Serializable {

	// Fields

	private Integer FId;
	private Integer FItemId;
	private Integer FLang;
	private Integer FSortOrder;
	private Integer FIsDefault;
	private String FCode;
	private String FDescription;
	private Integer FRelatedId;
	private String f1215;

	// Constructors

	/** default constructor */
	public TCl25() {
	}

	/** minimal constructor */
	public TCl25(Integer FId, Integer FItemId, Integer FLang,
			Integer FSortOrder, Integer FIsDefault, Integer FRelatedId) {
		this.FId = FId;
		this.FItemId = FItemId;
		this.FLang = FLang;
		this.FSortOrder = FSortOrder;
		this.FIsDefault = FIsDefault;
		this.FRelatedId = FRelatedId;
	}

	/** full constructor */
	public TCl25(Integer FId, Integer FItemId, Integer FLang,
			Integer FSortOrder, Integer FIsDefault, String FCode,
			String FDescription, Integer FRelatedId, String f1215) {
		this.FId = FId;
		this.FItemId = FItemId;
		this.FLang = FLang;
		this.FSortOrder = FSortOrder;
		this.FIsDefault = FIsDefault;
		this.FCode = FCode;
		this.FDescription = FDescription;
		this.FRelatedId = FRelatedId;
		this.f1215 = f1215;
	}

	// Property accessors

	public Integer getFId() {
		return this.FId;
	}

	public void setFId(Integer FId) {
		this.FId = FId;
	}

	public Integer getFItemId() {
		return this.FItemId;
	}

	public void setFItemId(Integer FItemId) {
		this.FItemId = FItemId;
	}

	public Integer getFLang() {
		return this.FLang;
	}

	public void setFLang(Integer FLang) {
		this.FLang = FLang;
	}

	public Integer getFSortOrder() {
		return this.FSortOrder;
	}

	public void setFSortOrder(Integer FSortOrder) {
		this.FSortOrder = FSortOrder;
	}

	public Integer getFIsDefault() {
		return this.FIsDefault;
	}

	public void setFIsDefault(Integer FIsDefault) {
		this.FIsDefault = FIsDefault;
	}

	public String getFCode() {
		return this.FCode;
	}

	public void setFCode(String FCode) {
		this.FCode = FCode;
	}

	public String getFDescription() {
		return this.FDescription;
	}

	public void setFDescription(String FDescription) {
		this.FDescription = FDescription;
	}

	public Integer getFRelatedId() {
		return this.FRelatedId;
	}

	public void setFRelatedId(Integer FRelatedId) {
		this.FRelatedId = FRelatedId;
	}

	public String getF1215() {
		return this.f1215;
	}

	public void setF1215(String f1215) {
		this.f1215 = f1215;
	}

}