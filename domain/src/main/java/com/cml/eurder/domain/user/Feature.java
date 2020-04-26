package com.cml.eurder.domain.user;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

public enum  Feature {
    ADD_ITEM(Role.ADMIN),
    UPDATE_ITEM(Role.ADMIN),
    DELETE_ITEM(Role.ADMIN),
    VIEW_ALL_CUSTOMERS(Role.ADMIN),
    VIEW_DETAILS_OF_CUSTOMER(Role.ADMIN),
    VIEW_ALL_ORDERS(Role.ADMIN),
    CREATE_ORDER(Role.CUSTOMER);


    private Role[] roles;

    Feature(Role... roles) {this.roles = roles; }

    public List<Role> getRoles() {
        return newArrayList(roles);
    }

    public static List<Feature> getFeaturesForRoles(List<String> rolesOfUserAsString) {
        List<Role> rolesOfUser = rolesOfUserAsString.stream()
                .map(Role::valueOf)
                .collect(Collectors.toList());
        return Arrays.stream(Feature.values())
                .filter(feature -> !Collections.disjoint(feature.getRoles(), rolesOfUser))
                .collect(Collectors.toList());
    }
}
