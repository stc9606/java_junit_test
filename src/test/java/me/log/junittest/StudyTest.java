package me.log.junittest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class StudyTest {

    @Test
    void create() {
        Study study = new Study();
        assertNotNull(study);

        System.out.println("create");
    }

    @Test
    @Disabled // 테스트 disable 처
    void create1() {
        System.out.println("create");
    }

    @DisplayName("모든 Test 실행 전 1번만 호출")
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @DisplayName("모든 Test 실행 후 1번만 호출")
    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @DisplayName("각각의 Test 실행 전 1번만 호출")
    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @DisplayName("각각의 Test 실행 후 1번만 호출")
    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }

}