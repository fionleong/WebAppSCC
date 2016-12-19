package edu.sjsu.db;

public class CaregiverSkills {
	private int skillsId;
	private int userId;
	private String resume;
	private boolean parkinsons;
	private boolean alzheimers;
	private boolean stroke;
	private boolean cancer;
	private boolean hospitalSitter;
	
	public CaregiverSkills(int skillsId, int userId, String resume, boolean parkinsons, 
			boolean alzheimers, boolean stroke, boolean cancer, boolean hospitalSitter) {
		this.skillsId = skillsId;
		this.userId = userId;
		this.resume = resume;
		this.parkinsons = parkinsons;
		this.alzheimers = alzheimers;
		this.stroke = stroke;
		this.cancer = cancer;
		this.hospitalSitter = hospitalSitter;
	}
	
	public String toJson() {
		String json = "{\"skillsId\":\"" + skillsId + "\",\"userId\":\"" + userId +
				"\",\"resume\":\"" + resume + "\",\"parkinsons\":\"" + 
				String.valueOf(parkinsons) + "\",\"alzheimers\":\"" + String.valueOf(alzheimers) + 
				"\",\"stroke\":\"" + String.valueOf(stroke) + "\",\"cancer\":\"" + 
				String.valueOf(cancer) + "\",\"hospitalSitter\":\"" + String.valueOf(hospitalSitter)
				+ "\"}";
		return json;
	}
	
	public int getSkillsId() {
		return skillsId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public String getResume() {
		return resume;
	}
	
	public boolean skillParkinsons() {
		return parkinsons;
	}
	
	public boolean skillAlzheimers() {
		return alzheimers;
	}
	
	public boolean skillStroke() {
		return stroke;
	}
	
	public boolean skillCancer() {
		return cancer;
	}
	
	public boolean skillHospitalSitter() {
		return hospitalSitter;
	}
}
