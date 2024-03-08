package com.example.viacademy.services.impls;

import com.example.viacademy.entities.User;
import com.example.viacademy.mapper.UserMapper;
import com.example.viacademy.repositories.IUserRepository;
import com.example.viacademy.services.IUserService;
import com.example.viacademy.web.requests.CreateUserRequest;
import com.example.viacademy.web.responses.BaseResponse;
import com.example.viacademy.web.responses.CreateUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;


    public UserServiceImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = userMapper;
    }

    @Override
    public BaseResponse get(Long id) {
        User user = this.findOneAndEnsureExist(id);

        return BaseResponse.builder()
                .data(toCreateUserResponse(user))
                .message("User retrieved")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public BaseResponse create(CreateUserRequest request) {
        if (repository.findOneByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = repository.save(from(request));

        return BaseResponse.builder()
                .data(toCreateUserResponse(user))
                .message("User created")
                .success(Boolean.TRUE)
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public User findOneAndEnsureExist(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User doesn't exist"));
    }

    @Override
    public User findOneAndEnsureExist(String email) {
        return null;
    }

    private User from(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encodePassword(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());

        return user;
    }

    private CreateUserResponse toCreateUserResponse(User user) {
        return mapper.userToCreateUserResponse(user);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
