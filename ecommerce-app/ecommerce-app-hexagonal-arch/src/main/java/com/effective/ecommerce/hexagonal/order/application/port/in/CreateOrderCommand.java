package com.effective.ecommerce.hexagonal.order.application.port.in;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public record CreateOrderCommand(
        @NotEmpty List<CreateOrderItemCommand> orderItems
) {
}
