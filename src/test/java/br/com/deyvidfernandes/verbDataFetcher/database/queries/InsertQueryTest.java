package br.com.deyvidfernandes.verbDataFetcher.database.queries;

import ch.qos.logback.core.model.Model;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InsertQueryTest {
    private class TestModel {
        public String fieldOne;
        public String fieldTwo;
        public double fieldThree;
        public int fieldFour;

        public TestModel(String fieldOne, String fieldTwo, double fieldThree, int fieldFour) {
            this.fieldOne = fieldOne;
            this.fieldTwo = fieldTwo;
            this.fieldThree = fieldThree;
            this.fieldFour = fieldFour;
        }

        public TestModel() {
            this.fieldOne = null;
            this.fieldTwo = null;
            this.fieldThree = 0;
            this.fieldFour = 0;
        }
    }

    InsertQuery insertQuery = new InsertQuery<TestModel>("(one, two, three, four)", "test", new TestModel());

    @Test
    @Order(1)
    @DisplayName("Generate correct value lists")
    void generateValueLists() {

        insertQuery.addValue(new TestModel("a", "aa", 0.122543, 2132));
        insertQuery.addValue(new TestModel("b", "bb", 0.133543, 2232));
        insertQuery.addValue(new TestModel("c", "cc", 0.144543, 2332));
        String MySqlQuery = insertQuery.MYSQL();
        System.out.println(MySqlQuery);

        assertAll(() -> {
            assertThat(MySqlQuery, containsString("(\"a\", \"aa\", 0.122543, 2132),"));
            assertThat(MySqlQuery, containsString("(\"b\", \"bb\", 0.133543, 2232),"));
            assertThat(MySqlQuery, containsString("(\"c\", \"cc\", 0.144543, 2332),"));
        });
    }
}
