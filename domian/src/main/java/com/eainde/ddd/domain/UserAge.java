package com.eainde.ddd.domain;

import com.eainde.ddd.constants.DomainConstants;
import com.google.common.base.Preconditions;
import org.immutables.value.Value;

import com.eainde.ddd.Wrapped;

import com.eainde.ddd.Wrapped;

@Value.Immutable
@Wrapped
public abstract class UserAge extends Domain<Integer> {

    @Value.Check
    public void check(){
        final int value=value();
        Preconditions.checkState(value !=0, DomainConstants.USER_ID_VALIDATION_FAILED);
    }
}
