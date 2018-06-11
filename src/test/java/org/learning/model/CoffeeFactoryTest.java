package org.learning.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoffeeFactoryTest {

    private static final CoffeeFactory DEFAULT_COFFEE_FACTORY = CoffeeFactory.factory(builder -> {
        builder.register("Americano", Americano::new);
        builder.register("Cappuccino", Cappuccino::new);
        builder.register("Espresso", Espresso::new);
        builder.register("Macchiato", Macchiato::new);
    });

    @Test
    public void creation() {
        Coffee americano = DEFAULT_COFFEE_FACTORY.prepare("Americano");

        assertThat(americano).isExactlyInstanceOf(Americano.class);
    }

    @Test
    public void exceptionThrownWhenIsSpecifiedUnknownCoffeeType() {

        assertThatThrownBy(() -> DEFAULT_COFFEE_FACTORY.prepare("Latte"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unknown coffee type.");
    }
}
