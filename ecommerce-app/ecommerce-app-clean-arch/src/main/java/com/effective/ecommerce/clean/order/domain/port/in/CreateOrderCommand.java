package com.effective.ecommerce.clean.order.domain.port.in;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public record CreateOrderCommand(
        @NotEmpty List<CreateOrderItemCommand> orderItems
) {
}
