package com.redabens.mypet.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users", schema = "public", catalog = "mypet")
@Data
@NoArgsConstructor

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "email", unique = true)
    private String email;
    @JsonIgnore
    @Column(name = "password")
    private String password;
    @Column(name = "address")
    private String address;
    @Column(name = "number_phone")
    private String numberPhone;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Post> posts;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Comment> comments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public User orElseThrow(Object user_not_found) {
        return null;
    }
}
