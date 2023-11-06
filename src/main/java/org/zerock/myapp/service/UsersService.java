package org.zerock.myapp.service;

import org.zerock.myapp.domain.Users;
import org.zerock.myapp.domain.UsersDTO;

public interface UsersService {
    public abstract void register_user(UsersDTO usersDTO);// register_user

    public abstract Users getUserById(String userId);

    //-------- <회원가입> 중복검사 ------------------
    public abstract boolean existsByUserInfo(String value, String keyWord);
//    public abstract boolean existsByNickName(String id);
//    public abstract boolean existsByEmail(String id);
    //-----------------------------------------------

} // end interface
