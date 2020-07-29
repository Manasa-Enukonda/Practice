package com.perficient.myservice.controller;

import com.perficient.myservice.service.RetailService;
import com.perficient.myservice.types.ProductInfoRequest;
import com.perficient.myservice.types.ProductInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value="api/v1/retail",headers = "Accept=application/json" )
public class RetailController {

    private RetailService retailService;

    public RetailController(RetailService retailService){
        this.retailService=retailService;
    }


    @GetMapping(value = "/{productname}")
    public ResponseEntity<ProductInfoResponse> getProductInfo(@PathVariable @NotBlank String productname){

        ProductInfoResponse response = retailService.getRetailInfo(productname);

        return response != null ? new ResponseEntity<>(response, HttpStatus.OK) :  new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping
    public ResponseEntity<ProductInfoResponse> saveRetailInfo(final @RequestBody @Valid ProductInfoRequest request){
        ProductInfoResponse response = retailService.createRetailInfo(request);
        return response != null ? new ResponseEntity<>(response, HttpStatus.CREATED) :  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
