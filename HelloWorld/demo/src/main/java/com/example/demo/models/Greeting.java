package com.example.demo.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Greeting information for the world!!!")
@Data
public class Greeting {

    @ApiModelProperty(notes = "Internal greeting identifer", example = "5")
    private Long id;

    @ApiModelProperty(notes = "Greeting message", example = "Hello world!!!")
    private String message;
}
