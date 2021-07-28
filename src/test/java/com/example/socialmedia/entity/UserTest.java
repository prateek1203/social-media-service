package com.example.socialmedia.entity;

import com.example.socialmedia.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserTest {

    private long id;
    private String name;
    private String emailId;
    private List<Long> posts;
    private Set<Long> followings;
    private User unit;

    @Before
    public void setUp() {
        id = 1L;
        name = "Andy";
        emailId = "andyh12@abc.com";
        posts = new ArrayList<>();
        followings = new HashSet<>();
        unit = new User(id, name, emailId);
    }

    @Test
    public void shouldSetFieldsFromConstructor() {
        assertThat(unit.getId()).isNotNull().isEqualTo(id);
        assertThat(unit.getName()).isEqualTo(name);
    }

    @Test
    public void shouldSetTheUserName() {
        unit.setName(name);
        assertThat(unit.getName()).isEqualTo(name);
    }

    @Test
    public void shouldAddThePost() {
        unit.setPostIds(List.of(1L));
        assertThat(unit.getPostIds().get(0)).isEqualTo(1L);
    }

    @Test
    public void shouldAddTheFollowees() {
        User user1 = new User(2L, "Tom", "tomD12@abc.com");
        User user2 = new User(3L, "Ivo", "ivo@123.com");
        unit.setFollowing(Set.of(user1.getId(), user2.getId()));
        assertThat(unit.getFollowing().contains(2L)).isTrue();
        assertThat(unit.getFollowing().contains(3L)).isTrue();
    }

    @Test
    public void hasAToString() {
        assertThat(unit.toString()).isEqualTo(
                "User(" +
                        "id=" + id +
                        ", name=" + name +
                        ", emailId=" + emailId +
                        ", postIds=" + posts +
                        ", following=" + followings +
                        ')');
    }


}
