package com.example.carrental.service;

import com.example.carrental.model.password;
import com.example.carrental.repository.PasswordRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PasswordService {

@Autowired
public PasswordRepository passwordRepository;
    public List<password> getpasswordlist() {
        return passwordRepository.getpasswordlist();
    }

    public boolean checkSession(HttpSession httpSession) {
        return httpSession.getAttribute("adminlogin") != null;
    }
}
