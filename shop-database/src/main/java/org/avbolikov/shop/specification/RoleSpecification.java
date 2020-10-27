package org.avbolikov.shop.specification;

import org.avbolikov.shop.entity.users.Role;
import org.springframework.data.jpa.domain.Specification;

public final class RoleSpecification {

    public static Specification<Role> trueLiteral() {
        return (root, query, builder) -> builder.isTrue(builder.literal(true));
    }

    public static Specification<Role> roleLike(String role) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + role + "%");
    }
}
