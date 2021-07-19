package com.effective.ecommerce.yetanother.order.domain.api;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public record CreateOrderCommand(
        @NotEmpty List<CreateOrderItemCommand> orderItems
) {
}
