package pl.coderslab.workshops.userDaoclass;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainDao {
    public static void main(String[] args) {
//        ADD
//        Check if adding user and table works:
//        UserDao userDao = new UserDao();
//        User user = new User();
//        user.setUserName("arek");
//        user.setEmail("arkadiusz.jozwiak@coderslab.pl");
//        user.setPassword("pass");
//        userDao.createTableUsers();
//        userDao.create(user);


//        READ
//        Check if read method works:
//        UserDao userDao = new UserDao();
//        User user1 = userDao.read(1);
//        System.out.printf("%d     |%s               |%s        |%s",user1.getId(),user1.getEmail(),user1.getUserName(), user1.getPassword());

//        UPDATE
//        UserDao userDao = new UserDao();
//        User userToUpdate = userDao.read(1);
//        userToUpdate.setUserName("Arkadiusz");
//        userToUpdate.setEmail("arek@coderslab.pl");
//        userDao.update(userToUpdate);
//        userToUpdate.setPassword("aGaTkA");
//        userDao.updatePassword(userToUpdate);


//        DELETE
//        UserDao userDao = new UserDao();
//        userDao.delete(2);

        UserDao userDao = new UserDao();
        userDao.delete(3);
        User[] arrCheck = userDao.findAll();
        for (User u : arrCheck) {
            System.out.printf("%d     |%s               |%s        |%s%n",u.getId(),u.getEmail(),u.getUserName(), u.getPassword());
        }


    }
}
