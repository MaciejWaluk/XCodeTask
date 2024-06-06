package com.mw.xcode.controller;

import com.mw.xcode.dto.RequestDto;
import com.mw.xcode.dto.UserRequest;
import com.mw.xcode.dto.UserResponse;
import com.mw.xcode.exceptions.CurrencyNotFoundException;
import com.mw.xcode.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/get-current-currency-value-command")
    public UserResponse getCurrencyValue(
            @RequestBody UserRequest userRequest) {
        return requestService.getCurrencyValue(userRequest);
    }

    @GetMapping("/requests")
    public List<RequestDto> getAllRequests() {
        return requestService.getAllRequests();
    }
}
