package lk.ac.kln.stu.gunaseka_ps17050.orderservice.service;

import jakarta.transaction.Transactional;
import lk.ac.kln.stu.gunaseka_ps17050.orderservice.dto.OrderLineItemDto;
import lk.ac.kln.stu.gunaseka_ps17050.orderservice.dto.OrderRequest;
import lk.ac.kln.stu.gunaseka_ps17050.orderservice.model.Order;
import lk.ac.kln.stu.gunaseka_ps17050.orderservice.model.OrderLineItem;
import lk.ac.kln.stu.gunaseka_ps17050.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemList(orderLineItems);

        orderRepository.save(order);
    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();

        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());

        return orderLineItem;
    }
}