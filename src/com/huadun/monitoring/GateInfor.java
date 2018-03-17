package com.huadun.monitoring;

public class GateInfor {
	
	private String id;
	
	private String apiName;//****/
	
	private String terminalNo;
	
	private String lineNo;
	
	private String stationNo;
	
	private String entryTime;
	
	private String frontPhoto;
	
	private String selfPhoto;
	
	private String checkInfo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getFrontPhoto() {
		return frontPhoto;
	}

	public void setFrontPhoto(String frontPhoto) {
		this.frontPhoto = frontPhoto;
	}

	public String getSelfPhoto() {
		return selfPhoto;
	}

	public void setSelfPhoto(String selfPhoto) {
		this.selfPhoto = selfPhoto;
	}

	public String getCheckInfo() {
		return checkInfo;
	}

	public void setCheckInfo(String checkInfo) {
		this.checkInfo = checkInfo;
	}
	
	@Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder("apiName="+apiName+"&terminalNo="+terminalNo+"&lineNo="+lineNo+"&stationNo="+stationNo
        		+"&entryTime="+entryTime+"&frontPhoto="+frontPhoto+"&selfPhoto="+selfPhoto+"&checkInfo="+checkInfo);
        return stringBuilder.toString();
    }

}
