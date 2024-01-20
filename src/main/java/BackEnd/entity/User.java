package BackEnd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false,unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String email;
    @JoinTable(name = "users_roles",
         joinColumns =   @JoinColumn(name = "user_id",referencedColumnName = "id"),
         inverseJoinColumns =   @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    @ManyToMany(fetch = FetchType.EAGER, cascade =CascadeType.ALL )
    private Set<Role> roles;
}
