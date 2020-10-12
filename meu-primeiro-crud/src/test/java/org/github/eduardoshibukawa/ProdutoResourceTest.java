package org.github.eduardoshibukawa;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.common.QuarkusTestResource;

import org.junit.jupiter.api.Test;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.dataset.DataSet;

import org.junit.jupiter.api.Assertions;

@DBRider
@QuarkusTest
@QuarkusTestResource(DataBaseLifecycle.class)
public class ProdutoResourceTest {

    @Test
    @DataSet("produtos1.yml")
    public void testUm() {
        Assertions.assertEquals(1, Produto.count());
    }
    
}
