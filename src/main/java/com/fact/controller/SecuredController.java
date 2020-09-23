package com.fact.controller;

import com.fact.model.Logtrace;
import com.fact.repository.LogtraceRepository;
import com.fact.util.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/secured")
public class SecuredController {
    @Autowired
    LogtraceRepository logtraceRepository;

    @GetMapping
    public String securedResource(Authentication auth) {
        String ret=  "This is a SECURED resource. Authentication: " + auth.getName() + "; Authorities: "  + auth.getAuthorities();
        Logtrace logtrace = new Logtrace();
        logtrace.setCreated(new Date());
        logtrace.setUsername( auth.getName() );
        logtrace.setTask("secured funct called");
        logtraceRepository.save(logtrace);
        SendMail.sendemail(auth.getName(), "Pour information,"
                + "\n\n API secured called..... !");
        return "This is a SECURED resource. Authentication: " + auth.getName() + "; Authorities: " + auth.getAuthorities();
    }

}