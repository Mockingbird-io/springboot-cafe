package com.mockingbird.Springbootcafe.web;

import com.mockingbird.Springbootcafe.pojo.Product;
import com.mockingbird.Springbootcafe.pojo.PropertyValue;
import com.mockingbird.Springbootcafe.service.ProductService;
import com.mockingbird.Springbootcafe.service.PropertyValueService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PropertyValueController {
    @Resource
    PropertyValueService propertyValueService;

    @Resource
    ProductService productService;

    @GetMapping("/products/{pid}/propertyValues")
    public List<PropertyValue> list(@PathVariable(name = "pid") int pid) throws Exception {
        Product product = productService.get(pid);
        propertyValueService.init(product);
        return propertyValueService.list(product);
    }

    @PutMapping("/propertyValues")
    public Object update(@RequestBody PropertyValue propertyValue) {
        propertyValueService.update(propertyValue);
        return propertyValue;
    }
}
