package org.ithub.userservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ithub.userservice.enums.Role;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String number;

    @Column(nullable = false)
    private Role role;

    public User(String email, String username, String password, String number, Role role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.number = number;
        this.role = role;
    }
}
