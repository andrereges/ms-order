package br.com.andre.order.msorder.integration.broker;

import br.com.andre.order.msorder.application.service.OrderService;
import br.com.andre.order.msorder.integration.broker.dto.OrderBrokerDto;
import br.com.andre.order.msorder.shared.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class QueueConsumer {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @RabbitListener(queues = {"order.create"})
    public void receive(@Payload OrderBrokerDto dto) {
        log.info("Order create : " + dto);

        orderService.save(orderMapper.orderBrokerDtoToOrderEntity(dto));
    }

}
