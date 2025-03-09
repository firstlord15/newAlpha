package org.ithub.userservice.service;

import org.ithub.userservice.dto.UserDto;
import org.ithub.userservice.enums.Role;
import org.ithub.userservice.model.User;
import org.ithub.userservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserDto createUser(UserDto userDto, String rawPassword) {
        String hashedPassword = passwordEncoder.encode(rawPassword);
        User user = new User(userDto.getEmail(), userDto.getUsername(), hashedPassword, userDto.getNumber(), Role.USER);
        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    public UserDto createUser(UserDto userDto, String rawPassword, Role role) {
        String hashedPassword = passwordEncoder.encode(rawPassword);
        User user = new User(userDto.getEmail(), userDto.getUsername(), hashedPassword, userDto.getNumber(), role);
        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    public boolean checkPasswordByUserId(Long userId, String rawPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Users not found"));
        return mapToDto(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Users not found"));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        // Обновление других полей по необходимости
        User updatedUser = userRepository.save(user);
        return mapToDto(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDto mapToDto(User user) {
        return new UserDto(user.getEmail(), user.getUsername(), user.getNumber(), user.getRole());
    }
}
