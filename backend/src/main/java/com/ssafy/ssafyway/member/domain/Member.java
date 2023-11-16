package com.ssafy.ssafyway.member.domain;

import com.ssafy.ssafyway.global.domain.BaseEntity;
import com.ssafy.ssafyway.member.domain.embbeded.Email;
import com.ssafy.ssafyway.member.domain.embbeded.Password;
import com.ssafy.ssafyway.member.domain.embbeded.PhoneNumber;
import com.ssafy.ssafyway.wishlist.domain.Wishlist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private PhoneNumber phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Wishlist> wishlists = new ArrayList<>();

    @Builder
    public Member(
            String name,
            Email email,
            Password password,
            PhoneNumber phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        role = Role.USER;
    }

    public String getEmail() {
        return email.getValue();
    }

    public String getPhoneNumber() {
        return phoneNumber.getValue();
    }
    public String getEncryptedPassword() {
        return password.getValue();
    }

}
