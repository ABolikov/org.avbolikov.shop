package org.avbolikov.shop.specification;

import org.avbolikov.shop.entity.users.User;
import org.springframework.data.jpa.domain.Specification;

public final class UserSpecification {

    public static Specification<User> trueLiteral() {
        return (root, query, builder) -> builder.isTrue(builder.literal(true));
    }

    public static Specification<User> loginLike(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }
}
