package service;

import config.UnitTestBase;
import cs309.data.Comment;
import cs309.repo.CommentRepository;
import cs309.service.CommentService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import util.MockData;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommentServiceUTest extends UnitTestBase {
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @Test
    public void saveComment() {
        Comment comment = mock(Comment.class);
        when(commentService.saveComment(comment)).thenReturn(MockData.getComment(1));
        Comment comment2 = commentService.saveComment(comment);
        assertTrue(comment2.getId() == 1);
    }
}
