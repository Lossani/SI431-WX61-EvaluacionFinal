package com.gamingworld.app.gamingworld.security.domain.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements org.springframework.security.core.userdetails.UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;

    private boolean enable;

    @Column(length = 40, nullable = false)
    private String lastName;

    @Column(length = 40, nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private Boolean premium = false;

    @Column(length = 30, nullable = false)
    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Authority> authorities;

    private Boolean enabled = true;

    private Boolean accountNonExpired = true;
    private Boolean credentialsNonExpired = true;
    private Boolean accountNonLocked = true;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnable();
    }

    public User() {
        this.enable = true;
        this.authorities = new ArrayList<>();
    }
    public User( String username, String password ) {
        this.username = username;
        this.password = password;
        this.enable = true;
        this.authorities = new ArrayList<>();
    }

    public void addAuthority( String _authority ) {
        Authority authority = new Authority();
        authority.setAuthority( _authority );
        authority.setUser(this);
        this.authorities.add(authority);
    }
}
