package com.lwk.blog.controller;

import com.lwk.blog.annotation.SystemLog;
import com.lwk.blog.pojo.Authorities;
import com.lwk.blog.pojo.Users;
import com.lwk.blog.service.IAuthoritiesService;
import com.lwk.blog.service.IUserService;
import com.lwk.blog.utils.CustomUserDetails;
import com.lwk.blog.utils.JwtTokenUtil;
import com.lwk.blog.utils.LoginUserInfo;
import com.lwk.blog.vo.LoginVo;
import com.lwk.blog.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author lwk
 * @Description 登录控制器
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthoritiesService authoritiesService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    @SystemLog("登录")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody LoginVo loginVo) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginVo.getUsername(),
                        loginVo.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }


    @Transactional
    @PostMapping("/register")
    @SystemLog("注册")
    public ResponseEntity<?> register(@RequestBody RegisterVo registerVo) {
        if (userService.findByUsername(registerVo.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("用户名已存在");
        }

        Users newUsers = new Users();
        Authorities authorities = new Authorities();
        newUsers.setUsername(registerVo.getUsername());
        newUsers.setPassword(passwordEncoder.encode(registerVo.getPassword()));
        newUsers.setEmail(registerVo.getEmail());
        authorities.setAuthority("USER");
        authorities.setUsername(registerVo.getUsername());

        userService.save(newUsers);
        authoritiesService.save(authorities);

        return ResponseEntity.ok("注册成功");
    }

    @GetMapping("/me")
    @PreAuthorize("hasAuthority('USER')")
    @SystemLog("获取个人信息")
    public ResponseEntity<CustomUserDetails> getCurrentUser() {
        CustomUserDetails currentUser = (CustomUserDetails) LoginUserInfo.getCurrentUser();
        currentUser.setPassword(null);
        return ResponseEntity.ok(currentUser);
    }
}

