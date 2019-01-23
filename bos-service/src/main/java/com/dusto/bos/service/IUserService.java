package com.dusto.bos.service;

import com.dusto.bos.domain.User;

public interface IUserService {
    public User login(User user);

    public void editPassword(String id, String password);

    public void save(User user, String[] roleIds);
}
