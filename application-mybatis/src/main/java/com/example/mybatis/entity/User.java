package com.example.mybatis.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User implements Serializable {
    private Integer id;
    private String account;
    private String password;
    private Integer permission;
    private Status status;
    private Date createTime;

    @ToString
    public enum Status {
        VALID(1, "启用"),
        INVALID(0, "禁用");

        private final Integer tag;
        private final String desc;

        Status(Integer tag, String desc) {
            this.tag = tag;
            this.desc = desc;
        }

        public Integer getTag() {
            return tag;
        }

        public String getDesc() {
            return desc;
        }

        public static Status convert(Integer tag) {
            Status[] statuses = Status.values();
            for (Status status : statuses) {
                if (tag != null && status.tag.compareTo(tag) == 0) {
                    return status;
                }
            }
            return null;
        }
    }
}
