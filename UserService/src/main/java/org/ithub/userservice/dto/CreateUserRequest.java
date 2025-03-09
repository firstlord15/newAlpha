package org.ithub.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ithub.userservice.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String email;
    private String username;
    private String password;
    private Role role;

    public UserDto toUserDto() {
        return new UserDto(email, username, email, role);
    }
}
