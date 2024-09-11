package com.vtn.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity implements Serializable {

    @NotNull(message = "{customer.firstName.notNull}")
    @NotBlank(message = "{customer.firstName.notNull}")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "{customer.middleName.notNull}")
    @NotBlank(message = "{customer.middleName.notNull}")
    @Column(nullable = false)
    private String middleName;

    @NotNull(message = "{customer.lastName.notNull}")
    @NotBlank(message = "{customer.lastName.notNull}")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "{customer.address.notNull}")
    @NotBlank(message = "{customer.address.notNull}")
    @Column(nullable = false)
    private String address;

    @NotNull(message = "{user.phone.notNull}")
    @NotBlank(message = "{user.phone.notNull}")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "{user.phone.pattern}")
    @Column(nullable = false, length = 15)
    private String phone;

    @Builder.Default
    @Column(columnDefinition = "TINYINT(1) default 1")
    private Boolean gender = true; // true (1): Nữ - false (0): Nam

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public String getFullName() {
        return this.lastName + " " + this.middleName + " " + this.firstName;
    }

    @Override
    public String toString() {
        return "com.fh.scm.pojo.Customer[ id=" + this.id + " ]";
    }
}
