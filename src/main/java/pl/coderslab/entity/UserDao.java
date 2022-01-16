package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.workshops.userDaoclass.DBUtil;

import java.sql.*;

public class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String CREATE_TABLE_USERS_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS users(\n" +
            "                      id INT(11) NOT NULL AUTO_INCREMENT,\n" +
            "                      email VARCHAR(255) NOT NULL UNIQUE,\n" +
            "                      username VARCHAR(255) NOT NULL,\n" +
            "                      password VARCHAR(60) NOT NULL,\n" +
            "                      PRIMARY KEY (id)\n" +
            ");";
    private static final String USERS_WHERE_ID = "SELECT id FROM users WHERE id = ?";
    private static final String SELECT_FROM_USERS_WHERE_ID = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_EMAIL_USERNAME = "UPDATE users SET email = ?, username = ? WHERE id = ?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE id = ?";


    public User create(User user) {
        try (Connection conn = DBUtil.getConnection()) {

            PreparedStatement preStmt = conn.prepareStatement(CREATE_USER_QUERY,  Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1,user.getUserName());
            preStmt.setString(2,user.getEmail());
            preStmt.setString(3,getPasswordHashed(user));
            preStmt.executeUpdate();

            ResultSet rs = preStmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            return user;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Can not add new user to DB.",ex);
        }

    }

    public String getPasswordHashed(User user) {
        return BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    }

    public void createTableUsers() {
        try (Connection conn = DBUtil.connect("workshop2")) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(CREATE_TABLE_USERS_IF_NOT_EXISTS);

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Can not create table users.", ex);
        }
    }

    public User read(int userId) {
        try (Connection conn = DBUtil.connect("workshop2")) {
            PreparedStatement preStmt = conn.prepareStatement(USERS_WHERE_ID);
            preStmt.setInt(1, userId);
            ResultSet rs = preStmt.executeQuery();
            if (rs.isBeforeFirst()){
                User user = new User();
                PreparedStatement preStmt2 = conn.prepareStatement(SELECT_FROM_USERS_WHERE_ID);
                preStmt2.setInt(1, userId);
                ResultSet result = preStmt2.executeQuery();
                if (result.next()) {
                    user.setId(userId);
                    user.setUserName(result.getString("username"));
                    user.setEmail(result.getString("email"));
                    user.setPassword(result.getString("password"));
                }
                return user;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Can not read this user.", ex);
        }

    }

    public void update(User user) {
        try (Connection conn = DBUtil.connect("workshop2")) {
            PreparedStatement preStmt = conn.prepareStatement(UPDATE_USER_EMAIL_USERNAME);
            preStmt.setString(1, user.getEmail());
            preStmt.setString(2, user.getUserName());
            preStmt.setInt(3,user.getId());
            preStmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Can not update the user.",ex);
        }

    }

    public void updatePassword(User user) {
        try (Connection conn = DBUtil.connect("workshop2")) {
            PreparedStatement preStmt = conn.prepareStatement(UPDATE_PASSWORD);
            preStmt.setString(1, getPasswordHashed(user));
            preStmt.setInt(2,user.getId());
            preStmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Can not update the password.",ex);
        }
    }


}
