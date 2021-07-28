package com.example.socialmedia.entity;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostTest {

    private Long id;
    private Long userId;
    private String content;

    private Post unit;

    @Before
    public void setUp() {
        id = 1L;
        userId = 1L;
        content = "Test post content";
        unit = new Post(id, userId, content);
    }

    @Test
    public void shouldSetFieldsFromConstructor() {
        assertThat(unit.getId()).isNotNull().isEqualTo(id);
        assertThat(unit.getContent()).isEqualTo(content);
        assertThat(unit.getUserId()).isEqualTo(userId);
    }

    @Test
    public void shouldSetTheUserId() {
        unit.setUserId(userId);
        assertThat(unit.getUserId()).isEqualTo(userId);
    }

    @Test
    public void shouldSetTheId() {
        unit.setUserId(id);
        assertThat(unit.getId()).isEqualTo(id);
    }

    @Test
    public void shouldSetTheContent() {
        unit.setContent(content);
        assertThat(unit.getContent()).isEqualTo(content);
    }

    @Test
    public void hasAToString() {
        assertThat(unit.toString()).isEqualTo(
                "Post(" +
                        "id=" + id +
                        ", userId=" + userId +
                        ", content=" + content +
                        ", createdAt=" + unit.getCreatedAt() +
                        ')');
    }


}
