package org.example.sample;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeProductsResourceIT extends ProductsResourceTest {

    // Execute the same tests but in native mode.
}