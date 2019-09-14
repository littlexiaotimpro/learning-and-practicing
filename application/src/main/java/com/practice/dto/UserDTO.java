package com.practice.dto;

import com.google.common.base.Converter;
import com.practice.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @ClassName UserDTO
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/9/913:38
 */
@Accessors(chain = true)
@Data
public class UserDTO {
    private String userName;
    private int age;
    private Date birthday;

    /**
     * 正向转换
     *
     * @return
     */
    public User convertToUser() {
        UserDTOConvert userDTOConvert = new UserDTOConvert();
        User user = userDTOConvert.convert(this);
        return user;
    }

    /**
     * 逆向转换
     * <code>public Converter<B, A> reverse()</code>
     * 调用此方法可以替换转换类的顺序，由此实现对象间的相互转换
     *
     * @param user
     * @return
     */
    public UserDTO convertToUserDTO(User user) {
        UserDTOConvert userDTOConvert = new UserDTOConvert();
        UserDTO userDTO = userDTOConvert.reverse().convert(user);
        return userDTO;
    }


    private static class UserDTOConvert extends Converter<UserDTO, User> {

        protected User doForward(UserDTO userDTO) {
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            return user;
        }

        protected UserDTO doBackward(User user) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            return null;
        }
    }

}
