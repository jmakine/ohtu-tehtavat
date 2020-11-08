package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        /* K�ytt�j�tunnuksen on oltava merkeist� a-z koostuva v�hint��n 3 merkin pituinen merkkijono, joka ei ole viel� k�yt�ss�.
           Salasanan on oltava pituudeltaan v�hint��n 8 merkki� ja se ei saa koostua pelk�st��n kirjaimista.
        */
        if(username.length() > 2 && userDao.findByName(username)==null && username.matches("[a-zA-Z]*") && password.length() > 8 && !password.matches("[a-zA-Z]*") ){
            return false;
        }
        return true;
    }
}
