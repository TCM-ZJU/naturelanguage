package entity;

/**
 * TUr1023 entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TUr1023 implements java.io.Serializable {

	// Fields

	private Integer FId;
	private Integer FMasterId;
	private Integer FLang;
	private Integer FSortOrder;
	private Integer f1021;
	private String f1022;
	private String f1023;
	private String f1024;

	// Constructors

	/** default constructor */
	public TUr1023() {
	}

	/** full constructor */
	public TUr1023(Integer FMasterId, Integer FLang, Integer FSortOrder,
			Integer f1021, String f1022, String f1023, String f1024) {
		this.FMasterId = FMasterId;
		this.FLang = FLang;
		this.FSortOrder = FSortOrder;
		this.f1021 = f1021;
		this.f1022 = f1022;
		this.f1023 = f1023;
		this.f1024 = f1024;
	}

	// Property accessors

	public Integer getFId() {
		return this.FId;
	}

	public void setFId(Integer FId) {
		this.FId = FId;
	}

	public Integer getFMasterId() {
		return this.FMasterId;
	}

	public void setFMasterId(Integer FMasterId) {
		this.FMasterId = FMasterId;
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

	public Integer getF1021() {
		return this.f1021;
	}

	public void setF1021(Integer f1021) {
		this.f1021 = f1021;
	}

	public String getF1022() {
		return this.f1022;
	}

	public void setF1022(String f1022) {
		this.f1022 = f1022;
	}

	public String getF1023() {
		return this.f1023;
	}

	public void setF1023(String f1023) {
		this.f1023 = f1023;
	}

	public String getF1024() {
		return this.f1024;
	}

	public void setF1024(String f1024) {
		this.f1024 = f1024;
	}

}