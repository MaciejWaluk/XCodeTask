package com.mw.xcode.service;

import com.mw.xcode.dto.CurrencyRateResponse;
import com.mw.xcode.dto.RequestDto;
import com.mw.xcode.dto.UserRequest;
import com.mw.xcode.dto.UserResponse;
import com.mw.xcode.entity.Request;
import com.mw.xcode.exceptions.CurrencyNotFoundException;
import com.mw.xcode.repository.RequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestService {


    private final RequestRepository requestRepository;
    private final WebClient localApiClient;
    private final ModelMapper modelMapper;

    public RequestService(RequestRepository requestRepository, WebClient localApiClient, ModelMapper modelMapper) {
        this.requestRepository = requestRepository;
        this.localApiClient = localApiClient;
        this.modelMapper = modelMapper;
    }

    public UserResponse getCurrencyValue(UserRequest userRequest) {
        CurrencyRateResponse response = localApiClient
                .get()
                .uri("/" + userRequest.getCurrency())
                .retrieve()
                .onStatus(httpStatus -> httpStatus.value() == 404,
                        error -> Mono.error(new CurrencyNotFoundException("Currency " + userRequest.getCurrency() + " not found")))
                .bodyToMono(CurrencyRateResponse.class)
                .block();

        double rate = response.getRates().get(0).getMid();
        requestRepository.save(
                new Request().builder()
                        .currency(userRequest.getCurrency())
                        .name(userRequest.getName())
                        .value(rate)
                        .date(LocalDateTime.now())
                        .build()
        );
        return new UserResponse(rate);
    }

    public List<RequestDto> getAllRequests() {
        return requestRepository.findAll()
                .stream()
                .map(request -> modelMapper.map(request, RequestDto.class))
                .toList();
    }
}
