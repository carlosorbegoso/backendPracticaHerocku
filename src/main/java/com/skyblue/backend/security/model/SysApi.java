package com.skyblue.backend.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

import com.skyblue.backend.security.repository.DataChange;


@Table("sys_api")
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class SysApi implements DataChange {

    @Id
    private long id;

    private String name;

    private String url;

    private String remark;

    private String createBy;

    private java.time.LocalDateTime createTime;

    private String lastUpdateBy;

    private java.time.LocalDateTime lastUpdateTime;

    @Transient   // does not exist with the field in the database
    private List<String> roles;

    @Override
    public void setCreateBy(String createBy) {

    }

    @Override
    public void setCreateTime(LocalDateTime createTime) {

    }

    @Override
    public void setLastUpdateBy(String lastUpdateBy) {

    }

    @Override
    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {

    }
}
