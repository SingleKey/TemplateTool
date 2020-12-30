package ${packageName}.service.impl;

import ${packageName}.dao.${modelName}Dao;
import ${packageName}.service.${modelName}Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${modelName}ServiceImpl implements ${modelName}Service {

    @Autowired
    private ${modelName}Dao ${modelName?uncap_first}Dao;

}