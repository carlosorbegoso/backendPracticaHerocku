package com.skyblue.backend.security.controller;

// import com.ffzs.webflux.system_app.service.UserDataFaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

import com.skyblue.backend.security.model.SysHttpResponse;
import com.skyblue.backend.security.model.SysUser;
import com.skyblue.backend.security.service.SysUserService;



@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
@Slf4j
@CrossOrigin
public class SysUserController {

    private final SysUserService sysUserService;
    // private final UserDataFaker userDataFaker;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'IT', 'HR')")
    Mono<SysHttpResponse> findByName (@RequestParam("username") String username) {
        return sysUserService.findByUsername(username)
                .map(SysHttpResponse::ok)
                .onErrorResume(e -> Mono.just(SysHttpResponse.error5xx(e.getMessage(), e)));
    }

    @GetMapping("all")
    @PreAuthorize("hasAnyRole('ADMIN', 'IT', 'HR')")
    Flux<SysUser> findAll () {
        return sysUserService.findAll();
    }

    @GetMapping("fake")
    Flux<SysUser> fake (@RequestParam("count") Long count) throws IOException {
        // return userDataFaker.fakeUserData(count);
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN', 'IT')")
    @ResponseStatus(value = HttpStatus.OK)
    Mono<SysHttpResponse> save (@RequestBody SysUser user) {

        return sysUserService.save(user)
                .map(it->SysHttpResponse
                        .builder()
                        .status(HttpStatus.OK.value())
                        .message("Stored successfully")
                        .data(it.withPassword(null))
                        .build()
                )
                .onErrorResume(err -> {
                    SysHttpResponse response = new SysHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error running", err.getMessage());
                    if (err instanceof DataIntegrityViolationException) {
                        response = new SysHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Some fields of stored data are not unique", err.getMessage());
                    }
                    return Mono.just(response);
                });
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'IT')")
    Mono<SysHttpResponse> update (@RequestBody SysUser user) {
        return sysUserService.save(user)
                .map(SysHttpResponse::ok)
                .onErrorResume(e -> Mono.just(SysHttpResponse.error5xx(e.getMessage(), e)));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    Mono<Void> deleteById (@RequestParam("id") long id) {
        return sysUserService.deleteById(id);
    }

}
