package org.whitebox.howlook.management.domain.report.service;

import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<String> loginThatServer(String ID, String PW);
}
