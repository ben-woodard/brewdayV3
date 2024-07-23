package com.coderscampus.SpringSecurityJWTDemo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Authority implements GrantedAuthority {
    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;
    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Authority () {}

    public Authority(String authority) {
		super();
		this.authority = authority;
	}

	public Authority(String auth, User user) {
        this.authority = auth;
        this.user = user;
    }

    @Override
	public String toString() {
		return "Authority [id=" + id + ", authority=" + authority + "]";
	}
}
