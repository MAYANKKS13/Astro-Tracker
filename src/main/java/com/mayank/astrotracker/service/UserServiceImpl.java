package com.mayank.astrotracker.service;

import com.mayank.astrotracker.dto.CreateUserRequest;
import com.mayank.astrotracker.dto.UserResponse;
import com.mayank.astrotracker.exception.ResourceNotFoundException;
import com.mayank.astrotracker.repository.UserRepository;
import com.mayank.astrotracker.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

        @Override
        public UserResponse createUser(CreateUserRequest request) {

            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setLatitude(request.getLatitude());
            user.setLongitude(request.getLongitude());
            user.setTimezone(request.getTimezone());

            User savedUser = userRepository.save(user);
            return mapToResponse(savedUser);
        }

        @Override
        public List<UserResponse> getAllUsers() {
            return userRepository.findAll().stream()
                    .map(this::mapToResponse)
                    .toList();
        }

        @Override
        public UserResponse getUserById(Long id) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            return mapToResponse(user);
        }

        @Override
        public void deleteUser(Long id) {
            userRepository.deleteById(id);
        }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .latitude(user.getLatitude())
                .longitude(user.getLongitude())
                .timezone(user.getTimezone())
                .build();
    }
}
