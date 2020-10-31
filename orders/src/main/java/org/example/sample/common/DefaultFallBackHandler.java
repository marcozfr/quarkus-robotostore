package org.example.sample.common;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;
import org.example.sample.users.model.User;

import java.util.concurrent.CompletionStage;

public class DefaultFallBackHandler implements FallbackHandler<CompletionStage<User>> {

    @Override
    public CompletionStage<User> handle(ExecutionContext context) {
        return Uni.createFrom()
                .item( new User("detaultUsername"))
                .subscribeAsCompletionStage();
    }

}
