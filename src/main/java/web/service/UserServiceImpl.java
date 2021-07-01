package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;


//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public void add(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword())); не знаю почему не отрабатывает
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }

    @Override
    public void update(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public Optional<User> getById(long id) {
        return userDao.findById(id);
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getByName(s);
        return user;
    }
}
