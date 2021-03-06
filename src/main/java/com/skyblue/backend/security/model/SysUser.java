package com.skyblue.backend.security.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

import com.skyblue.backend.security.repository.DataChange;


@Table("sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Builder
public class SysUser implements DataChange {

    @Id
    private long id;

    private String username;

    private String avatar;

//    @JsonIgnore   // The request returns fields that json does not want to display
    private String password;

    private String email;

    private String mobile;

    private long frozen;

    private String createBy;

    private java.time.LocalDateTime createTime;

    private String lastUpdateBy;

    private java.time.LocalDateTime lastUpdateTime;

    @Transient   // does not exist with the field in the database
    private List<String> roles;

}
