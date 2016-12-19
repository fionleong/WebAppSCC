package edu.sjsu.db;

public class CaregiverSchedule {
	private int scheduleId;
	private int userId;
	private boolean availableMo;
	private boolean availableTu;
	private boolean availableWe;
	private boolean availableTh;
	private boolean availableFr;
	private boolean availableSa;
	private boolean availableSu;
	
	public CaregiverSchedule(int scheduleId, int userId, boolean availableMo, boolean availableTu,
			boolean availableWe, boolean availableTh, boolean availableFr, boolean availableSa,
			boolean availableSu) {
		this.scheduleId = scheduleId;
		this.userId = userId;
		this.availableMo = availableMo;
		this.availableTu = availableTu;
		this.availableWe = availableWe;
		this.availableTh = availableTh;
		this.availableFr = availableFr;
		this.availableSa = availableSa;
		this.availableSu = availableSu;
	}
	
	public String toJson() {
		String json = "{\"scheduleId\":\"" + scheduleId + "\",\"userId\":\"" + userId +
				"\",\"availableMo\":\"" + String.valueOf(availableMo) +
				"\",\"availableTu\":\"" + String.valueOf(availableTu) + "\",\"availableWe\":\"" +
				String.valueOf(availableWe) + "\",\"availableTh\":\"" + String.valueOf(availableTh) +
				"\",\"availableFr\":\"" + String.valueOf(availableFr) + "\",\"availableSa\":\"" +
				String.valueOf(availableSa) + "\",\"availableSu\":\"" + String.valueOf(availableSu) +
				"\"}";
		return json;
	}
	
	public int getScheduleId() {
		return scheduleId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public boolean isAvailableMo() {
		return availableMo;
	}
	
	public boolean isAvailableTu() {
		return availableTu;
	}
	
	public boolean isAvailableWe() {
		return availableWe;
	}
	
	public boolean isAvailableTh() {
		return availableTh;
	}
	
	public boolean isAvailableFr() {
		return availableFr;
	}
	
	public boolean isAvailableSa() {
		return availableSa;
	}
	
	public boolean isAvailableSu() {
		return availableSu;
	}
}
