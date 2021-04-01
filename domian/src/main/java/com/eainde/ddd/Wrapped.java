package com.eainde.ddd;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Style;

@Style(
    typeAbstract = "*",
    typeImmutable = "Immutable*",
    visibility = Style.ImplementationVisibility.PUBLIC,
    defaults = @Immutable(builder = true, copy = false))
public @interface Wrapped {}
