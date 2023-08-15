package com.example.demo.user.adapter.out.persistence;

import com.example.demo.common.annotaion.PersistenceAdapter;
import com.example.demo.common.exception.UserNotFoundException;
import com.example.demo.common.utils.TokenProvider;
import com.example.demo.user.application.port.out.LoadUserPort;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@PersistenceAdapter
class UserLoadPersistenceAdapter implements LoadUserPort {
    private final SpringDataUserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User loadById(Long id) {
        UserJpaEntity userJpaEntity =
                userRepository.findById(id)
                        .orElseThrow(UserNotFoundException::new);
        return userMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    public User loadByEmail(String email) {
        UserJpaEntity userJpaEntity =
                userRepository.findByEmail(email)
                        .orElseThrow(UserNotFoundException::new);
        return userMapper.mapToDomainEntity(userJpaEntity);
    }


    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<User> searchUserList() {
        List<UserJpaEntity> userJpaEntityList = userRepository.findAll();
        System.out.println(userJpaEntityList);
        List<User> userList = userJpaEntityList.stream()
                .map((userJpaEntity) -> userMapper.mapToDomainEntity(userJpaEntity))
                .collect(Collectors.toList());
        return userList;
    }


}
