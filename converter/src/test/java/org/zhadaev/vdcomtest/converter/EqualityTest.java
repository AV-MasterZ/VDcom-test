package org.zhadaev.vdcomtest.converter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EqualityTest {

    @Test
    public void testToString() {
        Unit km = new Unit("km");
        Unit m = new Unit("m");
        Equality filledEquality = new Equality.Builder()
                .leftValue(4.5).leftUnit(km)
                .rightValue(4500.0).rightUnit(m)
                .build();
        Equality notFilledEquality = new Equality.Builder()
                .leftValue(4.5).leftUnit(km)
                .rightUnit(m)
                .build();
        Equality emptyEquality = new Equality.Builder().build();

        String filledResult = filledEquality.toString();
        String notFilledResult = notFilledEquality.toString();
        String emptyResult = emptyEquality.toString();

        assertThat(filledResult).isEqualTo("4.5 km = 4500 m");
        assertThat(notFilledResult).isEqualTo("4.5 km = ? m");
        assertThat(emptyResult).isEqualTo("? ? = ? ?");
    }

}
