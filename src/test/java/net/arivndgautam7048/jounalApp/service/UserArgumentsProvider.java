package net.arivndgautam7048.jounalApp.service;

import net.arivndgautam7048.jounalApp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {
    @Override

    public Stream<? extends Arguments> provideArguments(ExtensionContext var1) throws Exception {
    return Stream.of(
            Arguments.of(User.builder().userName("shyam").password("shyam").build()),
            Arguments.of(User.builder().userName("suraj").password("suraj").build())
            );
    }

}
