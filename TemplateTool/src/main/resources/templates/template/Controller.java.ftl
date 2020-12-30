package ${packageName}.controller;

import ${packageName}.service.${modelName}Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ${modelName}Controller {


    @Autowired
    private ${modelName}Service ${modelName?uncap_first}service;



}