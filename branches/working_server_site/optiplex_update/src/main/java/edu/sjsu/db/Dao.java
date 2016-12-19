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
import java.util.ArrayList;

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
    
    public static ArrayList<User> getAllUsers() throws SQLException {
		// initialize connection
		Connection conn = getConnection();

		// validate connection
		if (conn == null) {
			System.err.println("Connection Failed");
			return null;
		}

		ArrayList<User> listUser = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn
					.prepareStatement("SELECT * FROM `users` WHERE account_type = 'Caregiver'");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt(1);
                String email = rs.getString(2);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				String city = rs.getString(9);
				String state = rs.getString(10);
				
                User user = new User(userId, email, null, firstName, lastName, null, null, null, city, state, null, null); 
				listUser.add(user); 
			}
			preparedStatement.close();

		} catch (SQLException e) {
			System.out.println("UNABLE TO SELECT USER");
			e.printStackTrace();
		} finally {
			conn.close();
		}
        return listUser;
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
