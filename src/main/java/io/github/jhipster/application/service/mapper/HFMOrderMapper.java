package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.HFMOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity HFMOrder and its DTO HFMOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {TrayMapper.class, PaymentTypeMapper.class, DeliveryInfoMapper.class, ResturantMapper.class, UserMapper.class})
public interface HFMOrderMapper extends EntityMapper<HFMOrderDTO, HFMOrder> {

    @Mapping(source = "tray.id", target = "trayId")
    @Mapping(source = "payment.id", target = "paymentId")
    @Mapping(source = "payment.name", target = "paymentName")
    @Mapping(source = "deliveryInfo.id", target = "deliveryInfoId")
    @Mapping(source = "resturant.id", target = "resturantId")
    @Mapping(source = "resturant.name", target = "resturantName")
    @Mapping(source = "user.id", target = "userId")
    HFMOrderDTO toDto(HFMOrder hFMOrder);

    @Mapping(source = "trayId", target = "tray")
    @Mapping(source = "paymentId", target = "payment")
    @Mapping(source = "deliveryInfoId", target = "deliveryInfo")
    @Mapping(source = "resturantId", target = "resturant")
    @Mapping(source = "userId", target = "user")
    HFMOrder toEntity(HFMOrderDTO hFMOrderDTO);

    default HFMOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        HFMOrder hFMOrder = new HFMOrder();
        hFMOrder.setId(id);
        return hFMOrder;
    }
}
