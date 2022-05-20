package com.example.demo.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Pet model for store")
@Data
public class Pet {

    @ApiModelProperty(notes = "Internal pet identifer", example = "5")
    private Long id;

    @ApiModelProperty(notes = "Pet name", example = "Fido")
    private String name;

    @ApiModelProperty(notes = "Pet electronic tag", example = "ABC-123")
    private String tagId;

    @ApiModelProperty(notes = "Pet species", example = "dog")
    private String species;
}
