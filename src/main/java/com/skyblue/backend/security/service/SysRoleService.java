package com.skyblue.backend.security.service;

// import com.ffzs.webflux.system_app.utils.ReadExcelUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.skyblue.backend.security.model.SysRole;
import com.skyblue.backend.security.repository.SysRoleRepository;

@Service
@AllArgsConstructor
@Slf4j
@Order(1)
public class SysRoleService {

    private final SysRoleRepository sysRoleRepository;
    private final MarkDataService mark;


    public Mono<SysRole> findByName (String name) {
        return sysRoleRepository.findByName(name);
    }

    public Mono<SysRole> save (SysRole role) {
        if (role.getId() == 0) {
            return mark.createObj(role)
                    .flatMap(sysRoleRepository::save);
        }
        return mark.updateObj(role)
                .flatMap(sysRoleRepository::save);
    }

    public Flux<SysRole> findAll () {
        return sysRoleRepository.findAll();
    }

    public Mono<Void> delete (Long id) {
        return sysRoleRepository.deleteById(id);
    }
}
