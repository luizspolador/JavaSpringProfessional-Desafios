package com.devsuperior.demo.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    private Role(){

    }

    public Role(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Role role)) return false;
        return Objects.equals(getAuthority(), role.getAuthority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthority());
    }
}
