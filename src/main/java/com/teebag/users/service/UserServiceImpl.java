package com.teebag.users.service;


import com.teebag.users.entity.UserEntity;
import com.teebag.users.model.User;
import com.teebag.users.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements service.UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userRepository.save(userEntity);
        return user;
    }
//we are taking the data from the entity to the user {entity to model}
    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities =userRepository.findAll();
        List<User> users = userEntities
                .stream()
                .map(userEntity -> new User(
                        userEntity.getId(),
                        userEntity.getFirstname(),
                        userEntity.getLastname(),
                        userEntity.getEmailId()
                )).collect(Collectors.toList());
        return users;
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity= userRepository.findById(id).get();
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        userRepository.delete(userEntity);
        return  true;
    }
//userEntity communicated directly with database but we storing our data in the model which is users so we return actual data.
    @Override
    public User updateUser(Long id, User user) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setEmailId(user.getEmailId());
        userEntity.setFirstname(user.getFirstname());
        userEntity.setLastname(user.getLastname());

        userRepository.save(userEntity);
        return  user;
    }

}
