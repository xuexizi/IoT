package com.jit.iot.entry;

/**
 * @packageName: com.jit.iot.entry
 * @className: User
 * @Description:
 * @author: xxz
 * @date: 2019/7/25 8:55
 */

public class User {
    private String user_id;
    private String username;
    private String password;
    private String phone;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
