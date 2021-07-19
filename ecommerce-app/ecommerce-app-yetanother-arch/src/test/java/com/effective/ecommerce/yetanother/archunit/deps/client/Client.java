package com.effective.ecommerce.yetanother.archunit.deps.client;

import com.effective.ecommerce.yetanother.archunit.deps.service.impl.Service;

public class Client {

    public void accessProviderConstructorAndMethod() {
        var provider = new Service();
        provider.dodo();
    }
}
