package com.cpm.delegates;

public class CoverageBean
{
protected int MID;
	


	public int getMID() {
	return MID;
}

public void setMID(int mID) {
	MID = mID;
}

	protected String storeId;
	
	protected String userId;
	
	protected String inTime;
	
	protected String outTime;
	
	protected String visitDate;
	
	protected String version;
	
	protected String latitude;
	
	protected String longitude;
	
	protected String reasonid="";
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	protected String reason="";
	
	protected String status="";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getReasonid() {
		return reasonid;
	}

	public void setReasonid(String reasonid) {
		this.reasonid = reasonid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
