package com.mycompany.myapp.process.purchaseProcess;

import com.mycompany.myapp.domain.Product;
import com.mycompany.myapp.domain.Product;
import com.mycompany.myapp.domain.PurchaseProcess;
import com.mycompany.myapp.service.dto.ProductDTO;
import com.mycompany.myapp.service.dto.ProductDTO;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface ProductFormMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    PurchaseProcessDTO toPurchaseProcessDTO(PurchaseProcess purchaseProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "quantidade", source = "quantidade")
    @Mapping(target = "produto", source = "produto")
    ProductDTO toProductDTO(Product product);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ProductDTO toProductDTO(Product name);
}
