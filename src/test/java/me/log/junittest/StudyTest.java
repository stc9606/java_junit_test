package me.log.junittest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;


class StudyTest {

    @Test
    @DisplayName("Test 조건")
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_11})
    void assume() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);

        assumeTrue("LOCAL".equalsIgnoreCase(test_env));

        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println("LOCAL");
        });

        assumingThat("STC".equalsIgnoreCase(test_env), () -> {
            System.out.println("STC");
        });
    }

    @Test
    @DisplayName("스터디 Timeout")
    void timeout() {
        // TODO 시간 초과 시 Test 종료
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
            // TODO ThreadLocal
        });

        // TODO 시간 초과 되어도 기댓 값이 종료 될 때 까지 대기
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
    }

    @Test
    @DisplayName("스터디 예외 처리")
    void exception() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = exception.getMessage();

        assertEquals("limit은 0보다 커야 한다.", message);
    }

    @Test
    @DisplayName("Assert 일괄 처리")
    void assert_all() {
        Study study = new Study(-10);

        // TODO Assert 항목 일괄 처리
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
                                () -> "스터디를 처음 만들면" + StudyStatus.DRAFT + "상태다."),
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
        );
    }

    @Test
    @DisplayName("모든 Test 실행 전 1번만 호출")
    void create() {
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