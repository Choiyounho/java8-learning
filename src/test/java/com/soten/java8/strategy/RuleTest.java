package com.soten.java8.strategy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RuleTest {

    @Test
    void ruleSuccess() {
        assertThat(Rule.getRule(5)).isTrue();
    }

    @Test
    void ruleFailed() {
        assertThat(Rule.getRule(3)).isFalse();
    }

}
