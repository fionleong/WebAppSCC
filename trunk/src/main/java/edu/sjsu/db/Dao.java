//package edu.sjsu.db;
//
//import java.security.Key;
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;
//
//public class Dao {
//	// Database connection credentials
//	private static final String DRIVER_STR = "com.mysql.jdbc.Driver";
//	private static final String URL_STR    = "jdbc:mysql://localhost:3307/cs160_project";
//	private static final String DB_ID      = "root";
//	private static final String DB_PWD     = "fion1994";
//	
//	// Database tables
//	private static final String TABLE_USERS               = "users";
//	private static final String TABLE_CAREGIVER_SCHEDULES = "caregiver_schedules";
//	private static final String TABLE_CAREGIVER_SKILLS    = "caregiver_skills";
//	
//	// TABLE_USERS columns
//	private static final String KEY_USERS_USER_ID            = "user_id";
//	private static final String KEY_USERS_EMAIL              = "email";
//	private static final String KEY_USERS_PASSWORD           = "password";
//	private static final String KEY_USERS_FIRST_NAME         = "firstName";
//	private static final String KEY_USERS_LAST_NAME          = "lastName";
//	private static final String KEY_USERS_DOB                = "dob";
//	private static final String KEY_USERS_GENDER             = "gender";
//	private static final String KEY_USERS_ADDRESS            = "address";
//	private static final String KEY_USERS_CITY               = "city";
//	private static final String KEY_USERS_STATE              = "state";
//	private static final String KEY_USERS_ZIPCODE            = "zipcode";
//	private static final String KEY_USERS_ACCOUNT_TYPE       = "account_type";
//	private static final String KEY_USERS_VERIFIED_EMAIL     = "verified_email";
//	private static final String KEY_USERS_VERIFIED_CAREGIVER = "verified_caregiver";
//	
//	// TABLE_CAREGIVER_SCHEDULES columns
//	private static final String KEY_CAREGIVER_SCHEDULES_SCHEDULE_ID  = "schedule_id";
//	private static final String KEY_CAREGIVER_SCHEDULES_USER_ID      = "user_id";
//	private static final String KEY_CAREGIVER_SCHEDULES_AVAILABLE_MO = "available_monday";
//	private static final String KEY_CAREGIVER_SCHEDULES_AVAILABLE_TU = "available_tuesday";
//	private static final String KEY_CAREGIVER_SCHEDULES_AVAILABLE_WE = "available_wednesday";
//	private static final String KEY_CAREGIVER_SCHEDULES_AVAILABLE_TH = "available_thursday";
//	private static final String KEY_CAREGIVER_SCHEDULES_AVAILABLE_FR = "available_friday";
//	private static final String KEY_CAREGIVER_SCHEDULES_AVAILABLE_SA = "available_saturday";
//	private static final String KEY_CAREGIVER_SCHEDULES_AVAILABLE_SU = "available_sunday";
//	
//	// TABLE_CAREGIVER_SKILLS columns
//	private static final String KEY_CAREGIVER_SKILLS_SKILLS_ID       = "skills_id";
//	private static final String KEY_CAREGIVER_SKILLS_USER_ID         = "user_id";
//	private static final String KEY_CAREGIVER_SKILLS_RESUME          = "resume";
//	private static final String KEY_CAREGIVER_SKILLS_PARKINSONS      = "parkinsons";
//	private static final String KEY_CAREGIVER_SKILLS_ALZHEIMERS      = "alzheimers";
//	private static final String KEY_CAREGIVER_SKILLS_STROKE          = "stroke";
//	private static final String KEY_CAREGIVER_SKILLS_CANCER          = "cancer";
//	private static final String KEY_CAREGIVER_SKILLS_HOSPITAL_SITTER = "hospital_sitter";
//	
//	// Misc
//	private static final String KEY = "SjsuFall2016CS160Optiplex";
//	
//	public static Connection getConnection() {
//		try {
//			Class.forName(DRIVER_STR);
//			return DriverManager.getConnection(URL_STR, DB_ID, DB_PWD);
//		} catch (SQLException | ClassNotFoundException e) {
//			System.err.println("The connection failed because " + e.getMessage());
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
////	private static String encrypt(String pwd) {
////		try {
////			Key aesKey = new SecretKeySpec(KEY.getBytes(), "AES");
////			Cipher cipher = Cipher.getInstance("AES");
////			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
////			byte[] encryptedPwd = cipher.doFinal(pwd.getBytes());
////			return new String(encryptedPwd);
////		} catch (Exception e) {
////			e.printStackTrace();
////			return null;
////		}
////	}
//	
//	public static User getUser(String email, String pwd) throws SQLException {
//		// initialize connection
//		Connection conn = getConnection();
//		
//		// validate connection
//		if (conn == null) {
//			System.err.println("Connection Failed");
//			return null;
//		}
//		
//		// get encrypted password
//		String encryptedPwd = pwd;//encrypt(pwd);
//		
//		// form query
//		String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_USERS_EMAIL +
//				" = '" + email + "' AND " + KEY_USERS_PASSWORD + " = '" + encryptedPwd + "';"; // error might happen
//		PreparedStatement pstmt = conn.prepareStatement(query);
//		
//		// gather results
//		ResultSet rs = pstmt.executeQuery(); //error might happen
//		if (rs == null) {
//			System.err.println("Could not find user with specified email and password.");
//			return null;
//		} else {
//			rs.next();
//			int userId = rs.getInt(1);
//			String firstName = rs.getString(4);
//			String lastName = rs.getString(5);
//			Date dob = rs.getDate(6);
//			String gender = "Female";
//			if (rs.getBoolean(7)) {
//				gender = "Male";
//			}
//			String address = rs.getString(8);
//			String city = rs.getString(9);
//			String state = rs.getString(10);
//			int zipCode = rs.getInt(11);
//			boolean caregiver = rs.getBoolean(12);
//			boolean verifiedEmail = rs.getBoolean(13);
//			boolean verifiedCaregiver = rs.getBoolean(14);
//			
//			User user = new User(userId, email, pwd, firstName, lastName, dob, gender, address,
//					city, state, zipCode, caregiver, verifiedEmail, verifiedCaregiver); //error might happen
//			return user;
//		}
//	}
//	
////	public static void addUser(User user) throws SQLException {
////		// initialize connection
////		Connection conn = getConnection();
////				
////		// validate connection
////		if (conn == null) {
////			System.err.println("Connection Failed");
////		}
////		
////		// form query
////		String query = "INSERT INTO " + TABLE_USERS + " (" + KEY_USERS_EMAIL + ", " + 
////				KEY_USERS_PASSWORD + ", " + KEY_USERS_FIRST_NAME + ", " + KEY_USERS_LAST_NAME +
////				", " + KEY_USERS_DOB + ", " + KEY_USERS_GENDER + ", " + KEY_USERS_ADDRESS + ", " +
////				KEY_USERS_CITY + ", " + KEY_USERS_STATE + ", " + KEY_USERS_ZIPCODE + ", " +
////				KEY_USERS_ACCOUNT_TYPE + "," + KEY_USERS_VERIFIED_EMAIL + ", " +
////				KEY_USERS_VERIFIED_CAREGIVER + ") VALUES ('" + user.getEmail() + "', '" +
////				user.getPassword() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '" +
////				user.getDob() + "', '" + user.getGender() + "', '" + user.getAddress() + "', '" +
////				user.getCity() + "', '" + user.getState() + "', '" + user.getZipcode() + "', '" +
////				user.getAccountType() + "', '" + user.verifiedEmail() + "', '" +
////				user.verifiedCaregiver() + "');";
////		PreparedStatement pstmt = conn.prepareStatement(query);
////		
////		// execute
////		pstmt.execute();
////		
////		// close connection
////		conn.close();
////	}
//    
//    	public static void addUser(String email, String password, String firstName, String lastName, String dob, String gender, String address, String city, String state, String zipCode, String accType) throws SQLException {
//		// initialize connection
//		Connection conn = getConnection();
//				
//		// validate connection
//		if (conn == null) {
//			System.err.println("Connection Failed");
//		}
//		
//		try {
//			// form query
//			String query = "INSERT INTO " + TABLE_USERS + " (" + KEY_USERS_EMAIL + ", " + 
//					KEY_USERS_PASSWORD + ", " + KEY_USERS_FIRST_NAME + ", " + KEY_USERS_LAST_NAME +
//					", " + KEY_USERS_DOB + ", " + KEY_USERS_GENDER + ", " + KEY_USERS_ADDRESS + ", " +
//					KEY_USERS_CITY + ", " + KEY_USERS_STATE + ", " + KEY_USERS_ZIPCODE + ", " +
//					KEY_USERS_ACCOUNT_TYPE + ") VALUES ('" + email + "', '" +
//					password + "', '" + firstName + "', '" + lastName + "', '" +
//					dob + "', '" + gender + "', '" + address + "', '" +
//					city + "', '" + state + "', '" + zipCode + "', '" +
//					accType + "');";
//			PreparedStatement pstmt = conn.prepareStatement(query);
//			
//			// execute
//			pstmt.execute();
//			
//			// close connection
//			conn.close();
//			System.out.println("User inserted");
//		}
//		catch (SQLException e) {
//			System.out.println("UNABLE TO INSERT USER");
//			e.printStackTrace();	
//		}	
//	}
//	
//	public static void updateUser(User user, int userId) throws SQLException {
//		// initialize connection
//		Connection conn = getConnection();
//						
//		// validate connection
//		if (conn == null) {
//			System.err.println("Connection Failed");
//		}
//		
//		// form query
//		String query = "UPDATE " + TABLE_USERS + " SET " + KEY_USERS_EMAIL + " = '" + user.getEmail()
//			+ "', " + KEY_USERS_PASSWORD + " = '" + user.getPassword() + "', " +
//			KEY_USERS_FIRST_NAME + " = '" + user.getFirstName() + "', " +
//			KEY_USERS_LAST_NAME + " = '" + user.getLastName() + "', " + KEY_USERS_DOB + " = '" +
//			user.getDob() + "', " + KEY_USERS_GENDER + " = '" + user.getGender() + "', " +
//			KEY_USERS_ADDRESS + " = '" + user.getAddress() + "', " + KEY_USERS_CITY + " = '" +
//			user.getCity() + "', " + KEY_USERS_STATE + " = '" + user.getState() + "', " +
//			KEY_USERS_ZIPCODE + " = '" + user.getZipcode() + "', " + KEY_USERS_ACCOUNT_TYPE +
//			" = '" + user.getAccountType() + "', " + KEY_USERS_VERIFIED_EMAIL + " = '" +
//			user.verifiedEmail() + "', " + KEY_USERS_VERIFIED_CAREGIVER + " = '" +
//			user.verifiedCaregiver() + "' WHERE " + KEY_USERS_USER_ID + " = '" + userId + "';";
//		
//		// execute
//		PreparedStatement pstmt = conn.prepareStatement(query);
//		pstmt.executeUpdate();
//		
//		// close connection
//		conn.close();
//	}
//	
//	public static CaregiverSchedule getCaregiverSchedule(int userId) throws SQLException {
//		// initialize connection
//		Connection conn = getConnection();
//		
//		// validate connection
//		if (conn == null) {
//			System.err.println("Connection Failed");
//			return null;
//		}
//		
//		// form query
//		String query = "SELECT * FROM " + TABLE_CAREGIVER_SCHEDULES + " WHERE " +
//				KEY_CAREGIVER_SCHEDULES_USER_ID + " = '" + userId + "';";
//		PreparedStatement pstmt = conn.prepareStatement(query);
//		
//		// gather results
//		ResultSet rs = pstmt.executeQuery(query);
//		if (rs == null) {
//			System.err.println("Could not find schedule for specified user.");
//			return null;
//		} else {
//			rs.next();
//			int scheduleId = rs.getInt(1);
//			boolean availableMo = rs.getBoolean(3);
//			boolean availableTu = rs.getBoolean(4);
//			boolean availableWe = rs.getBoolean(5);
//			boolean availableTh = rs.getBoolean(6);
//			boolean availableFr = rs.getBoolean(7);
//			boolean availableSa = rs.getBoolean(8);
//			boolean availableSu = rs.getBoolean(9);
//			
//			CaregiverSchedule schedule = new CaregiverSchedule(scheduleId, userId, availableMo, 
//					availableTu, availableWe, availableTh, availableFr, availableSa, availableSu);
//			return schedule;
//		}
//	}
//	
//	public static void addCaregiverSchedule(CaregiverSchedule schedule) throws SQLException {
//		// initialize connection
//		Connection conn = getConnection();
//				
//		// validate connection
//		if (conn == null) {
//			System.err.println("Connection Failed");
//		}
//		
//		// form query
//		String query = "INSERT INTO " + TABLE_CAREGIVER_SCHEDULES + " (" +
//				KEY_CAREGIVER_SCHEDULES_USER_ID + ", " 
//				+ KEY_CAREGIVER_SCHEDULES_AVAILABLE_MO + ", " + KEY_CAREGIVER_SCHEDULES_AVAILABLE_TU
//				+ ", " + KEY_CAREGIVER_SCHEDULES_AVAILABLE_WE + ", " +
//				KEY_CAREGIVER_SCHEDULES_AVAILABLE_TH + ", " + KEY_CAREGIVER_SCHEDULES_AVAILABLE_FR
//				+ ", " + KEY_CAREGIVER_SCHEDULES_AVAILABLE_SA + ", " +
//				KEY_CAREGIVER_SCHEDULES_AVAILABLE_SU + ") VALUES ('" + schedule.getUserId() + "', '" +
//				schedule.isAvailableMo() + "', '" + schedule.isAvailableTu() + "', '" +
//				schedule.isAvailableWe() + "', '" + schedule.isAvailableTh() + "', '" +
//				schedule.isAvailableFr() + "', '" + schedule.isAvailableSa() + "', '" +
//				schedule.isAvailableSu() + "');";
//		PreparedStatement pstmt = conn.prepareStatement(query);
//		
//		// execute
//		pstmt.execute();
//		
//		// close connection
//		conn.close();
//	}
//	
//	public static void updateCaregiverSchedule(CaregiverSchedule schedule, int userId) 
//			throws SQLException {
//		// initialize connection
//		Connection conn = getConnection();
//						
//		// validate connection
//		if (conn == null) {
//			System.err.println("Connection Failed");
//		}
//		
//		// form query
//		String query = "UPDATE " + TABLE_CAREGIVER_SCHEDULES + " SET " +
//				KEY_CAREGIVER_SCHEDULES_AVAILABLE_MO + " = '" + schedule.isAvailableMo() + "', " +
//				KEY_CAREGIVER_SCHEDULES_AVAILABLE_TU + " = '" + schedule.isAvailableTu() + "', " +
//				KEY_CAREGIVER_SCHEDULES_AVAILABLE_WE + " = '" + schedule.isAvailableWe() + "', " +
//				KEY_CAREGIVER_SCHEDULES_AVAILABLE_TH + " = '" + schedule.isAvailableTh() + "', " +
//				KEY_CAREGIVER_SCHEDULES_AVAILABLE_FR + " = '" + schedule.isAvailableFr() + "', " +
//				KEY_CAREGIVER_SCHEDULES_AVAILABLE_SA + " = '" + schedule.isAvailableSa() + "', " +
//				KEY_CAREGIVER_SCHEDULES_AVAILABLE_SU + " = '" + schedule.isAvailableSu() +
//				"' WHERE " + KEY_CAREGIVER_SCHEDULES_USER_ID + " = '" + userId +"';";
//		
//		// execute
//		PreparedStatement pstmt = conn.prepareStatement(query);
//		pstmt.executeUpdate();
//		
//		// close connection
//		conn.close();
//	}
//	
//	public static CaregiverSkills getCaregiverSkills(int userId) throws SQLException {
//		// initialize connection
//		Connection conn = getConnection();
//		
//		// validate connection
//		if (conn == null) {
//			System.err.println("Connection Failed");
//			return null;
//		}
//		
//		// form query
//		String query = "SELECT * FROM " + TABLE_CAREGIVER_SKILLS + " WHERE " +
//				KEY_CAREGIVER_SKILLS_USER_ID + " = '" + userId + "';";
//		PreparedStatement pstmt = conn.prepareStatement(query);
//		
//		// gather results
//		ResultSet rs = pstmt.executeQuery(query);
//		if (rs == null) {
//			System.err.println("Could not find skills for specified user.");
//			return null;
//		} else {
//			rs.next();
//			int skillsId = rs.getInt(1);
//			String resume = rs.getString(3);
//			boolean parkinsons = rs.getBoolean(4);
//			boolean alzheimers = rs.getBoolean(5);
//			boolean stroke = rs.getBoolean(6);
//			boolean cancer = rs.getBoolean(7);
//			boolean hospitalSitter = rs.getBoolean(8);
//			
//			CaregiverSkills skills = new CaregiverSkills(skillsId, userId, resume, parkinsons, 
//					alzheimers, stroke, cancer, hospitalSitter);
//			
//			return skills;
//		}
//	}
//	
//	public static void addCaregiverSkills(CaregiverSkills skills) throws SQLException {
//		// initialize connection
//		Connection conn = getConnection();
//				
//		// validate connection
//		if (conn == null) {
//			System.err.println("Connection Failed");
//		}
//		
//		// form query
//		String query = "INSERT INTO " + TABLE_CAREGIVER_SKILLS + " (" +
//				KEY_CAREGIVER_SKILLS_USER_ID + ", " +
//				KEY_CAREGIVER_SKILLS_RESUME + ", " +KEY_CAREGIVER_SKILLS_PARKINSONS + ", " +
//				KEY_CAREGIVER_SKILLS_ALZHEIMERS + ", " + KEY_CAREGIVER_SKILLS_STROKE + ", " +
//				KEY_CAREGIVER_SKILLS_CANCER + ", " + KEY_CAREGIVER_SKILLS_HOSPITAL_SITTER +
//				") VALUES ('" + skills.getUserId() + "', '" +
//				skills.getResume() + "', '" + skills.skillParkinsons() + "', '" +
//				skills.skillAlzheimers() + "', '" + skills.skillStroke() + "', '" + skills.skillCancer()
//				+ "', '" + skills.skillHospitalSitter() + "');";
//		PreparedStatement pstmt = conn.prepareStatement(query);
//		
//		// execute
//		pstmt.execute();
//		
//		// close connection
//		conn.close();
//	}
//	
//	public static void updateCaregiverSkills(CaregiverSkills skills, int userId) 
//			throws SQLException {
//		// initialize connection
//		Connection conn = getConnection();
//						
//		// validate connection
//		if (conn == null) {
//			System.err.println("Connection Failed");
//		}
//		
//		// form query
//		String query = "UPDATE " + TABLE_CAREGIVER_SKILLS + " SET " +
//				KEY_CAREGIVER_SKILLS_RESUME + " = '" + skills.getResume() + "', " +
//				KEY_CAREGIVER_SKILLS_PARKINSONS + " = '" + skills.skillParkinsons() + "', " +
//				KEY_CAREGIVER_SKILLS_ALZHEIMERS + " = '" + skills.skillAlzheimers() + "', " +
//				KEY_CAREGIVER_SKILLS_STROKE + " = '" + skills.skillStroke() + "', " +
//				KEY_CAREGIVER_SKILLS_CANCER + " = '" + skills.skillCancer() + "', " +
//				KEY_CAREGIVER_SKILLS_HOSPITAL_SITTER + " = '" + skills.skillHospitalSitter() + "', "
//				+ " WHERE " + KEY_CAREGIVER_SKILLS_USER_ID + " = '" + userId + "';";
//		
//		// execute
//		PreparedStatement pstmt = conn.prepareStatement(query);
//		pstmt.executeUpdate();
//		
//		// close connection
//		conn.close();
//	}
//}
//
//
//
//
//
////public class Dao {
////	public static Connection getConn(){
////		String driverStr = "com.mysql.jdbc.Driver";
////		String urlStr = "jdbc:mysql://localhost:3306/cs160test";
////		String uid = "root";    
////		String pwd = "naghmehA331371"; 
////		
////		try {
////				Class.forName(driverStr);
////				return DriverManager.getConnection(urlStr, uid, pwd);
////			} catch (SQLException | ClassNotFoundException ex) {
////				System.err.println("The connection failed because " + ex.getMessage());
////				ex.printStackTrace();
////				return null;
////			}
////	} // method
////	/*	
////		public static String getPhone(String name) throws SQLException {
////			String output = name + "'s phone is ";
////			Connection conn = getConn();
////			if (conn == null) return "connection failed";
////			String sqlStr = "select * from phones where name=?;";
////			ResultSet rs = null;
////			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
////			pstmt.setString(1, name);
////			rs = pstmt.executeQuery();
////			if (rs == null)
////			output += "Name not found!";
////			else {
////			rs.next();
////			output += rs.getString(2);
////			}
////			return output;
////			} // method
////			*/
////		
////		//return Json
////	public static String getPhone(String name) throws SQLException {
////		String output = "{\"phone\" : ";
////		Connection conn = getConn();
////		if (conn == null) return output + "\"connection failed\"}";
////		String sqlStr = "select * from phones where name=?;";
////		ResultSet rs = null;
////		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
////		pstmt.setString(1, name);
////		rs = pstmt.executeQuery();
////		if (rs == null)
////			output += "\"Name not found!\"";
////		else {
////			rs.next();
////			output += "\"" + rs.getString(2) + "\"";
////		}
////		return output + "}";
////	} // method
////}
package edu.sjsu.db;

import java.security.Key;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Dao {
	// Database connection credentials
	private static final String DRIVER_STR = "com.mysql.jdbc.Driver";
	private static final String URL_STR = "jdbc:mysql://localhost:3307/cs160_project";
	private static final String DB_ID = "root";
	private static final String DB_PWD = "fion1994";

	// Database tables
	private static final String TABLE_USERS = "users";
	private static final String TABLE_CAREGIVER_SCHEDULES = "caregiver_schedules";
	private static final String TABLE_CAREGIVER_SKILLS = "caregiver_skills";

	// TABLE_USERS columns
	private static final String KEY_USERS_USER_ID = "user_id";
	private static final String KEY_USERS_EMAIL = "email";
	private static final String KEY_USERS_PASSWORD = "password";
	private static final String KEY_USERS_FIRST_NAME = "firstName";
	private static final String KEY_USERS_LAST_NAME = "lastName";
	private static final String KEY_USERS_DOB = "dob";
	private static final String KEY_USERS_GENDER = "gender";
	private static final String KEY_USERS_ADDRESS = "address";
	private static final String KEY_USERS_CITY = "city";
	private static final String KEY_USERS_STATE = "state";
	private static final String KEY_USERS_ZIPCODE = "zipcode";
	private static final String KEY_USERS_ACCOUNT_TYPE = "account_type";
	private static final String KEY_USERS_VERIFIED_EMAIL = "verified_email";
	private static final String KEY_USERS_VERIFIED_CAREGIVER = "verified_caregiver";

	// TABLE_CAREGIVER_SCHEDULES columns
	private static final String KEY_CAREGIVER_SCHEDULES_SCHEDULE_ID = "schedule_id";
	private static final String KEY_CAREGIVER_SCHEDULES_USER_ID = "user_id";
	private static final String KEY_CAREGIVER_SCHEDULES_AVAILABLE_MO = "available_monday";
	private static final String KEY_CAREGIVER_SCHEDULES_AVAILABLE_TU = "available_tuesday";
	private static final String KEY_CAREGIVER_SCHEDULES_AVAILABLE_WE = "available_wednesday";
	private static final String KEY_CAREGIVER_SCHEDULES_AVAILABLE_TH = "available_thursday";
	private static final String KEY_CAREGIVER_SCHEDULES_AVAILABLE_FR = "available_friday";
	private static final String KEY_CAREGIVER_SCHEDULES_AVAILABLE_SA = "available_saturday";
	private static final String KEY_CAREGIVER_SCHEDULES_AVAILABLE_SU = "available_sunday";

	// TABLE_CAREGIVER_SKILLS columns
	private static final String KEY_CAREGIVER_SKILLS_SKILLS_ID = "skills_id";
	private static final String KEY_CAREGIVER_SKILLS_USER_ID = "user_id";
	private static final String KEY_CAREGIVER_SKILLS_RESUME = "resume";
	private static final String KEY_CAREGIVER_SKILLS_PARKINSONS = "parkinsons";
	private static final String KEY_CAREGIVER_SKILLS_ALZHEIMERS = "alzheimers";
	private static final String KEY_CAREGIVER_SKILLS_STROKE = "stroke";
	private static final String KEY_CAREGIVER_SKILLS_CANCER = "cancer";
	private static final String KEY_CAREGIVER_SKILLS_HOSPITAL_SITTER = "hospital_sitter";

	// Misc
	private static final String KEY = "SjsuFall2016CS160Optiplex";

	public static Connection getConnection() {
		try {
			Class.forName(DRIVER_STR);
			return DriverManager.getConnection(URL_STR, DB_ID, DB_PWD);
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("The connection failed because " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	// private static String encrypt(String pwd) {
	// try {
	// Key aesKey = new SecretKeySpec(KEY.getBytes(), "AES");
	// Cipher cipher = Cipher.getInstance("AES");
	// cipher.init(Cipher.ENCRYPT_MODE, aesKey);
	// byte[] encryptedPwd = cipher.doFinal(pwd.getBytes());
	// return new String(encryptedPwd);
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

	public static User getUser(String email, String pwd) throws SQLException {
		// initialize connection
		Connection conn = getConnection();

		// validate connection
		if (conn == null) {
			System.err.println("Connection Failed");
			return null;
		}

		// get encrypted password
		String encryptedPwd = pwd;// encrypt(pwd);

		User user = null;
		try {
			PreparedStatement preparedStatement = conn
					.prepareStatement("SELECT * FROM `users` WHERE email = ? AND password = ?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, encryptedPwd);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int userId = rs.getInt(1);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				String dob = rs.getString(6);
				String gender = rs.getString(7);
				String address = rs.getString(8);
				String city = rs.getString(9);
				String state = rs.getString(10);
				String zipCode = rs.getString(11);
				String accType = rs.getString(12);

				user = new User(userId, email, pwd, firstName, lastName, dob, gender, address, city, state, zipCode,
						accType);
			}
			preparedStatement.close();

		} catch (SQLException e) {
			System.out.println("UNABLE TO SELECT USER");
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return user;
	}

	public static String addUser(String email, String password, String firstName, String lastName, String dob,
			String gender, String address, String city, String state, String zipCode, String accType)
			throws SQLException {
		// initialize connection
		Connection conn = getConnection();

		// validate connection
		if (conn == null) {
			System.err.println("Connection Failed");
		}

		try {
			PreparedStatement preparedStatement = conn
					.prepareStatement(
							"INSERT INTO `users` (email, password, first_name, last_name, "
							+ "dob, gender, address, city, state, zipcode, account_type) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, firstName);
			preparedStatement.setString(4, lastName);
			preparedStatement.setString(5, dob);
			preparedStatement.setString(6, gender);
			preparedStatement.setString(7, address);
			preparedStatement.setString(8, city);
			preparedStatement.setString(9, state);
			preparedStatement.setString(10, zipCode);
			preparedStatement.setString(11, accType);
			// execute
			preparedStatement.execute();

			// close pareparedstatement
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("UNABLE TO INSERT USER");
			e.printStackTrace();
			return "" + e;
		} finally {
			// close connection
			conn.close();
		}
		return "Successful";
	}

	public static String updateUser(String email, String password, String firstName, String lastName, String dob,
			String gender, String address, String city, String state, String zipCode, String accType)
			throws SQLException {
		// initialize connection
		Connection conn = getConnection();

		// validate connection
		if (conn == null) {
			System.err.println("Connection Failed");
		}

		try {
			PreparedStatement preparedStatement = conn
					.prepareStatement(
							"UPDATE FROM `users` "
							+ "SET email = ?, password = ?, first_name = ?, last_name = ?, dob = ?, gender = ?, "
							+ "address = ?, city = ?, state = ?, zipcode = ?, account_type = ? "
							+ "WHERE email = ?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, firstName);
			preparedStatement.setString(4, lastName);
			preparedStatement.setString(5, dob);
			preparedStatement.setString(6, gender);
			preparedStatement.setString(7, address);
			preparedStatement.setString(8, city);
			preparedStatement.setString(9, state);
			preparedStatement.setString(10, zipCode);
			preparedStatement.setString(11, accType);
			preparedStatement.setString(12, email);
			// execute
			preparedStatement.execute();
			// close pareparedstatement
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return "" + e;
		} finally {
			conn.close();
		}
		return "Successful";
	}

	public static CaregiverSchedule getCaregiverSchedule(int userId) throws SQLException {
		// initialize connection
		Connection conn = getConnection();

		// validate connection
		if (conn == null) {
			System.err.println("Connection Failed");
			return null;
		}

		CaregiverSchedule schedule = null;
		try {
			PreparedStatement preparedStatement = conn
					.prepareStatement("SELECT * FROM `caregiver_schedules` WHERE user_id = ?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int scheduleId = rs.getInt(1);
				boolean availableMo = rs.getBoolean(2);
				boolean availableTu = rs.getBoolean(3);
				boolean availableWe = rs.getBoolean(4);
				boolean availableTh = rs.getBoolean(5);
				boolean availableFr = rs.getBoolean(6);
				boolean availableSa = rs.getBoolean(7);
				boolean availableSu = rs.getBoolean(8);

				schedule = new CaregiverSchedule(scheduleId, userId, availableMo, availableTu, availableWe, availableTh,
						availableFr, availableSa, availableSu);
			}
			preparedStatement.close();

		} catch (SQLException e) {
			System.out.println("UNABLE TO SELECT USER");
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return schedule;
	}

	public static String addCaregiverSchedule(int userId, boolean mon, boolean tue, boolean wed, boolean thu,
			boolean fri, boolean sat, boolean sun) throws SQLException {
		// initialize connection
		Connection conn = getConnection();

		// validate connection
		if (conn == null) {
			System.err.println("Connection Failed");
		}

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(
					"INSERT INTO `caregiver_schedules` (user_id, available_monday, available_tuesday, available_wednesday, "
					+ "available_thursday, available_friday, available_saturday, available_sunday)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, userId);
			preparedStatement.setBoolean(2, mon);
			preparedStatement.setBoolean(3, tue);
			preparedStatement.setBoolean(4, wed);
			preparedStatement.setBoolean(5, thu);
			preparedStatement.setBoolean(6, fri);
			preparedStatement.setBoolean(7, sat);
			preparedStatement.setBoolean(8, sun);

			// execute
			preparedStatement.execute();

			// close pareparedstatement
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("UNABLE TO INSERT SCHEDULE");
			e.printStackTrace();
			return "" + e;
		} finally {
			// close connection
			conn.close();
		}
		return "Successful";
	}

	public static String updateCaregiverSchedule(int userId, boolean mon, boolean tue, boolean wed, boolean thu,
			boolean fri, boolean sat, boolean sun) throws SQLException {
		// initialize connection
		Connection conn = getConnection();

		// validate connection
		if (conn == null) {
			System.err.println("Connection Failed");
		}

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(
					"UPDATE FROM `caregiver_schedules` "
					+ "SET available_monday = ?, available_tuesday = ?, available_wednesday = ?, "
					+ "available_thursday = ?, available_friday = ?, available_saturday = ?, available_sunday = ?)"
					+ "WHERE user_id = ?");
			preparedStatement.setBoolean(1, mon);
			preparedStatement.setBoolean(2, tue);
			preparedStatement.setBoolean(3, wed);
			preparedStatement.setBoolean(4, thu);
			preparedStatement.setBoolean(5, fri);
			preparedStatement.setBoolean(6, sat);
			preparedStatement.setBoolean(7, sun);
			preparedStatement.setInt(8, userId);

			// execute
			preparedStatement.execute();

			// close pareparedstatement
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("UNABLE TO UPDATE SCHEDULE");
			e.printStackTrace();
			return "" + e;
		} finally {
			// close connection
			conn.close();
		}
		return "Successful";
	}

	public static CaregiverSkills getCaregiverSkills(int userId) throws SQLException {
		// initialize connection
		Connection conn = getConnection();

		// validate connection
		if (conn == null) {
			System.err.println("Connection Failed");
			return null;
		}

		CaregiverSkills skills = null;
		try {
			PreparedStatement preparedStatement = conn
					.prepareStatement("SELECT * FROM `caregiver_skills` WHERE user_id = ?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int skillsId = rs.getInt(1);
				String resume = rs.getString(3);
				boolean parkinsons = rs.getBoolean(4);
				boolean alzheimers = rs.getBoolean(5);
				boolean stroke = rs.getBoolean(6);
				boolean cancer = rs.getBoolean(7);
				boolean hospitalSitter = rs.getBoolean(8);

				skills = new CaregiverSkills(skillsId, userId, resume, parkinsons, alzheimers, stroke, cancer,
						hospitalSitter);
			}
			preparedStatement.close();

		} catch (SQLException e) {
			System.out.println("UNABLE TO SELECT SKILL");
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return skills;
	}

	public static String addCaregiverSkills(int userId, String resume, boolean parkinsons, boolean alzheimers,
			boolean stroke, boolean cancer, boolean hospitalSitter) throws SQLException {
		// initialize connection
		Connection conn = getConnection();

		// validate connection
		if (conn == null) {
			System.err.println("Connection Failed");
		}

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(
					"INSERT INTO `caregiver_skills` (user_id, resume, parkinsons, alzheimers, stroke, cancer, hospital_sitter)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, resume);
			preparedStatement.setBoolean(3, parkinsons);
			preparedStatement.setBoolean(4, alzheimers);
			preparedStatement.setBoolean(5, stroke);
			preparedStatement.setBoolean(6, cancer);
			preparedStatement.setBoolean(7, hospitalSitter);

			// execute
			preparedStatement.execute();

			// close pareparedstatement
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("UNABLE TO INSERT SKILLS");
			e.printStackTrace();
			return "" + e;
		} finally {
			// close connection
			conn.close();
		}
		return "Successful";
	}

	public static String updateCaregiverSkills(int userId, String resume, boolean parkinsons, boolean alzheimers,
			boolean stroke, boolean cancer, boolean hospitalSitter) throws SQLException {
		// initialize connection
		Connection conn = getConnection();

		// validate connection
		if (conn == null) {
			System.err.println("Connection Failed");
		}

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(
					"UPDATE FROM `caregiver_skills` "
					+ "SET resume = ?, parkinsons = ?, alzheimers = ?, stroke = ?, cancer = ?, hospital_sitter = ?)"
					+ "WHERE user_id = ?");
			preparedStatement.setString(1, resume);
			preparedStatement.setBoolean(2, parkinsons);
			preparedStatement.setBoolean(3, alzheimers);
			preparedStatement.setBoolean(4, stroke);
			preparedStatement.setBoolean(5, cancer);
			preparedStatement.setBoolean(6, hospitalSitter);
			preparedStatement.setInt(7, userId);

			// execute
			preparedStatement.execute();

			// close pareparedstatement
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("UNABLE TO UPDATE SKILL");
			e.printStackTrace();
			return "" + e;
		} finally {
			// close connection
			conn.close();
		}
		return "Successful";
	}
}
