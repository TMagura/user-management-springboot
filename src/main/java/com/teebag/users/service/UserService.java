package service;




import com.teebag.users.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    boolean deleteUser (Long id);

    User updateUser(Long id, User user);
}
