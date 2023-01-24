package com.maxisoft.fileSystem.services;

import com.maxisoft.fileSystem.models.Status;
import com.maxisoft.fileSystem.models.User;
import com.maxisoft.fileSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService implements GenericService<User, Long> {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user.getStatus() == null) {
            user.setStatus(Status.NEW);
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        if (user.getStatus() == null || !user.getStatus().equals(Status.UPDATED)) {
            user.setStatus(Status.UPDATED);
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
