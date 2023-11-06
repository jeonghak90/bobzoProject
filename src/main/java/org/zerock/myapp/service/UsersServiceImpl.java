package org.zerock.myapp.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Users;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.repository.UsersRepository;

import java.util.Optional;

@Log4j2

@Service
public class UsersServiceImpl implements UsersService{
    @Setter(onMethod_ = @Autowired)
    private UsersRepository usersRepo;

    @Setter(onMethod_ = @Autowired)
    private PasswordEncoder passwordEncoder;



    @Override
    public void register_user(UsersDTO usersDTO){

        Users users = new Users();
        users.setUserId(usersDTO.getUserId());
        users.setEmail(usersDTO.getEmail());
        users.setBirthdate(usersDTO.getBirthdate());

        String rawPassword = usersDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        users.setPassword(encodedPassword);

        users.setNickName(usersDTO.getNickName());

        this.usersRepo.save(users);
    } // register_user



    @Override
    public Users getUserById(String userId) {
        Optional<Users> usersOptional = usersRepo.findByUserId(userId);

        if(usersOptional.isPresent()){
            return usersOptional.get();
        } else {
            return null;
        } // if-else
    } // getUserById


    @Override
    public boolean existsByUserInfo(String value, String keyWord) {
        log.info("☆★유저서비스☆★ - 중복검사 Repository 진입");
        log.info("\t☆★ [유저서비스] key : {} ★☆", keyWord);

        boolean result;

        switch (keyWord) {
            case "id" -> result = !this.usersRepo.existsByUserId(value);
            case "email" -> result = !this.usersRepo.existsByEmail(value);
            case "nickName" -> result = !this.usersRepo.existsByNickName(value);
            default -> result = false;
        }
        log.info("\t☆★ [유저서비스] key : {} ★☆", result);
        return result;
    }


} // end class


